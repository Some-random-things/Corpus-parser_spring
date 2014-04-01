package com.imilkaeu.sprcrp.models.output;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: imilka
 * Date: 01.04.14
 * Time: 15:54
 */
public class BigramCombinationInfo implements Serializable {
    private String hash;
    private List<BigramCombination> combinations;

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public List<BigramCombination> getCombinations() {
        return combinations;
    }

    public void setCombinations(List<BigramCombination> combinations) {
        this.combinations = combinations;
    }
}
