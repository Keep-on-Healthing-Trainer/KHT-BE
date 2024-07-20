package com.example.khtbe.domain.user.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "BINARY(16)", nullable = false)
    private UUID id;

    @NotNull
    @Column(name = "user_id", unique = true)
    private String userId;

    @NotNull
    private String name;

    @NotNull
    private String password;

    @NotNull
    private String phoneNumber;

    private String path;

    private Integer totalCounts = 0;

    private Integer sitUpCounts = 0;

    private Integer pushUpCounts = 0;

    private Integer squatCounts = 0;

    @Builder
    public User(UUID id, String userId, String name, String password, String phoneNumber){
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public String modifyProfile(String path){
        this.path = path;
        return this.path;
    }

    public void addCounts(int counts) {
        this.totalCounts += counts;
    }

    public void addPushUpCounts(int counts) {
        this.pushUpCounts += counts;
    }

    public void addSitUpCounts(int counts) {
        this.sitUpCounts += counts;
    }

    public void addSquatCounts(int counts) {
        this.squatCounts += counts;
    }
}

