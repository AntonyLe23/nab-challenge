package com.phatlt.prepaiddatasimcard.repository;

import com.phatlt.prepaiddatasimcard.dao.AuthorizationDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorizationOtpRepo extends JpaRepository<AuthorizationDAO, Long> {
    boolean existsByPhoneNumberAndAuthIdAndExpire(String phoneNumber, String authId, boolean expire);
    AuthorizationDAO findByPhoneNumberAndAuthIdAndExpire(String phoneNumber, String authId, boolean expire);
}
