package com.example.khtbe.domain.comment.exception;

import com.example.khtbe.global.error.exception.BaseException;
import com.example.khtbe.global.error.exception.ErrorCode;

public class CommentNotFoundException extends BaseException {

    public final static CommentNotFoundException EXCEPTION = new  CommentNotFoundException();

    public CommentNotFoundException() {
        super(ErrorCode.COMMENT_NOT_FOUND);
    }
}
