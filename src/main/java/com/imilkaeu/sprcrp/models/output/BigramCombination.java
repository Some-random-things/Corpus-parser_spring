package com.imilkaeu.sprcrp.models.output;

import java.io.Serializable;

/**
 * Created by imilka on 13.12.13.
 */
public class BigramCombination implements Serializable {
    private int count;
    private PartOfSpeech main;
    private PartOfSpeech dep;
    private int direction;

    public int getCount() {
        return count;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public PartOfSpeech getMain() {
        return main;
    }

    public void setMain(PartOfSpeech main) {
        this.main = main;
    }

    public PartOfSpeech getDep() {
        return dep;
    }

    public void setDep(PartOfSpeech dep) {
        this.dep = dep;
    }
}
