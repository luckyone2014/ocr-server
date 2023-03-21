package com.luckyone.ocrserver.model;

import java.util.Objects;

public class ResponseModels {

    public static ResponseModel ok() {
        return new ResponseModel().responseCode(ResponseCodeEnum.OK);
    }

    public static <T> ResponseModel<T> ok(T data) {
        ResponseModel result;
        if (Objects.isNull(data)) {
            result = new ResponseModel().responseCode(ResponseCodeEnum.EMPTY);
        } else {
            result = ok();
            result.setData(data);
        }
        return result;
    }

    public static <T> ResponseModel<T> exception(ResponseCodeEnum responseCodeEnum) {
        return new ResponseModel().responseCode(responseCodeEnum);
    }

    public static <T> ResponseModel<T> exception(String message) {
        ResponseModel responseModel = new ResponseModel();
        responseModel.setCode(ResponseCodeEnum.Exception.getCode());
        responseModel.setMessage(message);
        return responseModel;
    }

}
