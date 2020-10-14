/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Hashtable;

/**
 *
 * @author Carlos Augusto
 */
public class Graph {
    private Hashtable<String,Node> nodes;
    
    public Hashtable<String,Node> getNodes(){
        return nodes;
    }
    
    public Node Find(String name){
        if(nodes.containsKey(name))
            return nodes.get(name);
        return null;
    }
    
    public Node Find(char name) {
        if(nodes.containsKey(Character.toString(name)))
            return nodes.get(Character.toString(name));
        return null;
    }
    
    public Node FindFromNode(char name,Node nFrom) {
        if(nodes.containsKey(nFrom.getInfo()))
        {
            Arc aTarget = null;
            boolean find = false;
            for(Arc a : nFrom.getArcs()){
                if(a.getTarget().getInfo().equals(Character.toString(name))){
                    aTarget = a;
                    find = true;
                    break;
                }
            }
            if(find)
                return aTarget.getTarget();
            else return null;
        }
        return null;
    }
    
    public Graph(){
        nodes = new Hashtable<String, Node>();
    }
    
    public void AddArc(String From, String To){
        AddArc(From,To,0);
    }
    
    public void AddArc(String From, String To, int Weigth){
        Node nF = nodes.get(From);
        Node nT = nodes.get(To);
        Arc a = new Arc(nT,Weigth);
        nF.AddArc(a);
    }
    
    public void AddNode(String info) {
        if (Find(info) == null){
            nodes.put(info, new Node(info));
        }
    }
    
    public void AddNode(char info) {
        if (Find(info) == null){
            nodes.put(Character.toString(info), new Node(info));
        }
    }
    
    public void ClearNodeInfo(){
        for(Node n : nodes.values()){
            n.setMarked(false);
        }
    }
}
