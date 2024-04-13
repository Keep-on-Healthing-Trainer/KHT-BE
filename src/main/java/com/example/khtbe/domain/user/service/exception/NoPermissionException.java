package com.example.khtbe.domain.user.service.exception;

import com.example.khtbe.global.error.exception.BaseException;
import com.example.khtbe.global.error.exception.ErrorCode;

public class NoPermissionException extends BaseException {
    public static final BaseException EXCEPTION = new NoPermissionException();

    public NoPermissionException(){
        super(ErrorCode.NO_PERMISSION);
    }
}
