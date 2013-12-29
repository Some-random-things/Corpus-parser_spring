package com.imilkaeu.sprcrp.models.output;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: imilka
 * Date: 13.12.13
 * Time: 11:40
 */
public class OutputSentenceInfo implements Serializable {
    private int id;
    private Bigram bigram;
    private String sentence;

    public OutputSentenceInfo(int id, Bigram bigram, String sentence) {
        this.id = id;
        this.bigram = bigram;
        this.sentence = sentence;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bigram getBigram() {
        return bigram;
    }

    public void setBigram(Bigram bigram) {
        this.bigram = bigram;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }
}
