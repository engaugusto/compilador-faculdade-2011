/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 *
 * @author Queiroz
 */
public class Compiler_Stack{
    private ArrayList<Object> objetos;
    private int elementos;
    
    
    public Compiler_Stack()
    {
        objetos = new ArrayList<Object>();
        elementos = 0;
    }
    public int height() {
        return elementos;
    }

    public Object pop() throws NoSuchElementException {
        return objetos.get(--elementos);
        
    }

    public void push(Object o) {
        objetos.add(o);
        elementos++;
    }

    public Object top() throws NoSuchElementException {
        return objetos.get(elementos);
    }

    public void clear() {
        objetos.clear();
        elementos = 0;
    }
    
}
