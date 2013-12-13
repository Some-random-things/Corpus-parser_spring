package com.imilkaeu.sprcrp.models.output;

import java.io.Serializable;

/**
 * Created by imilka on 13.12.13.
 */
public class PartOfSpeech implements Serializable {
    private String partOfSpeech;
    private String[] properties;

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public String[] getProperties() {
        return properties;
    }

    public void setProperties(String[] properties) {
        this.properties = properties;
    }
}
