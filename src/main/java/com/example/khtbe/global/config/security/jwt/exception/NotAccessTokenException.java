package com.example.khtbe.global.config.security.jwt.exception;

import com.example.khtbe.global.error.exception.BaseException;
import com.example.khtbe.global.error.exception.ErrorCode;

public class NotAccessTokenException extends BaseException {
    public static final BaseException EXCEPTION = new NotAccessTokenException();

    public NotAccessTokenException(){
        super(ErrorCode.NOT_ACCESS_TOKEN);
    }
}
