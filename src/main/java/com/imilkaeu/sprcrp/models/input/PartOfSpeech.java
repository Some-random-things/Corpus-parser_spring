package com.imilkaeu.sprcrp.models.input;

import java.io.Serializable;
import java.util.List;

/**
 * Created by imilka on 10.12.13.
 */
public class PartOfSpeech implements Serializable {
    private String partOfSpeech;
    private String title;
    private boolean selected;
    private List<Property> content;

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Property> getContent() {
        return content;
    }

    public void setContent(List<Property> content) {
        this.content = content;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
