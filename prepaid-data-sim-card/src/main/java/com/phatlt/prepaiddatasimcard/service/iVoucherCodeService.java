package com.phatlt.prepaiddatasimcard.service;

import com.phatlt.prepaiddatasimcard.dao.VoucherCodeDAO;
import com.phatlt.prepaiddatasimcard.dto.OtpResponse;
import com.phatlt.prepaiddatasimcard.dto.ValidOtp;
import com.phatlt.prepaiddatasimcard.dto.VoucherCode;

import java.util.List;

public interface iVoucherCodeService {
    VoucherCode getVoucherCode(String phoneNumber);

    List<VoucherCode> getAllVoucherCodeByPhoneNumber(ValidOtp validOtp);

    OtpResponse verifyPhoneNumber(String phoneNumber);
}
