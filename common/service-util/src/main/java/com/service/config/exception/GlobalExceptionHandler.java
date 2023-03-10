package com.service.config.exception;

import com.oa.common.result.ResultCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @Author: Jason
 * @Date: 2023/3/9 19:03 19 03
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultCode exceptionCatcherGlobal(Exception e){
        e.printStackTrace();
        return ResultCode.fail().message("Exception catch!");
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public ResultCode exceptionCatchArithmetic(ArithmeticException e){
        e.printStackTrace();
        return ResultCode.fail().message("Specific exception.");
    }

    @ExceptionHandler(TypeException.class)
    @ResponseBody
    public ResultCode error(TypeException e){
        e.printStackTrace();
        return ResultCode.fail().message(e.getMessage()).code(e.getCode());
    }
}