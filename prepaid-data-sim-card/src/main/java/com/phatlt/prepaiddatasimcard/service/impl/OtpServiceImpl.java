package com.phatlt.prepaiddatasimcard.service.impl;

import com.google.gson.Gson;
import com.phatlt.prepaiddatasimcard.dto.APIResponse;
import com.phatlt.prepaiddatasimcard.dto.OtpResponse;
import com.phatlt.prepaiddatasimcard.externalService.OtpServiceAPI;
import com.phatlt.prepaiddatasimcard.service.iOtpService;
import lombok.extern.jbosslog.JBossLog;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
@JBossLog
public class OtpServiceImpl implements iOtpService {
    private final String BASE_OTP_URL = OtpServiceAPI.DOMAIN.url + OtpServiceAPI.PORT.url + OtpServiceAPI.BASE_URL.url;
    private RestTemplate restTemplate = new RestTemplate();
    private Gson gson = new Gson();

    @Override
    public OtpResponse getOtpCode() {
        String getOtpUri = BASE_OTP_URL + OtpServiceAPI.GET_OTP_UTL.url;
        ResponseEntity entity = restTemplate.getForEntity(getOtpUri, String.class);
        log.info(entity);
        if (entity.getStatusCode() == HttpStatus.OK) {
            APIResponse apiResponse = gson.fromJson(entity.getBody().toString(), APIResponse.class);
            OtpResponse otpResponse = gson.fromJson(apiResponse.getData().toString(), OtpResponse.class);
//            otpResponse.setCurrentDateTime(LocalDateTime.now().toString());
            return otpResponse;
        }
        return null;
    }

    @Override
    public boolean authenOtp(OtpResponse otpResponse) {
        String postOtpUri = BASE_OTP_URL + OtpServiceAPI.AUTHEN_OTP.url;
        ResponseEntity entity = restTemplate.postForEntity(postOtpUri, otpResponse, String.class);
        if (entity.getStatusCode() == HttpStatus.OK){
            APIResponse apiResponse = gson.fromJson(entity.getBody().toString(), APIResponse.class);
            return gson.fromJson(apiResponse.getData().toString(), Boolean.class);
        }
        return false;
    }
}
