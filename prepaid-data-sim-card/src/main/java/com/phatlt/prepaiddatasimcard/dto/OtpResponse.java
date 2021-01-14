package com.phatlt.prepaiddatasimcard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class OtpResponse implements Serializable {
    private String otpCode;
    private String authId;
    private String currentDateTime;

    public OtpResponse() {
    }

    public OtpResponse(String otpCode, String authId) {
        this.otpCode = otpCode;
        this.authId = authId;
    }
}
