package com.phatlt.OTPService.repository;

import com.phatlt.OTPService.dao.otpDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OTPRepo extends JpaRepository<otpDAO, Long> {
    otpDAO findByAuthIdAndOtpcodeAndAndExpire(String authId, String otpCode, boolean expire);
}
