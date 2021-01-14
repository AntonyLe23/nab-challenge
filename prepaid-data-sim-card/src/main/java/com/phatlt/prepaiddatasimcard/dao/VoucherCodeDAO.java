package com.phatlt.prepaiddatasimcard.dao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "vouchercode")
public class VoucherCodeDAO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    long id;

    @Column(name = "phonenumber")
    String phoneNumber;

    @Column(name = "code")
    String code;


}
