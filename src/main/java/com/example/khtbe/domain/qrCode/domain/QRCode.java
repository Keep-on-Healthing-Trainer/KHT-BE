package com.example.khtbe.domain.qrCode.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class QRCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String code;

    public QRCode() {}

    public QRCode(String code) {
        this.code = code;
    }
}