package com.phatlt.OTPService.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "otp")
@Getter
@Setter
@AllArgsConstructor
public class otpDAO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "otpcode")
    private String otpcode;

    @Column(name = "createdate")
    private String createdate;

    @Column(name = "updatedate")
    private String updatedate;

    @Column(name = "expire")
    private boolean expire;

    @Column(name = "authid")
    private String authId;

    public otpDAO(String otpcode, String createdate, String updatedate, boolean expire, String authId) {
        this.otpcode = otpcode;
        this.createdate = createdate;
        this.updatedate = updatedate;
        this.expire = expire;
        this.authId = authId;
    }

    public otpDAO() {

    }
}
