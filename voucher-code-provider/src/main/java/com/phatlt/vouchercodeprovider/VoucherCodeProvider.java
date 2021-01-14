package com.phatlt.vouchercodeprovider;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class VoucherCodeProvider {


    @GetMapping("/voucher-code")
    public VoucherCode getVoucherCode() {
        String digits = "0123456789";
        Random rnd = new Random();
        int len = 15;
        StringBuilder code = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            code.append(digits.charAt(rnd.nextInt(digits.length())));

        return new VoucherCode(code.toString());
    }
}
