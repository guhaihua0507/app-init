package com.ghh.sample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ErrorImageLogger {
    private final static Logger logger = LoggerFactory.getLogger(ErrorImageLogger.class);

    public void info(String name) {
        logger.info(name);
    }
}
