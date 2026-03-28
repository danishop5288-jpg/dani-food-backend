package com.dani.danifood.result;

import com.dani.danifood.constant.MessageConstant;
import com.dani.danifood.constant.StatusCode;
import lombok.Data;

import java.io.Serializable;
@Data
public class Result<T> implements Serializable {
    private Integer code; //这是内部status code，不是http code
    private String msg; //message
    private T data; //data(token)

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.code = StatusCode.SUCCESS;
        result.msg = MessageConstant.SUCCESS;
        result.data = data;
        return result;
    }
    public static <T> Result<T> error(String msg) {
        Result<T> result = new Result<>();
        result.code = StatusCode.ERROR;
        result.msg = msg;
        return result;
    }


}
