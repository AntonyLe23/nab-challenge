package com.phatlt.prepaiddatasimcard.controller;

import com.phatlt.prepaiddatasimcard.dto.OtpResponse;
import com.phatlt.prepaiddatasimcard.service.iOtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OtpController {
    private static final String BASE_URL = "/api/v1/otp";
    @Autowired
    iOtpService iOtpService;

    @GetMapping(BASE_URL + "/getOtp")
    @ResponseBody
    public OtpResponse getOtp() {
        return iOtpService.getOtpCode();
    }

    @PostMapping(BASE_URL + "/authenOtp")
    @ResponseBody
    public boolean authenOtp(@RequestBody OtpResponse otpResponse) {
        return false;
    }
}
