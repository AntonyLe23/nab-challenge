package com.phatlt.OTPService.controller;

import com.phatlt.OTPService.constant.Common;
import com.phatlt.OTPService.dao.otpDAO;
import com.phatlt.OTPService.model.APIResponse;
import com.phatlt.OTPService.model.OtpResponse;
import com.phatlt.OTPService.service.iOTPService;
import lombok.extern.jbosslog.JBossLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@JBossLog
public class OTPController {
    private static final String BASE_URL = "/api/v1/otp";

    @Autowired
    private iOTPService iOTPService;

    @GetMapping(BASE_URL + "/getOTP")
    @ResponseBody
    public APIResponse getOTP() {
        try {
            otpDAO dao = iOTPService.getOTP();
            return new APIResponse(HttpStatus.OK.value(), Common.SUCCESS, new OtpResponse(dao.getOtpcode(), dao.getAuthId(), null));
        } catch (Exception e) {
            log.error(e.getMessage());
            return new APIResponse(-1, e.getMessage(), null);
        }
    }

    @PostMapping(value = BASE_URL + "/authenOTP")
    @ResponseBody
    public APIResponse authenOTP(@RequestBody OtpResponse otpResponse) {
        try {
            return new APIResponse(HttpStatus.OK.value(), Common.SUCCESS, iOTPService.authenOTP(otpResponse));
        } catch (Exception e) {
            log.error(e.getMessage());
            return new APIResponse(-1, e.getMessage(), null);
        }
    }
}
