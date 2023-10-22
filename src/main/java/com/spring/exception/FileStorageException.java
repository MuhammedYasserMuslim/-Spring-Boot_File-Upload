package com.spring.exception;

public class FileStorageException extends RuntimeException {

    public FileStorageException() {
        super();
    }

    public FileStorageException(String message, Throwable cause) {
        super(message, cause);

    }

    public FileStorageException(String message) {
        super(message);// TODO Auto-generated constructor stub
    }

}