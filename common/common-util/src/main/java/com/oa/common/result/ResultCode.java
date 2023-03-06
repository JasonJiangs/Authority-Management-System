package com.oa.common.result;

import lombok.Data;

/**
 * @Author: Jason
 * @Date: 2023/3/6 13:27 13 27
 */

/**
 * encapsulated data, return data with standard code and message
 * @param <T>
 */
@Data
public class ResultCode<T> {

    private Integer code;
    private String message;
    private T data;

    // singleton
    private ResultCode() {
    }

    public static <T> ResultCode<T> build(T body, ResultCodeEnum resultCodeEnum) {
        ResultCode<T> result = new ResultCode<>();
        // encapsulated data
        if (body != null) {
            result.setData(body);
        }
        // code
        result.setCode(resultCodeEnum.getCode());
        // message
        result.setMessage(resultCodeEnum.getMessage());
        return result;
    }

    public static <T> ResultCode<T> succeed() {
        return build(null, ResultCodeEnum.SUCCESS);
    }

    public static <T> ResultCode<T> succeed(T data) {
        return build(data, ResultCodeEnum.SUCCESS);
    }

    public static <T> ResultCode<T> fail() {
        return build(null, ResultCodeEnum.FAIL);
    }

    public static <T> ResultCode<T> fail(T data) {
        return build(data, ResultCodeEnum.FAIL);
    }

    // flexible define message and code information
    public ResultCode<T> message(String msg){
        this.setMessage(msg);
        return this;
    }

    public ResultCode<T> code(Integer code){
        this.setCode(code);
        return this;
    }
}