package com.hicola.model;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.Data;

/**
 * @author baiyang
 * @date 2021/7/19
 */
@Data
public class ResponseInfo<T> {
    public static final String SUCCESS_CODE = "200";
    public static final String SUCCESS_MSG = "success";
    public static final String ERROR_CODE = "500";
    public static final String ERROR_MSG = "error";

    private String errorCode;
    private String errorMsg;
    private T data;

    public static ResponseInfo returnSuccessMsg() {
        ResponseInfo res = new ResponseInfo();
        res.setErrorCode(SUCCESS_CODE);
        res.setErrorMsg(SUCCESS_MSG);
        return res;
    }

    public static ResponseInfo returnErrorMsg() {
        return returnErrorMsg(null, null);
    }

    public static ResponseInfo returnErrorMsg(String errorCode, String errorMsg) {
        ResponseInfo res = new ResponseInfo();
        if (StringUtils.isBlank(errorCode)) {
            res.setErrorCode(ERROR_CODE);
        }
        if (StringUtils.isBlank(errorMsg)) {
            res.setErrorMsg(ERROR_MSG);
        }
        return res;
    }
}
