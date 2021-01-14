package com.phatlt.prepaiddatasimcard.service;

import com.phatlt.prepaiddatasimcard.dto.OtpResponse;

public interface iOtpService {
    OtpResponse getOtpCode();
    boolean authenOtp (OtpResponse otpResponse);
}
