package com.imilkaeu.sprcrp.models.input;

import java.io.Serializable;

/**
 * Created by imilka on 11.12.13.
 */
public class PropertyValue implements Serializable {
    private String ident;
    private String text;
    private boolean selected = false;

    public String getIdent() {
        return ident;
    }

    public void setIdent(String ident) {
        this.ident = ident;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
