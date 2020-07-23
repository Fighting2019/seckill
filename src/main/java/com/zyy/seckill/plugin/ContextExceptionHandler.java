package com.zyy.seckill.plugin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ContextExceptionHandler {

    @ExceptionHandler(Exception.class)
    public void exceptionHandler(Exception exception){
        exception.printStackTrace();
        log.error(String.format("异常信息：%s",exception.getMessage()));
    }
}
