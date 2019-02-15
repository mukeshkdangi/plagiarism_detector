package com.tripadvisor.utils.plagiarismdetector;

public class Constants {
    public static final int MIN_NUM_USER_INPUT = 3;
    public static int DEFAULT_TUPLE_SIZE = 3;

    public static final String TUPLE_NUMBER_ERROR = "WARN :Invalid tuple size. taking default tupe size 3";
    public static final String ERROR_MESSAGE_HEADING = "Error Code --------Error Message ";
    public static final String WORD_SEPRATOR = " ";

    public static final String INSUFFICIENT_ARGS_DES = "Insufficient input(s). Please enter according to following command \nCompile : mvn clean compile && mvn package  \n" +
            "Run     : java -cp <path_to_jar_generated_from_step_1> com.tripadvisor.utils.plagiarismdetector.PlagiarismDetector <Synonym_file> <file_1_path> <file_2_path> [tupe_size]";
    public static final String INVALID_FILE_INPUT_DES = "Invalid file name/path";
    public static final String EMPTY_FILE_CONTENT_DES = "File content is empty or requested tuple size is greater than file entry";

    public static final String DEFAULT_TUPLE_SIZE_MSG = "***Taking Default tuple size ";
    public static final String EXTRA_INPUT_MSG = "extra inputs will be Ignoring inputs";

}
