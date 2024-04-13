package com.example.khtbe.global.config.security.jwt.exception;

import com.example.khtbe.global.error.exception.BaseException;
import com.example.khtbe.global.error.exception.ErrorCode;

public class TokenErrorException extends BaseException {
    public static final BaseException EXCEPTION = new TokenErrorException();

    public TokenErrorException() {
        super(ErrorCode.TOKEN_ERROR);
    }
}
