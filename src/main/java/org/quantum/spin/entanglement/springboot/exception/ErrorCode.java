package org.quantum.spin.entanglement.springboot.exception;

import lombok.Getter;

public enum ErrorCode {

    NOT_NULL("ERR_CODE_0001","필수값이 누락되었습니다")
    , NOT_EMPTY("ERR_CODE_0002","필수값이 누락되었습니다")
    , MIN_VALUE("ERR_CODE_0003", "최소값보다 커야 합니다.")
    ;

    @Getter
    private String code;

    @Getter
    private String description;

    ErrorCode(String code, String description) {
        this.code = code;
        this.description = description;
    }
}