package com.phatlt.OTPService.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class APIResponse implements Serializable {
    private int codeStatus;
    private String messageStatus;
    private Object data;
}
