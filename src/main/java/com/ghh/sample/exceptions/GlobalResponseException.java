package com.ghh.sample.exceptions;

public class GlobalResponseException extends RuntimeException {
    private int code;

    public GlobalResponseException(int code, String message) {
        super(message);
        this.code = code;
    }

    public GlobalResponseException(int code, Exception e) {
        super(e);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
