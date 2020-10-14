/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.ArrayList;

/**
 *
 * @author Carlos Augusto
 */
public class Node {
    private ArrayList<Arc> arcs;
    private boolean marked;
    private String info;
    private boolean fim;
    
    public String getInfo() {
        return info;
    }

    public void setInfo(String value) {
        this.info = value;
    }
    
    public boolean getFim(){
        return fim;
    }
    
    public void setFim(boolean value){
        fim = value;
    }
    
    public ArrayList<Arc> getArcs(){
        return arcs;
    }
    
    public void setMarked(boolean value){
        this.marked = value;
    }
    
    public boolean getMarked(){
        return this.marked;
    }
    
    public Node(String info){
        arcs = new ArrayList<Arc>();
        this.info = info;
    }
    
    public Node(char info) {
        this(Character.toString(info));
    }
    
    public void AddArc(Arc value){
        arcs.add(value);
    }
    
    public Node getNodeByName(String info){
        for (Arc a : arcs){
            if (a.getTarget().getInfo().equals(info)){
                return a.getTarget();
            }
        }
        return null;
    }

    public Node getNodeByName(char charAt) {
        return getNodeByName(Character.toString(charAt));
    }
}
