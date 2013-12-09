package com.imilkaeu.sprcrp.models.input;

import java.io.Serializable;
import java.util.List;

/**
 * Created by imilka on 10.12.13.
 */
public class PartOfSpeechInputData implements Serializable {
    private String partOfSpeech;
    private List<String> selected;

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public List<String> getSelected() {
        return selected;
    }

    public void setSelected(List<String> selected) {
        this.selected = selected;
    }
}
