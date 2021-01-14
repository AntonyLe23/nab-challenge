package com.phatlt.OTPService.service;

import com.phatlt.OTPService.dao.otpDAO;
import com.phatlt.OTPService.model.OtpResponse;

import java.util.List;

public interface iOTPService {
    otpDAO getOTP();
    boolean authenOTP(OtpResponse otpResponse);
}
