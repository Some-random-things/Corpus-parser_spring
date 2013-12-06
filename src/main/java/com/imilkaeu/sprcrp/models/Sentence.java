package com.imilkaeu.sprcrp.models;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: imilka
 * Date: 06.12.13
 * Time: 10:57
 */

@Entity
@Table(name = "sentences")
public class Sentence {

    @Id
    @GeneratedValue
    long id;

    @Basic
    int internalId;

    @Basic
    String sentence;

    @Basic
    int text_id;

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

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public int getText_id() {
        return text_id;
    }

    public void setText_id(int text_id) {
        this.text_id = text_id;
    }
}
