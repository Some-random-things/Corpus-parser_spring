package com.imilkaeu.sprcrp.models.output;

import java.io.Serializable;

/**
 * Created by imilka on 13.12.13.
 */
public class BigramCombination implements Serializable {
    private int count;
    private OutputPartOfSpeech main;
    private OutputPartOfSpeech dep;

    public BigramCombination(int count, OutputPartOfSpeech main, OutputPartOfSpeech dep) {
        this.count = count;
        this.main = main;
        this.dep = dep;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public OutputPartOfSpeech getMain() {
        return main;
    }

    public void setMain(OutputPartOfSpeech main) {
        this.main = main;
    }

    public OutputPartOfSpeech getDep() {
        return dep;
    }

    public void setDep(OutputPartOfSpeech dep) {
        this.dep = dep;
    }
}
