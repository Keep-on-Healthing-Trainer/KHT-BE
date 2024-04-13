package com.example.khtbe.infra.aws.exception;

import com.example.khtbe.global.error.exception.BaseException;
import com.example.khtbe.global.error.exception.ErrorCode;

public class ImageBadRequestException extends BaseException {
    public static final BaseException EXCEPTION = new ImageBadRequestException();
    private ImageBadRequestException(){
        super(ErrorCode.IMAGE_BAD_REQUEST);
    }
}

