package com.example.khtbe.global.config.security.jwt.exception;

import com.example.khtbe.global.error.exception.BaseException;
import com.example.khtbe.global.error.exception.ErrorCode;

public class TokenUnauthorizedException extends BaseException {
    public static final BaseException EXCEPTION = new TokenUnauthorizedException();

    public TokenUnauthorizedException(){
        super(ErrorCode.TOKEN_UNAUTHORIZED);
    }
}
