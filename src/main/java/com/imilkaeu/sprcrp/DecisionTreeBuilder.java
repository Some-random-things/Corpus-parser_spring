package com.imilkaeu.sprcrp;

import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry;

import java.util.*;

/**
 * Created by Pavel on 02.04.14.
 */
public class DecisionTreeBuilder {
    class Node {
        public ArrayList<Edge> children;
        public String name;

        public Node(String n) {
            this.name = n;
        }
    }
    class Edge {
        public Node end;
        public String value; //String?

        public Edge(Node e, String v) {
            this.end = e;
            this.value = v;
        }
    }
    public int chooseParam(List<HashMap<String,String>> data, HashMap<String,ArrayList<String>> params,Node currNode, String currValue){
        int right = 0, left = 0, total;
        double entropy, gain;
        Node rootNode;
        HashMap<String, Double> gains = new HashMap<String, Double>();

        //calculate left and right
        for(HashMap<String,String> ans : data){
            if(ans.get("direction").matches("0")) left++; //в php имелось в виду "0" или пустое значение?
            else right++;
        }
        //
        total = right + left;
        entropy = ((-1)*(right/total)*(Math.log(right/total)/Math.log(2))) - ((left/total)*(Math.log(left/total)/Math.log(2)));

        //finishing
        if(left == 0 || right ==0){
           String val = (left == 0) ? "<b>RIGHT</b> "+right+" occurences" : "<b>LEFT</b> "+left+" occurences";
           Node newNode = new Node(val);
           currNode.children.add(new Edge(newNode, currValue));
           return 1;
        }

        for(Map.Entry<String, ArrayList<String>> e : params.entrySet()){


            for(HashMap<String,String> ans : data){
                //што
            }

            int i = 0;
            double ent;
            gain = entropy;
            for(String v : e.getValue()){
                //што2
            }
            gains.put(e.getKey(), gain);
        }

        if(Collections.max(gains.values()) == 0){
            //finishing no gain
            Node newNode = new Node("left: "+left+", right: "+right);
            currNode.children.add(new Edge(newNode, currValue));
            return 1;
        }

        // $sel = array_search(max($gains),$gains);
        Node newNode = new Node(sel);
        if(currNode == null){
            rootNode = newNode;
        } else {
            currNode.children.add(new Edge(newNode,currValue));
        }

        //slicing data
        /*




         */

        //removing current param ...

        return 0;
    }

}
