package com.imilkaeu.sprcrp;

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
        }
    }

    public class Edge {
        public Node end;
        public String value;

        public Edge(Node e, String v) {
            this.end = e;
            this.value = v;
        }
    }

    private Node rootNode = null;
    private List<Map<String, Object>> sourceDataSet;
    private HashMap<String, ArrayList<String>> sourceParams;

    public DecisionTreeBuilder(List<Map<String, Object>> ds) {
        sourceDataSet = ds;
        HashMap<String, ArrayList<String>> paramsDn = new HashMap<String, ArrayList<String>>();

        for(Map<String, Object> sourceData : sourceDataSet) {
            for(Map.Entry<String, Object> e : sourceData.entrySet()) {
                String key = e.getKey(); Object value = e.getValue();
                // if($k != "direction" AND $k != "partOfSpeech1" AND $k != "partOfSpeech2") $paramsdn[$k][] = $v;
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
            if((Integer) data.get("direction") == 0) left++;
            else right++;
        }

        total = right + left;
        entropy = countEntropy(left, right, total);

        if(left == 0 || right ==0) {
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

                counts.get(key)[(Integer) data.get("direction")]++;
            }

            int i = 0; double ent;
            gain = entropy;
            for(String v : paramValues) {
                int ttl = counts.get(i)[0] + counts.get(i)[1];

                if(counts.get(i)[0] == 0 || counts.get(i)[1] == 0) ent = 0;
                else ent = countEntropy(counts.get(i)[0], counts.get(i)[1], ttl);

                gain -= ((ttl/total)*ent);
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
            parseNode(newDataSet.get(i), params, newNode, currentParam.get(i));
        }

        return this;
    }

    private double countEntropy(int a, int b, int total) {
        return ((-1)*(a/total)*(Math.log(a/total)/Math.log(2))) - ((b/total)*(Math.log(b/total)/Math.log(2)));
    }

}
