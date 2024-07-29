package com.spring.exception.exceptions;

import org.springframework.http.HttpStatus;

public class convertMultipartFileToFileException extends BaseException {

    public convertMultipartFileToFileException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
