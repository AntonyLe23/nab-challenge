package com.phatlt.prepaiddatasimcard.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoucherCodeServiceException extends RuntimeException {
    private int code;
    private String message;
    private String reason;

    public VoucherCodeServiceException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public VoucherCodeServiceException(int code, String reason, String message) {
        this.code = code;
        this.reason = reason;
        this.message = message;
    }
}
