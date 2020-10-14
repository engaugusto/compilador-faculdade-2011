/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

/**
 *
 * @author Andreia
 */
public class ActionFactory {

    public static Action create(String action){
        Action actionObject = null;
        //montando o nome da action seguindo o padrao <package>.<nomeAction>Action
        String nomeClasse = "action." + action + "Action";

        Class classe = null;
        Object objeto = null;

        try{
            //Instanciando a classe da action
            classe = Class.forName(nomeClasse);
            objeto = classe.newInstance();

        }catch(Exception e){
            return null;
        }

        if(!(objeto instanceof Action)) return null;
        //Castando a action específica para a interface para o retorno genérico
        actionObject = (Action) objeto;

        return actionObject;
    }

    

}


