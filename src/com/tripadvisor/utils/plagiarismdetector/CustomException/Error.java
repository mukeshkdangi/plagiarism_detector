package com.tripadvisor.utils.plagiarismdetector.CustomException;

public class Error {

    private final int errorCode;
    private final String errorDescription;

    public Error(int code, String errorDescription) {
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