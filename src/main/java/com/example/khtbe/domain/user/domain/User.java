package com.example.khtbe.domain.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
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

    @Builder
    public User(UUID id, String userId, String name, String password, String phoneNumber){
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public String modifyProfile(String profileImgUrl){
        this.path = profileImgUrl;
        return this.path;
    }
}

