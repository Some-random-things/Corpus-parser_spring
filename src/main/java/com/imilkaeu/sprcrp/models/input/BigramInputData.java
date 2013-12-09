package com.imilkaeu.sprcrp.models.input;

import java.io.Serializable;
import java.util.List;

/**
 * Created by imilka on 10.12.13.
 */
public class BigramInputData implements Serializable {
    private List<PartOfSpeechInputData> main;
    private List<PartOfSpeechInputData> dep;

    public List<PartOfSpeechInputData> getMain() {
        return main;
    }

    public void setMain(List<PartOfSpeechInputData> main) {
        this.main = main;
    }

    public List<PartOfSpeechInputData> getDep() {
        return dep;
    }

    public void setDep(List<PartOfSpeechInputData> dep) {
        this.dep = dep;
    }
}
