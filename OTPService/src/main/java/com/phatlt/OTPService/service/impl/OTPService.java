package com.phatlt.OTPService.service.impl;

import com.phatlt.OTPService.constant.Common;
import com.phatlt.OTPService.dao.otpDAO;
import com.phatlt.OTPService.model.OtpResponse;
import com.phatlt.OTPService.repository.OTPRepo;
import com.phatlt.OTPService.service.iOTPService;
import lombok.extern.jbosslog.JBossLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.UUID;

@Service
@JBossLog
public class OTPService implements iOTPService {

    @Autowired
    private OTPRepo otpRepo;

    @Override
    public otpDAO getOTP() {
        try {
            Random rd = new Random();
            String code = (rd.nextInt(999999 + 1 - 100000) + 100000) + "";
            String authId = UUID.randomUUID().toString();
            otpDAO dao = new otpDAO();
            dao.setOtpcode(code);
            dao.setAuthId(authId);
            dao.setExpire(false);
            dao.setCreatedate(LocalDateTime.now().toString());
            otpRepo.save(dao);
            return dao;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public boolean authenOTP(OtpResponse otpResponse) {
        try {
            otpDAO otpDAO = otpRepo.findByAuthIdAndOtpcodeAndAndExpire(otpResponse.getAuthId(), otpResponse.getOtpCode(), false);

            if (otpDAO != null) {
                Duration duration = Duration.between(LocalDateTime.parse(otpResponse.getCurrentDateTime(), DateTimeFormatter.ISO_DATE_TIME),
                                                        LocalDateTime.parse(otpDAO.getCreatedate(), DateTimeFormatter.ISO_DATE_TIME));
                long seconds = Math.abs(duration.getSeconds());

                // OTP expire because the response time is > MAX_EXPIRE
                if (seconds > Common.MAX_EXPIRE) {
                    setUnavailableOtp(otpDAO);
                    return false;
                }

                setUnavailableOtp(otpDAO);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    private otpDAO setUnavailableOtp(otpDAO otpDAO) {
        otpDAO.setUpdatedate(LocalDateTime.now().toString());
        otpDAO.setExpire(true);
        return otpRepo.save(otpDAO);
    }
}
