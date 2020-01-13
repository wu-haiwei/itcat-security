package com.itcat.common.beans.resp;

import com.itcat.common.constants.RespCode;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BaseResponse<T> {

    private int code;

    private String message;

    private T data;

    public BaseResponse<T> code(RespCode code){
        this.code = code.getCode();
        this.message = code.getMsg();
        return this;
    }
}
