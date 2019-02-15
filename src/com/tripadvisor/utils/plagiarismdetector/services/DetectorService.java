package com.tripadvisor.utils.plagiarismdetector.services;

import com.tripadvisor.utils.plagiarismdetector.Constants;
import com.tripadvisor.utils.plagiarismdetector.CustomException.ApplicationException;
import com.tripadvisor.utils.plagiarismdetector.CustomException.Error;
import com.tripadvisor.utils.plagiarismdetector.CustomException.ErrorCodes;
import com.tripadvisor.utils.plagiarismdetector.pojo.NTuple;
import com.tripadvisor.utils.plagiarismdetector.pojo.UserInputInfo;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class DetectorService {
    private String[] userInput;

    private List<NTuple> baseFileNTuples;
    private List<NTuple> comparisonFileNTuples;
    private Map<String, Set<String>> synonMap;
    private UserInputInfo userInputInfo;

    /**
     * Validates user inputs and throw appropriate exception in case of failure
     */

    public void validateUserInput() throws ApplicationException {
        if (userInput.length < Constants.MIN_NUM_USER_INPUT) {
            Error error = new Error(ErrorCodes.INSUFFICIENT_ARGS_CODE.getErrorCode(), ErrorCodes.INSUFFICIENT_ARGS_CODE.getErrorDescription());
            throw new ApplicationException(error);
        }
    }

    /**
     * return false if 1.  no synonym found for any of the word or synonym list 2. intersection of
     * two set did not result in a single synonym matched
     *
     * @param baseNTuple        : N-Tuples from base File(file One)
     * @param comparisionNTuple : N-Tuples from comparision file (file two)
     * @return : True/False if 2 N-tuple  are same after replacing unmatched words with synonyms
     */
    private boolean isNTupleContentPlagiarised(NTuple baseNTuple, NTuple comparisionNTuple) {

        for (int tupleIndex = 0; tupleIndex < baseNTuple.getTupleSize(); tupleIndex++) {
            String baseWord = baseNTuple.getWord(tupleIndex);
            String comparisionWord = comparisionNTuple.getWord(tupleIndex);

            if (!baseWord.equals(comparisionWord)) {
                Set<String> baseWordSyns = synonMap.get(baseWord);
                Set<String> comparisionWordSyns = synonMap.get(comparisionWord);
                if (Objects.isNull(baseWordSyns) || Objects.isNull(comparisionWordSyns))
                    return false;
                baseWordSyns.retainAll(comparisionWordSyns);
                if (baseWordSyns.size() <= 0) return false;
            }
        }
        return true;
    }

    /**
     * 1. Initialise all tuples in specified length and genrate Synonyms 2. Throw exception if any
     * of the files is empty
     */

    public void generateNTuples() throws ApplicationException {
        TupleService tupleService = TupleService.getTupleServiceInstance();

        baseFileNTuples = tupleService.generateNTuple(userInputInfo.getBaseFileName(), userInputInfo.getTupleSize());
        comparisonFileNTuples = tupleService.generateNTuple(userInputInfo.getComparisionFileName(), userInputInfo.getTupleSize());
        synonMap = tupleService.generateSynonyms(userInputInfo.getSynonymsFileName());
        System.out.println("synonMap" + synonMap);

        if (baseFileNTuples.size() == 0 || comparisonFileNTuples.size() == 0 || synonMap.isEmpty()) {
            Error error = new Error(ErrorCodes.EMPTY_FILE_CONTENT.getErrorCode(), ErrorCodes.EMPTY_FILE_CONTENT.getErrorDescription());
            throw new ApplicationException(error);
        }
    }

    /**
     * 1. Check if N-Tuple group of base file and comparision file are similar or not 2. Take
     * consideration of Synonym Map
     *
     * plagiarisedContentCount :  gets incremented by one of 2 N-tuple  are same after replacing
     * unmatched words with synonyms
     */

    public void analyseContentForPlagiarism() {
        int plagiarisedContentCount = 0;
        for (int baseTupleIdx = 0; baseTupleIdx < baseFileNTuples.size(); baseTupleIdx++) {
            for (int comparisonTupleIndex = 0; comparisonTupleIndex < comparisonFileNTuples.size(); comparisonTupleIndex++) {
                if (isNTupleContentPlagiarised(baseFileNTuples.get(baseTupleIdx), comparisonFileNTuples.get(comparisonTupleIndex))) {
                    plagiarisedContentCount++;
                    break;
                }
            }
        }
        double contentSimilarityPercentage = (plagiarisedContentCount * 100) / (baseFileNTuples.size());
        System.out.println("plagiarism content  % " + contentSimilarityPercentage + " baseFileNTuples.size()" + baseFileNTuples.size());
    }

    /**
     * Check if user input length is valid
     *
     * @return default if 1. invalid or negative input  or 2. entered input tuple size
     */

    private int getValidTupleSize() {
        int tupleSize = Constants.DEFAULT_TUPLE_SIZE;
        try {
            tupleSize = userInput.length > Constants.MIN_NUM_USER_INPUT ? Integer.parseInt(userInput[3]) : tupleSize;
        } catch (NumberFormatException e) {
            System.out.println(Constants.TUPLE_NUMBER_ERROR);
        }
        return tupleSize > 0 ? tupleSize : Constants.DEFAULT_TUPLE_SIZE;
    }

    /**
     * @return : User Input Info Object
     */
    public void createUserInputInfo() {
        userInputInfo = new UserInputInfo();
        userInputInfo.setBaseFileName(userInput[0]);
        userInputInfo.setComparisionFileName(userInput[1]);
        userInputInfo.setSynonymsFileName(userInput[2]);
        userInputInfo.setTupleSize(getValidTupleSize());
    }

    public void setUserInputArgs(String[] userInput) {
        this.userInput = userInput;
    }

    public DetectorService(String[] userInput) {
        this.setUserInputArgs(userInput);
    }
}


