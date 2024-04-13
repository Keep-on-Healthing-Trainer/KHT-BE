package com.example.khtbe.domain.user.service.exception;

import com.example.khtbe.global.error.exception.BaseException;
import com.example.khtbe.global.error.exception.ErrorCode;

public class UserNotFoundException extends BaseException {
    public static final BaseException EXCEPTION= new UserNotFoundException();

    private UserNotFoundException(){
        super(ErrorCode.USER_NOT_FOUND);
    }
}
