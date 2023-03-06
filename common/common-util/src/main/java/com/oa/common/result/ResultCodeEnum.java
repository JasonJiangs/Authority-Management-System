package com.oa.common.result;

import lombok.Getter;

/**
 * @Author: Jason
 * @Date: 2023/3/6 13:19 13 19
 */

@Getter
public enum ResultCodeEnum {

    SUCCESS(200,"Succeed"),
    FAIL(201, "Fail"),
    SERVICE_ERROR(2012, "Service failed"),
    DATA_ERROR(204, "Data failed"),
    LOGIN_AUTH(208, "Not login"),
    PERMISSION(209, "Not authorized");

    private Integer code;
    private String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}