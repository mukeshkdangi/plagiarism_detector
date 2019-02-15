package com.tripadvisor.utils.plagiarismdetector;

import com.tripadvisor.utils.plagiarismdetector.CustomException.ApplicationException;
import com.tripadvisor.utils.plagiarismdetector.interfaces.Detector;
import com.tripadvisor.utils.plagiarismdetector.services.DetectorService;

public class PlagiarismDetector extends Detector {
    String[] userInput;
    DetectorService detectorService;


    public static void main(String args[]) {
        try {
            PlagiarismDetector plagiarismDetector = new PlagiarismDetector(args);
            plagiarismDetector.preProcess();
            plagiarismDetector.process();
            plagiarismDetector.postProcess();

        } catch (ApplicationException appException) {
            System.out.println(Constants.ERROR_MESSAGE_HEADING);
            System.out.println(appException.getErrorCode() + " --" + appException.getErrorMessage());
        }
    }

    @Override
    public void preProcess() throws ApplicationException {
        detectorService = new DetectorService(getUserInput());
        detectorService.validateUserInput();
        detectorService.createUserInputInfo();
    }

    @Override
    public void process() throws ApplicationException {
        detectorService.generateNTuples();
    }

    @Override
    public void postProcess() {
        detectorService.analyseContentForPlagiarism();
    }


    public String[] getUserInput() {
        return userInput;
    }

    PlagiarismDetector(String[] input) {
        this.userInput = input;
    }
}
