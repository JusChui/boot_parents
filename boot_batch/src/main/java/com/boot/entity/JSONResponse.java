package com.boot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @File: JSONResponse.java
 * @Author: JusChui
 * @CreateTime: 2022-07-10 17:46:00
 * @Description:
 */
@Data
@AllArgsConstructor
public class JSONResponse {

    private Integer status;
    private String message;

    private Map<String, Object> bean;
    private List<Map<String, Object>> beans;
    private Object data;

    public static JSONResponse getSuccessResponse() {
        return new JSONResponse(200, "SUCCESS", null, null, null);
    }

    public static JSONResponse getSuccessResponse(String message, Object data) {
        return new JSONResponse(200, message, null, null, data);
    }

    public static JSONResponse getSuccessResponse(Object data) {
        return new JSONResponse(200, "SUCCESS", null, null, data);
    }

    public static JSONResponse getFailureResponse() {
        return new JSONResponse(400, "FAILURE", null, null, null);
    }

    public static JSONResponse getFailureResponse(String message) {
        return new JSONResponse(400, message, null, null, null);
    }
}
