package com.phatlt.prepaiddatasimcard.dao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "authorizationcode")
@Setter
@Getter
public class AuthorizationDAO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    long id;

    @Column(name = "phonenumber")
    private String phoneNumber;

    @Column(name = "authid")
    private String authId;

    @Column(name = "expire")
    private boolean expire;

    public AuthorizationDAO() {
    }
}
