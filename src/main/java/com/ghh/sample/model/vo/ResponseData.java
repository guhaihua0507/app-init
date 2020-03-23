package com.ghh.sample.model.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.pagehelper.Page;

import java.util.ArrayList;

public class ResponseData {
    private int code;
    private String message;
    private Object data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer pageNum;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer pageSize;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long total;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer pages;

    public ResponseData(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        if (data instanceof Page) {
            Page<?> pagedData = (Page<?>)data;
            this.pageNum = pagedData.getPageNum();
            this.pageSize = pagedData.getPageSize();
            this.total = pagedData.getTotal();
            this.pages = pagedData.getPages();
            this.data = new ArrayList<>(pagedData);
        } else {
            this.data = data;
        }
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

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }
}
