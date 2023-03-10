package com.service.config.exception;

import com.oa.common.result.ResultCodeEnum;
import lombok.Data;


/**
 * @Author: Jason
 * @Date: 2023/3/9 19:31 19 31
 */

@Data
public class TypeException extends RuntimeException {

    private Integer code;
    private String message;

    public TypeException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
    public TypeException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }

    @Override
    public String toString() {
        return "TypeException{" +
                "code=" + code +
                ", message=" + this.getMessage() +
                '}';
    }



}