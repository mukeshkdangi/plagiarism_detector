package com.tripadvisor.utils.plagiarismdetector.services;

import com.tripadvisor.utils.plagiarismdetector.Constants;
import com.tripadvisor.utils.plagiarismdetector.customexception.ApplicationException;
import com.tripadvisor.utils.plagiarismdetector.customexception.Error;
import com.tripadvisor.utils.plagiarismdetector.customexception.ErrorCodes;
import com.tripadvisor.utils.plagiarismdetector.pojo.NTuple;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TupleService {

    private static TupleService tupleServiceInstance;

    private TupleService() {
    }

    public static TupleService getTupleServiceInstance() {
        return Objects.isNull(tupleServiceInstance) ? new TupleService() : tupleServiceInstance;
    }


    /**
     * @param fileName  : File for which we need to generate tuples
     * @param tupleSize : tuple size
     * @return : List of all possible tuples in file for a input tuple size
     */

    public List<NTuple> generateNTuple(String fileName, int tupleSize) throws ApplicationException {

        List<NTuple> tuples = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(line -> {
                tuples.addAll(getTuplesForLine(line.toLowerCase(), tupleSize));
            });
        } catch (IOException e) {
            Error error = new Error(ErrorCodes.INVALID_FILE_INPUT.getErrorCode(), ErrorCodes.INVALID_FILE_INPUT.getErrorDescription());
            throw new ApplicationException(error);
        }
        return tuples;
    }

    /**
     * Returns synonyms for each word in line. This function handles words if they even appears in
     * multiple Synonym lines
     *
     * @param fileName : File which contains Synonym lines
     * @return : key : word, value: set of Synonyms of key
     */
    public Map<String, Set<String>> generateSynonyms(String fileName) throws ApplicationException {
        Map<String, Set<String>> synonyMap = new HashMap<>();

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

            stream.forEach(line -> {
                String[] words = line.toLowerCase().split(Constants.WORD_SEPRATOR);
                Set<String> synonymSet = Arrays.stream(words).collect(Collectors.toSet());
                Set<String> newSynonyms = synonymSet.stream().map(String::new).collect(Collectors.toSet());

                for (Iterator<String> synSetItr = synonymSet.iterator(); synSetItr.hasNext(); ) {
                    String word = synSetItr.next();
                    Set<String> existingSynonyms = synonyMap.get(word);
                    if (Objects.nonNull(existingSynonyms) && !existingSynonyms.isEmpty()) {
                        newSynonyms.addAll(existingSynonyms);
                    }
                    synonyMap.put(word, newSynonyms);

                }


            });
        } catch (IOException e) {
            Error error = new Error(ErrorCodes.INVALID_FILE_INPUT.getErrorCode(), ErrorCodes.INVALID_FILE_INPUT.getErrorDescription());
            throw new ApplicationException(error);
        }
        return synonyMap;
    }

    /**
     * Note : Assuming file has alpha numeric entries. no  [.,?!;:.()[]{}] etc otherwise we have to
     * add one more line to replace invalid character "jog," and "jog"
     *
     * In Case of STOP Words at the end of word, we need to replace it. Assuming we have valid words
     * without stop-words.
     *
     * @param line : line in file
     * @return List of Tuple with requested tuple size
     */

    private List<NTuple> getTuplesForLine(String line, int tupleSize) {

        List<NTuple> tuples = new ArrayList<>();

        String[] totalWordsInLine = line.split(Constants.WORD_SEPRATOR);
        if (totalWordsInLine.length < tupleSize) return tuples;

        //generate all possible N-grams of length tupleSize
        for (int wordIdx = 0; wordIdx < totalWordsInLine.length - tupleSize + 1; wordIdx++) {
            NTuple nTuple = new NTuple();
            for (int tupleStartindx = wordIdx; tupleStartindx < wordIdx + tupleSize; tupleStartindx++)
                nTuple.addWordInNTuple(totalWordsInLine[tupleStartindx]);

            tuples.add(nTuple);
        }
        return tuples;
    }
}
