package com.phatlt.prepaiddatasimcard.externalService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum VoucherCodeAPI {
    DOMAIN("http://localhost:"),
    PORT("1000"),

    GET_VOUCHER_CODE("/voucher-code");

    public String url;
}
