package com.luckyone.ocrserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseModel<T> implements Serializable {

    private static final long serialVersionUID = 4525431233220956770L;
    private int code;
    private String message;
    private T data;

    public ResponseModel responseCode(ResponseCodeEnum responseCodeEnum) {
        setCode(responseCodeEnum.getCode());
        setMessage(responseCodeEnum.getMessage());
        return this;
    }
}
