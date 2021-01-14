package com.phatlt.prepaiddatasimcard.controller;

import com.google.gson.Gson;
import com.phatlt.prepaiddatasimcard.dto.APIResponse;
import com.phatlt.prepaiddatasimcard.dto.OtpResponse;
import com.phatlt.prepaiddatasimcard.dto.ValidOtp;
import com.phatlt.prepaiddatasimcard.dto.VoucherCode;
import com.phatlt.prepaiddatasimcard.service.iVoucherCodeService;
import lombok.extern.jbosslog.JBossLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@JBossLog
@Controller
public class VoucherCodeController {
    private static final String BASE_URL = "/api/v1/voucher-code";
    private Gson gson = new Gson();
    @Autowired
    private iVoucherCodeService iVoucherCodeService;

    @CrossOrigin
    @GetMapping(BASE_URL)
    @ResponseBody
    public VoucherCode getVoucherCode(@RequestParam(name = "phone-number") String phone_number) {
        return iVoucherCodeService.getVoucherCode(phone_number);
    }

    @CrossOrigin
    @GetMapping(BASE_URL + "/verify")
    @ResponseBody
    public APIResponse verifyPhoneNumber(@RequestParam (name = "phone-number") String phoneNumber) {
        OtpResponse response = iVoucherCodeService.verifyPhoneNumber(phoneNumber);
        APIResponse apiResponse = new APIResponse();
        if (response != null) {
            apiResponse.setCodeStatus(HttpStatus.OK.value());
            apiResponse.setMessageStatus("Success");
            ValidOtp validOtp = new ValidOtp();
            validOtp.setPhoneNumber(phoneNumber);
            validOtp.setOtpResponse(response);
            apiResponse.setData(validOtp);
            return apiResponse;
        }
        return null;
    }

    @CrossOrigin
    @PostMapping(BASE_URL + "/voucher-codes")
    @ResponseBody
    public List<VoucherCode> getAllVoucherCodeByPhoneNumber(@RequestBody ValidOtp data) {
        data.getOtpResponse().setCurrentDateTime(LocalDateTime.now().toString());
        return iVoucherCodeService.getAllVoucherCodeByPhoneNumber(data);
    }
}
