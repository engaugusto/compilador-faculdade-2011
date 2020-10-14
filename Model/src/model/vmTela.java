/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Queiroz
 * Classe genérica para transporte das telas para a controller
 */
public class vmTela {

    private String action;

    public vmTela() {
    }

    /**
     *
     * @return Action Invocada pela tela
     */
    public String GetAction() {
        return action;
    }

    public void SetAction(String s) {
        action = s;
    }
}
