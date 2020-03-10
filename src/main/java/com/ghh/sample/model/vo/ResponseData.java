package com.ghh.sample.model.vo;

public class ResponseData {
    private int code;
    private String message;
    private Object data;

    public ResponseData(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ResponseData ok() {
        return new ResponseData(0, null, null);
    }

    public static ResponseData ok(Object data) {
        return new ResponseData(0, null, data);
    }

    public static ResponseData error(int code, String message) {
        return new ResponseData(code, message, null);
    }

    public static ResponseData error(int code, String message, Object data) {
        return new ResponseData(code, message, data);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
