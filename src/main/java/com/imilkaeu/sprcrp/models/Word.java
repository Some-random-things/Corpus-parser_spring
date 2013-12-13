package com.imilkaeu.sprcrp.models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by imilka on 13.12.13.
 */
@Entity
@Table(name = "words")
public class Word implements Serializable {
    @Id
    @GeneratedValue
    private long id;
    private int internalId;
    private int domid;
    private String lemma;
    private String link;
    private String word;
    private String partOfSpeech;
    private String count;
    private String gender;
    private String animacy;
    private String aspect;
    private String mood;
    private String tense;
    private String person;
    private String form;
    private String comparison;
    private String voice;
    private String pledge;
    private String additional;
    private int sentenceId;

    @Column(name = "case")
    private String wordCase;

    @Column(name = "short")
    private String wordShort;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getInternalId() {
        return internalId;
    }

    public void setInternalId(int internalId) {
        this.internalId = internalId;
    }

    public int getDomid() {
        return domid;
    }

    public void setDomid(int domid) {
        this.domid = domid;
    }

    public String getLemma() {
        return lemma;
    }

    public void setLemma(String lemma) {
        this.lemma = lemma;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAnimacy() {
        return animacy;
    }

    public void setAnimacy(String animacy) {
        this.animacy = animacy;
    }

    public String getAspect() {
        return aspect;
    }

    public void setAspect(String aspect) {
        this.aspect = aspect;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getTense() {
        return tense;
    }

    public void setTense(String tense) {
        this.tense = tense;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getComparison() {
        return comparison;
    }

    public void setComparison(String comparison) {
        this.comparison = comparison;
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }

    public String getPledge() {
        return pledge;
    }

    public void setPledge(String pledge) {
        this.pledge = pledge;
    }

    public String getAdditional() {
        return additional;
    }

    public void setAdditional(String additional) {
        this.additional = additional;
    }

    public int getSentenceId() {
        return sentenceId;
    }

    public void setSentenceId(int sentenceId) {
        this.sentenceId = sentenceId;
    }

    public String getWordCase() {
        return wordCase;
    }

    public void setWordCase(String wordCase) {
        this.wordCase = wordCase;
    }

    public String getWordShort() {
        return wordShort;
    }

    public void setWordShort(String wordShort) {
        this.wordShort = wordShort;
    }
}
