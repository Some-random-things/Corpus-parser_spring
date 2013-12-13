package com.imilkaeu.sprcrp.models.output;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by imilka on 13.12.13.
 */
public class BigramCombination {
    private int count;
    private List<String> properties = new ArrayList<String>();

    public BigramCombination(int count, List<String> properties) {
        this.count = count;
        this.properties = properties;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<String> getProperties() {
        return properties;
    }

    public void setProperties(List<String> properties) {
        this.properties = properties;
    }
}
