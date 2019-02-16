package com.tripadvisor.utils.plagiarismdetector.customexception;

import com.tripadvisor.utils.plagiarismdetector.Constants;

public enum ErrorCodes {

    INSUFFICIENT_ARGS_CODE(100, Constants.INSUFFICIENT_ARGS_DES),
    INVALID_FILE_INPUT(101, Constants.INVALID_FILE_INPUT_DES),
    EMPTY_FILE_CONTENT(102, Constants.EMPTY_FILE_CONTENT_DES);
    private final int errorCode;
    private final String errorDescription;

    ErrorCodes(int code, String errorDescription) {
        this.errorCode = code;
        this.errorDescription = errorDescription;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public String toString() {
        return errorCode + " : " + errorDescription;
    }
}
