package com.phatlt.prepaiddatasimcard.service.impl;

import com.google.gson.Gson;
import com.phatlt.prepaiddatasimcard.dao.AuthorizationDAO;
import com.phatlt.prepaiddatasimcard.dao.VoucherCodeDAO;
import com.phatlt.prepaiddatasimcard.dto.OtpResponse;
import com.phatlt.prepaiddatasimcard.dto.ValidOtp;
import com.phatlt.prepaiddatasimcard.dto.VoucherCode;
import com.phatlt.prepaiddatasimcard.exception.VoucherCodeServiceException;
import com.phatlt.prepaiddatasimcard.externalService.VoucherCodeAPI;
import com.phatlt.prepaiddatasimcard.repository.AuthorizationOtpRepo;
import com.phatlt.prepaiddatasimcard.repository.VoucherCodeRepo;
import com.phatlt.prepaiddatasimcard.service.iOtpService;
import com.phatlt.prepaiddatasimcard.service.iVoucherCodeService;
import lombok.extern.jbosslog.JBossLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@JBossLog
@Service
public class VoucherCodeImpl implements iVoucherCodeService {
    private static final String voucherCodeBaseURI = VoucherCodeAPI.DOMAIN.url + VoucherCodeAPI.PORT.url;
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final Gson gson = new Gson();

    @Autowired
    VoucherCodeRepo voucherCodeRepo;

    @Autowired
    AuthorizationOtpRepo authorizationOtpRepo;

    @Autowired
    iOtpService iOtpService;

    @Override
    public VoucherCode getVoucherCode(String phoneNumber) {
        try {
            String uri = voucherCodeBaseURI + VoucherCodeAPI.GET_VOUCHER_CODE.url;
            ResponseEntity response = restTemplate.getForEntity(uri, String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                VoucherCode code = gson.fromJson(response.getBody().toString(), VoucherCode.class);
                VoucherCodeDAO dao = new VoucherCodeDAO();
                dao.setCode(code.getCode());
                dao.setPhoneNumber(phoneNumber);
                voucherCodeRepo.save(dao);
                return code;
            } else {
                throw new VoucherCodeServiceException(response.getStatusCodeValue(), response.getStatusCode().getReasonPhrase(), response.getBody().toString());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return new VoucherCode("-1");
        }
    }

    @Override
    public List<VoucherCode> getAllVoucherCodeByPhoneNumber(ValidOtp validOtp) {
        try {
            AuthorizationDAO auDao = authorizationOtpRepo.findByPhoneNumberAndAuthIdAndExpire(validOtp.getPhoneNumber(), validOtp.getOtpResponse().getAuthId(), false);
            if (auDao != null  && iOtpService.authenOtp(validOtp.getOtpResponse())) {
                List<VoucherCodeDAO> list = voucherCodeRepo.findAllByPhoneNumber(validOtp.getPhoneNumber());
                List<VoucherCode> res = new ArrayList<>();
                list.forEach(d -> {
                    VoucherCode code = new VoucherCode(d.getCode());
                    res.add(code);
                });
                auDao.setExpire(true);

                // Set authorization expire to true
                authorizationOtpRepo.save(auDao);
                return res;
            } else {
                return Collections.emptyList();
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public OtpResponse verifyPhoneNumber(String phoneNumber) {
        try {
            if (voucherCodeRepo.existsByPhoneNumber(phoneNumber)) {
                OtpResponse response = iOtpService.getOtpCode();
                AuthorizationDAO dao = new AuthorizationDAO();
                dao.setPhoneNumber(phoneNumber);
                dao.setAuthId(response.getAuthId());
                dao.setExpire(false);
                dao = authorizationOtpRepo.save(dao);
                log.info(dao.getId());
                return response;
            } else {
                return new OtpResponse();
            }
        } catch (Exception e) {
            log.error(e);
            return null;
        }
    }
}
