/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import config.Tokens;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.TokenValue;
import model.vmTela;
import model.vmTela_Compilador;

/**
 *
 * @author Admin
 */
public class TranslateToCAction implements controller.Action {

    private boolean achouIf;
    private boolean achouFor;
    private int cont = 0;
    
    public TranslateToCAction() {
    }

    public void execute(vmTela dados) {
        ((vmTela_Compilador) dados).setSintatica(true);
        AnaliseAction analise = new AnaliseAction();
        analise.execute(dados);
        if (!analise.GetTokens().isEmpty()) {
            ((vmTela_Compilador) dados).setOutput(Translate(analise.GetTokens(),analise.GetTokenInfo()));
            ((vmTela_Compilador) dados)._CCODE = Translate(analise.GetTokens(),analise.GetTokenInfo());
        }

        String path = "C:\\Users\\luizbag\\Desktop\\tmp.c";
        String pathOutput = "C:\\Users\\luizbag\\Desktop\\a.exe";
        String command = "C:\\MinGW\\bin\\gcc.exe "+path+" -o "+pathOutput;

        FileWriter fr;
        try {
            fr = new FileWriter(path);
            fr.write(((vmTela_Compilador) dados)._CCODE);
            fr.close();
        } catch (IOException ex) {
            Logger.getLogger(TranslateToCAction.class.getName()).log(Level.SEVERE, null, ex);
        }


        String command2 = pathOutput;
        Runtime system = Runtime.getRuntime();
        try {
            system.exec(command);
            system.exec(command2);
        } catch (IOException ex) {
            Logger.getLogger(TranslateToCAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String Translate(ArrayList<String> tokens, ArrayList<TokenValue> tokenNames) {
        String traduzido = "";
        Tokens t = new Tokens();
        int countVars = 0;
        traduzido += "#include<stdio.h>\r\n#include<stdlib.h>\r\n\r\n";
        for (String s : tokens) {
            if(s.equals("tkId") || s.equals("tkInt") || s.equals("tkReal")){
                traduzido += tokenNames.get(countVars).getTokenName();
                countVars++;
            }else if (t.getCKeyWord(s) != null) {
                if (cont != 13) {
                    traduzido += t.getCKeyWord(s) + "\n";
                } else {
                    System.out.println("Erro Token NULO : " + s);
                }
            }

            if (achouFor || achouIf) {
                cont++;
            }
            seEncontrouComParenteses(s);

            if (cont == 3
                    && achouIf) {
                traduzido += ")";
                cont = 0;
                achouIf = false;
            } else if (cont == 13
                    && achouFor) {
                traduzido += ")";
                cont = 0;
                achouIf = false;
            }            
        }          
        return traduzido;
    }
    
    private boolean seEncontrouComParenteses(String s) {
        if (s.contains("tkIf")
                || s.contains("tkFor")) {
            if (s.contains("tkIf")) {
                achouIf = true;
            } else if (s.contains("tkFor")) {
                achouFor = true;
            }
            return true;
        }
        return false;
    }
}
