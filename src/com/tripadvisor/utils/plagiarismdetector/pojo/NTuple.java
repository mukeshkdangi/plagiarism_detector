package com.tripadvisor.utils.plagiarismdetector.pojo;

import java.util.ArrayList;
import java.util.List;

public class NTuple {
    List<String> wordsInNTuple = new ArrayList<>();

    public List<String> getWordsInTuple() {
        return this.wordsInNTuple;
    }

    public void addWordInNTuple(String word) {
        getWordsInTuple().add(word);
    }

    public String getWordAtIndex(int wordPosition) {
        return this.wordsInNTuple.get(wordPosition);
    }

    public int getTupleSize() {
        return this.wordsInNTuple.size();
    }
}
