package com.hicola.exception;

import com.hicola.model.enums.ErrorCodeEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * RuntimeException一 定是程序员的错误，所以一般自定义这样的异常
 * @author bai.yang email:willis.bai@outlook.com
 * @date 2021/7/22
 * @
 */
public class ExcelBatchException extends RuntimeException {

    @Getter
    @Setter
    private Integer errorCode;

    public ExcelBatchException() {
    }

    public ExcelBatchException(String message) {
        super(message);
    }

    public ExcelBatchException(Integer errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ExcelBatchException(Integer errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public ExcelBatchException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getErrorMsg());
        this.errorCode = errorCodeEnum.getErrorCode();
    }
}
