/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Carlos Augusto
 */
public class Arc {
    private Node target;
    private int weigth;
    
    public Node getTarget(){
        return target;
    }

    public int getWeigth(){
        return weigth;
    }
    
    public Arc(Node target){
        this.target = target;
        this.weigth = 0;
    }
    public Arc(Node target,int weigth){
        this.target = target;
        this.weigth = weigth;
    }
}
