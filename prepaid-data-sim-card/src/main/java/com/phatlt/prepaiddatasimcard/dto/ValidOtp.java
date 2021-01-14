package com.phatlt.prepaiddatasimcard.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ValidOtp implements Serializable {
    private String phoneNumber;
    private OtpResponse otpResponse;
}
