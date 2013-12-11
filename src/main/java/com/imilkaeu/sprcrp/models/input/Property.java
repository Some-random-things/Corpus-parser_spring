package com.imilkaeu.sprcrp.models.input;

import java.io.Serializable;
import java.util.List;

/**
 * Created by imilka on 12.12.13.
 */
public class Property implements Serializable {
    private String dbfield;
    private String text;
    private List<PropertyValue> values;
    private List<String> select;

    public List<PropertyValue> getValues() {
        return values;
    }

    public void setValues(List<PropertyValue> values) {
        this.values = values;
    }

    public String getDbfield() {
        return dbfield;
    }

    public void setDbfield(String dbfield) {
        this.dbfield = dbfield;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getSelect() {
        return select;
    }

    public void setSelect(List<String> select) {
        this.select = select;
    }
}
