package com.boot.util;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName JSONResponse.java
 * @Author Xuhui
 * @Date 2022/8/1 18:32
 * @Description
 */
@Data
@AllArgsConstructor
public class JSONResponse implements Serializable {

    private Integer status;

    private String message;

    private Object data;
}
