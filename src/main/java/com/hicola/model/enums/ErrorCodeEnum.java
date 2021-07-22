package com.hicola.model.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * @author bai.yang email:willis.bai@outlook.com
 * @date 2021/7/22
 * @
 */
public enum ErrorCodeEnum {
    PARAMETER_ERROR(2001, "参数错误"),
    FIELD_VALUE_NULL_ERROR(2002, "字段为空"),
    RECORD_NULL_ERROR(2002, "记录为空");
    @Setter
    @Getter
    private Integer errorCode;
    @Setter
    @Getter
    private String errorMsg;

    ErrorCodeEnum(Integer errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
}
