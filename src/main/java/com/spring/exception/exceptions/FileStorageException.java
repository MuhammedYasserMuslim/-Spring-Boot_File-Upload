package com.spring.exception.exceptions;

import org.springframework.http.HttpStatus;

public class FileStorageException extends BaseException {


    public FileStorageException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_GATEWAY;
    }
}