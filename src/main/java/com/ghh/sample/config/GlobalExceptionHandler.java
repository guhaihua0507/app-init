package com.ghh.sample.config;

import com.ghh.sample.exceptions.GlobalResponseException;
import com.ghh.sample.model.vo.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public ResponseData exceptionHandler(HttpServletRequest request, Exception e) {
        logger.error("exception caught on rest call", e);

        if (e instanceof GlobalResponseException) {
            GlobalResponseException ex = (GlobalResponseException)e;
            return ResponseData.error(ex.getCode(), ex.getMessage());
        } else {
            return ResponseData.error(1, e.getMessage());
        }
    }
}
