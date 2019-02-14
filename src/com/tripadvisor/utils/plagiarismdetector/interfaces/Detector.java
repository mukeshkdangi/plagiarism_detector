package com.tripadvisor.utils.plagiarismdetector.interfaces;

import com.tripadvisor.utils.plagiarismdetector.CustomException.ApplicationException;

public abstract class Detector {
    abstract public void preProcess() throws ApplicationException;

    abstract public void process() throws ApplicationException;

    abstract public void postProcess() throws ApplicationException;
}
