package com.phatlt.OTPService.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class OtpResponse implements Serializable {
    private String otpCode;
    private String authId;
    private String currentDateTime;
}
