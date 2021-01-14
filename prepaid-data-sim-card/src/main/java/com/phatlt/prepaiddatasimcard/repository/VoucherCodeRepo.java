package com.phatlt.prepaiddatasimcard.repository;

import com.phatlt.prepaiddatasimcard.dao.VoucherCodeDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoucherCodeRepo extends JpaRepository<VoucherCodeDAO, Long> {
    List<VoucherCodeDAO> findAllByPhoneNumber(String phoneNumber);
    boolean existsByPhoneNumber(String phoneNumber);
}
