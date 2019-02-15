package com.tripadvisor.utils.plagiarismdetector.customexception;

public class ApplicationException extends Exception {

    private Error error;

    public ApplicationException(Error error) {
        this.error = error;
    }

    public String getErrorMessage() {
        return error.getErrorDescription();
    }

    public Integer getErrorCode() {
        return error.getErrorCode();
    }


}
