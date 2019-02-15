package com.tripadvisor.utils.plagiarismdetector.pojo;


import com.tripadvisor.utils.plagiarismdetector.Constants;

/**
 * User input information base file name, comparision file name, synonyms file name tuple
 * size(default 3)
 */

// We could use lombok for getter and setter

public class UserInputInfo {

    private String synonymsFileName;
    private String baseFileName;

    private String comparisionFileName;

    private int tupleSize = Constants.DEFAULT_TUPLE_SIZE;


    public String getSynonymsFileName() {
        return synonymsFileName;
    }

    public void setSynonymsFileName(String synonymsFileName) {
        this.synonymsFileName = synonymsFileName;
    }

    public String getBaseFileName() {
        return baseFileName;
    }

    public void setBaseFileName(String baseFileName) {
        this.baseFileName = baseFileName;
    }

    public String getComparisionFileName() {
        return comparisionFileName;
    }

    public void setComparisionFileName(String comparisionFileName) {
        this.comparisionFileName = comparisionFileName;
    }

    public int getTupleSize() {
        return tupleSize;
    }

    public void setTupleSize(int tupleSize) {
        this.tupleSize = tupleSize;
    }
}
