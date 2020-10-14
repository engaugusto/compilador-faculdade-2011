/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package appCode;

import config.Tokens;
import java.util.ArrayList;
import java.util.Enumeration;
import util.Graph;

/**
 * 
 * @author Carlos Augusto
 */
public class AutoComplete {
    private Graph g;
    private Tokens tk;

    public AutoComplete() {
        g = new Graph();
    }

    public ArrayList<String> ReturnAutoComplete(String word) {
        ArrayList<String> retorno = new ArrayList<String>();
        Tokens tk = new Tokens();
        Enumeration e = tk.getTokens().keys();
        while (e.hasMoreElements()) {
            String value = (String) e.nextElement();
            if(value.startsWith(word)){
                retorno.add(value);
            }
        }
        return retorno;
    }
}
