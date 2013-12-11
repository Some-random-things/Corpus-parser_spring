package com.imilkaeu.sprcrp.models.input;

import java.io.Serializable;
import java.util.List;

/**
 * Created by imilka on 10.12.13.
 */
public class BigramInputData implements Serializable {
    private List<PartOfSpeech> main;
    private List<PartOfSpeech> dep;

    public List<PartOfSpeech> getMain() {
        return main;
    }

    public void setMain(List<PartOfSpeech> main) {
        this.main = main;
    }

    public List<PartOfSpeech> getDep() {
        return dep;
    }

    public void setDep(List<PartOfSpeech> dep) {
        this.dep = dep;
    }
}
