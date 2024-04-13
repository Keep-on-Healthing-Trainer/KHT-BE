package com.example.khtbe.infra.aws.exception;

import com.example.khtbe.global.error.exception.BaseException;
import com.example.khtbe.global.error.exception.ErrorCode;

public class ImageUploadFailException extends BaseException {
    public static final BaseException EXCEPTION = new ImageUploadFailException();
    private ImageUploadFailException(){
        super(ErrorCode.IMAGE_UPLOAD_FAIL);
    }
}
