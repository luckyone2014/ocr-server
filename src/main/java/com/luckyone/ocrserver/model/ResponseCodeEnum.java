package com.luckyone.ocrserver.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
//@ApiModel("响应码信息类")
public enum ResponseCodeEnum {

    OK(200, "OK", "OK"),
    Unauthorized(10001, "认证不通过", "Unauthorized"),
    Forbidden(10003, "没有权限", "Forbidden"),
    Exception(20001, "", ""),
    ParamValidException(20003, "参数校验异常", "Param Valid Exception"),
    BusinessException(20004, "系统出现异常，请稍后再试！", "Business Exception"),
    HttpRequestMethodNotSupportedException(20005, "当前http调用方法不支持,以下是支持的方法:%s", "HttpRequest Method Not Supported Exception,Supported Methods:%s"),
    EMPTY(50002, "查询结果为空", "NO RESULT"),
    EVENT_PUSH_DATA_EXIST(50003,"该通报事件已经存在","event push data already exist");

    private int code;
    private String message;
    private String message_en;
}
