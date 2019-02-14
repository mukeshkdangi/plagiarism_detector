package com.tripadvisor.utils.plagiarismdetector.CustomException;

public enum ErrorCodes {

    INSUFFICIENT_ARGS_CODE(100, "Insufficient input. Please enter \n\t\t java <PlagiarismDetector> <file_1_path> <file_2_path> <synonym_file_path> [optional]<tuple_size>"),
    INVALID_FILE_INPUT(101, "Invalid file path"),
    EMPTY_FILE_CONTENT(102, "File content is empty or invalid");

    private final int errorCode;
    private final String errorDescription;

    private ErrorCodes(int code, String errorDescription) {
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
