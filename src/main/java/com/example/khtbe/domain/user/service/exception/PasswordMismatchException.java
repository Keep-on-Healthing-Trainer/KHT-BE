package com.example.khtbe.domain.user.service.exception;

import com.example.khtbe.global.error.exception.BaseException;
import com.example.khtbe.global.error.exception.ErrorCode;

public class PasswordMismatchException extends BaseException {
    public static final BaseException EXCEPTION = new PasswordMismatchException();

    public PasswordMismatchException(){
        super(ErrorCode.PASSWORD_MISMATCH);
    }
}
