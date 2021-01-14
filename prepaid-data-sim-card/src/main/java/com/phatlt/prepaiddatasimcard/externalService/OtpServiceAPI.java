package com.phatlt.prepaiddatasimcard.externalService;

import lombok.AllArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
public enum OtpServiceAPI implements Serializable {
    DOMAIN("http://localhost:"),
    PORT("8081"),
    BASE_URL("/api/v1/otp"),

    GET_OTP_UTL("/getOTP"),
    AUTHEN_OTP("/authenOTP");

    public String url;
}
