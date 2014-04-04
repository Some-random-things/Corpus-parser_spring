package com.imilkaeu.sprcrp;

import java.math.BigInteger;
import java.util.*;

/**
 * Created by Pavel on 02.04.14.
 */
public class DecisionTreeBuilder {

    public class Node {
        public ArrayList<Edge> children;
        public String name;

        public int left = 0;
        public int right = 0;

        public Node(String n, int left, int right) {
            this.name = n;
            this.left = left;
            this.right = right;

            children = new ArrayList<Edge>();
        }

        public ArrayList<Edge> getChildren() {
            return children;
        }

        public void setChildren(ArrayList<Edge> children) {
            this.children = children;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

    public class Edge {
        public Node end;
        public String value;

        public Edge(Node e, String v) {
            this.end = e;
            this.value = v;
        }

        public Node getEnd() {
            return end;
        }

        public void setEnd(Node end) {
            this.end = end;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    private Node rootNode = null;
    private List<Map<String, Object>> sourceDataSet;
    private HashMap<String, ArrayList<String>> sourceParams;

    public DecisionTreeBuilder(List<Map<String, Object>> ds) {
        sourceDataSet = ds; sourceParams = new HashMap<String, ArrayList<String>>();
        HashMap<String, ArrayList<String>> paramsDn = new HashMap<String, ArrayList<String>>();

        for(Map<String, Object> sourceData : sourceDataSet) {
            for(Map.Entry<String, Object> e : sourceData.entrySet()) {
                String key = e.getKey(); Object value = e.getValue();
                if(!key.equals("direction")
                        && !key.equals("partOfSpeech1")
                        && !key.equals("partOfSpeech2")) {
                    if(!paramsDn.containsKey(key)) paramsDn.put(key, new ArrayList<String>());
                    paramsDn.get(key).add((String) value);
                }
            }
        }

        for(Map.Entry<String, ArrayList<String>> e : paramsDn.entrySet()) {
            String key = e.getKey(); ArrayList<String> value = e.getValue();
            Set<String> unique = new HashSet<String>(value);

            if(unique.size() > 1) {
                sourceParams.put(key, new ArrayList<String>(unique));
            }
        }
    }

    public DecisionTreeBuilder startParse() {
        return parseNode(sourceDataSet, sourceParams);
    }

    public Node getRootNode() {
        return rootNode;
    }

    public DecisionTreeBuilder parseNode(List<Map<String, Object>> dataSet, HashMap<String, ArrayList<String>> params) {
        return parseNode(dataSet, params, null, null);
    }

    public DecisionTreeBuilder parseNode(List<Map<String, Object>> dataSet, HashMap<String, ArrayList<String>> params, Node currNode, String currValue) {
        if(dataSet.size() == 0) return this;

        int right = 0, left = 0, total;
        double entropy, gain;
        HashMap<String, Double> gains = new HashMap<String, Double>();

        for(Map<String,Object>  data: dataSet) {
            if(((BigInteger) data.get("direction")).intValue() == 0) left++;
            else right++;
        }

        total = right + left;
        entropy = countEntropy(left, right);

        if(left == 0 || right == 0) {
            String val = (left == 0) ? Integer.toString(right) : Integer.toString(left);
            Node newNode = new Node(val, left, right);
            currNode.children.add(new Edge(newNode, currValue));
            return this;
        }

        for(Map.Entry<String, ArrayList<String>> e : params.entrySet()) {
            String paramName = e.getKey();
            ArrayList<String> paramValues = e.getValue();

            HashMap<Integer, Integer[]> counts = new HashMap<Integer, Integer[]>();

            for(Map<String,Object> data : dataSet) {
                int key = paramValues.indexOf(data.get(paramName));
                if(!counts.containsKey(key)) {
                    Integer[] empty = {0,0};
                    counts.put(key, empty);
                }

                counts.get(key)[((BigInteger) data.get("direction")).intValue()]++;
            }

            int i = 0; double ent;
            gain = entropy;
            for(String v : paramValues) {
                if(!counts.containsKey(i)) {
                    Integer[] empty = {0,0};
                    counts.put(i, empty);
                }

                int ttl = counts.get(i)[0] + counts.get(i)[1];

                if(counts.get(i)[0] == 0 || counts.get(i)[1] == 0) ent = 0;
                else ent = countEntropy(counts.get(i)[0], counts.get(i)[1]);

                gain -= ((ttl/(double) total)*ent);
                i++;
            }
            gains.put(paramName, gain);
        }

        double maxGains = Collections.max(gains.values());
        if(maxGains == 0) {
            Node newNode = new Node("left: "+left+", right: "+right, left, right);
            currNode.children.add(new Edge(newNode, currValue));
            return this;
        }

        Map.Entry<String, Double> maxEntry = null;
        for (Map.Entry<String, Double> entry : gains.entrySet()) {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
                maxEntry = entry;
            }
        }

        String sel = maxEntry.getKey();
        Node newNode = new Node(sel, left, right);
        if(currNode == null) {
            rootNode = newNode;
        } else {
            currNode.children.add(new Edge(newNode, currValue));
        }

        HashMap<Integer, List<Map<String, Object>>> newDataSet = new HashMap<Integer, List<Map<String, Object>>>();
        for(Map<String,Object> data : dataSet) {
            int key = params.get(sel).indexOf(data.get(sel));
            if(!newDataSet.containsKey(key)) newDataSet.put(key, new ArrayList<Map<String, Object>>());

            newDataSet.get(key).add(data);
        }

        ArrayList<String> currentParam = params.get(sel);
        params.remove(sel);

        for(int i = 0; i < currentParam.size(); i++) {
            if(!newDataSet.containsKey(i)) continue;
            HashMap<String, ArrayList<String>> newParams = cloneParams(params);
            parseNode(newDataSet.get(i), newParams, newNode, currentParam.get(i));
        }

        return this;
    }

    private HashMap<String, ArrayList<String>> cloneParams(HashMap<String, ArrayList<String>> params) {
        HashMap<String, ArrayList<String>> newParams = new HashMap<String, ArrayList<String>>();
        for (Map.Entry<String, ArrayList<String>> entry : params.entrySet()) {
            newParams.put(entry.getKey(), entry.getValue());
        }
        return newParams;
    }

    private double countEntropy(int a, int b) {
        double total = a + b;
        return ((-1)*(a/total)*(Math.log(a/total)/Math.log(2))) - ((b/total)*(Math.log(b/total)/Math.log(2)));
    }

}
