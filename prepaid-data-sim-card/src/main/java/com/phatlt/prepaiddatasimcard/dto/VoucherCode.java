package com.phatlt.prepaiddatasimcard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class VoucherCode implements Serializable {
    String code;

}
