package com.imilkaeu.sprcrp.models.input;

import java.io.Serializable;
import java.util.List;

/**
 * Created by imilka on 10.12.13.
 */
public class BigramInputData implements Serializable {
    private List<InputPartOfSpeech> main;
    private List<InputPartOfSpeech> dep;
    private boolean rawRequest = false;

    public boolean isRawRequest() {
        return rawRequest;
    }

    public void setRawRequest(boolean rawRequest) {
        this.rawRequest = rawRequest;
    }

    public List<InputPartOfSpeech> getMain() {
        return main;
    }

    public void setMain(List<InputPartOfSpeech> main) {
        this.main = main;
    }

    public List<InputPartOfSpeech> getDep() {
        return dep;
    }

    public void setDep(List<InputPartOfSpeech> dep) {
        this.dep = dep;
    }
}
