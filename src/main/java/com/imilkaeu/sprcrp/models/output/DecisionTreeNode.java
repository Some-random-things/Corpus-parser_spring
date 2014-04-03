package com.imilkaeu.sprcrp.models.output;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: imilka
 * Date: 02.04.14
 * Time: 21:03
 */
public class DecisionTreeNode implements Serializable {
    private String name;
    private List<DecisionTreeNode> children = new ArrayList<DecisionTreeNode>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DecisionTreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<DecisionTreeNode> children) {
        this.children = children;
    }

    public void addChild(DecisionTreeNode child) {
        children.add(child);
    }
}
