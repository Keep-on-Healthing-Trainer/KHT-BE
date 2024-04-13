package com.example.khtbe.domain.user.service.exception;

import com.example.khtbe.global.error.exception.BaseException;
import com.example.khtbe.global.error.exception.ErrorCode;

public class UserIdAlreadyExistException extends BaseException {
    public static final BaseException EXCEPTION = new UserIdAlreadyExistException();
    public UserIdAlreadyExistException(){
        super(ErrorCode.USER_ID_ALREADY_EXIST);
    }
}
