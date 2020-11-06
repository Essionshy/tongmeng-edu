package com.tingyu.tongmeng.edu.service.edu.exception;


import com.tingyu.tongmeng.edu.commons.R;
import com.tingyu.tongmeng.edu.commons.ResultException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    /**
     * 处理自定义异常 ResultException
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = {ResultException.class})
    public R handleResultException(ResultException e) {
        return  R.error().code(e.getCode()).message(e.getMessage());
    }


    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public R handleValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        Map<String, String> errorMap = new HashMap<>();

        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        fieldErrors.forEach(fieldError -> {
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        return  R.error().code(4000).message( "参数校验失败").data("errorMap",errorMap);
    }

}
