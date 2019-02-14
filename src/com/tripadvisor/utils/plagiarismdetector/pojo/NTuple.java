package com.tripadvisor.utils.plagiarismdetector.pojo;

import java.util.ArrayList;
import java.util.List;

public class NTuple {
    List<String> wordsInNTuple = new ArrayList<>();

    public void addWordInNTuple(String word) {
        this.wordsInNTuple.add(word);
    }

    public String getWord(int wordPosition) {
        return this.wordsInNTuple.get(wordPosition);
    }

    public List<String> getWordsInTuple() {
        return wordsInNTuple;
    }

    public int getTupleSize() {
        return this.wordsInNTuple.size();
    }
}
