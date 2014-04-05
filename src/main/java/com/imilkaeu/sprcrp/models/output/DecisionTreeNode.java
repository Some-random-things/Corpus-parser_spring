package com.imilkaeu.sprcrp.models.output;

import com.imilkaeu.sprcrp.common.DecisionTreeBuilder;

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
    private int left, right;
    private List<DecisionTreeNode> children;

    public DecisionTreeNode(String n, int l, int r) {
        name = n; left = l; right = r;
    }

    public DecisionTreeNode(DecisionTreeBuilder.Node node) {
        name = node.getName(); right = node.getRight(); left = node.getLeft();
    }

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
        if(children == null) children = new ArrayList<DecisionTreeNode>();
        children.add(child);
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }
}
