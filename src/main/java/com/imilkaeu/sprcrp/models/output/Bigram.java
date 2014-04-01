package com.imilkaeu.sprcrp.models.output;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: imilka
 * Date: 28.12.13
 * Time: 22:08
 */
public class Bigram implements Serializable {
    String main;
    String dep;
    int direction;

    public Bigram(String main, String dep, int direction) {
        this.main = main;
        this.dep = dep;
        this.direction = direction;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
