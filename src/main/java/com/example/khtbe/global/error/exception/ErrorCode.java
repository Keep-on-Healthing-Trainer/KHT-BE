package com.example.khtbe.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {
    //Bad Request
    BAD_REQUEST(400, "잘못된 요청입니다."),
    PASSWORD_MISMATCH(400, "비밀번호가 일치하지 않습니다."),
    IMAGE_BAD_REQUEST(400, "이미지의 형식이 올바르지 않습니다"),
    IMAGE_UPLOAD_FAIL(400, "이미지가 정상적으로 저장되지 않았습니다"),
    USER_NOT_MATCH(400, "유저가 일치하지 않습니다."),
    NO_PERMISSION(403, "권한이 없습니다."),
    QR_BAD_REQUEST(400, "QR이 정상적이지 않습니다."),

    //UnAuthorised
    NOT_ACCESS_TOKEN(401, "access 토큰이 아닙니다."),
    TOKEN_ERROR(401, "토큰이 잘못되지 않았는지 확인해주세요."),
    TOKEN_UNAUTHORIZED(401,"토큰이 승인 되지 않습니다."),
    JWT_INVALID(401, "토큰이 유효하지 않습니다."),

    //Not Found
    USER_NOT_FOUND(404,"찾을 수 없는 유저입니다."),
    REFRESH_TOKEN_NOT_FOUND(404, "리프레쉬 토큰을 찾을 수 없습니다."),

    USER_ID_ALREADY_EXIST(409, "계정 아이디가 이미 존재합니다"),

    COMMENT_NOT_FOUND(404, "댓글을 찾을 수 없습니다."),
    POST_NOT_FOUND(404, "게시글을 찾을 수 없습니다.");

    private final int statusCode;
    private final String errorMessage;
}

