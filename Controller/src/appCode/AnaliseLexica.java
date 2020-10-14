/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package appCode;

import config.Tokens;
import java.util.ArrayList;
import java.util.Enumeration;
import model.TokenValue;
import util.Graph;
import util.Node;

class RetornoCheckVariable {

    private boolean ehVariavel;
    private String variavel;
    private int i;

    /**
     * @return the ehVariavel
     */
    public boolean isEhVariavel() {
        return ehVariavel;
    }

    /**
     * @param ehVariavel the ehVariavel to set
     */
    public void setEhVariavel(boolean ehVariavel) {
        this.ehVariavel = ehVariavel;
    }

    /**
     * @return the variavel
     */
    public String getVariavel() {
        return variavel;
    }

    /**
     * @param variavel the variavel to set
     */
    public void setVariavel(String variavel) {
        this.variavel = variavel;
    }

    /**
     * @return the i
     */
    public int getI() {
        return i;
    }

    /**
     * @param i the i to set
     */
    public void setI(int i) {
        this.i = i;
    }

    public RetornoCheckVariable(int i, String variavel, boolean ehVariavel) {
        this.i = i;
        this.variavel = variavel;
        this.ehVariavel = ehVariavel;
    }
}

/**
 * 
 * @author Carlos Augusto
 */
public class AnaliseLexica {

    private Graph g;
    private ArrayList<String> tokens;
    private ArrayList<String> vars;
    private int lin;
    private String textoComErro;
    private ArrayList<TokenValue> tokenValues;

    public String getTextoComErro() {
        return textoComErro;
    }

    public int getLinhaErro() {
        return lin+1;
    }

    public ArrayList<String> getTokens() {
        return tokens;
    }
    
    public ArrayList<TokenValue> getTokenValues(){
        return this.tokenValues;
    }

    public AnaliseLexica() {
        g = new Graph();
        tokens = new ArrayList<String>();
        tokenValues = new ArrayList<TokenValue>();
        mountGraph();
    }

    /**
     * Se é o proximo caracter é espaço vazio ou \r\n
     * @param i
     * @param text
     * @return 
     */
    private boolean Fim(int i, String text) {
        if (i + 1 == text.length()) {
            return true;
        }
        if (text.charAt(i + 1) == ' '
                || text.charAt(i + 1) == '\r'
                || text.charAt(i + 1) == '*') {
            return true;
        }
        return false;
    }

    public boolean Analyse(String text)  {
        int i;
        char charac, proximoCharac = 0;
        Tokens tk = new Tokens();
        String token = "";

        lin = 0;

        for (i = 0; i < text.length(); i++) {
            charac = text.charAt(i);
            if (i + 1 < text.length()) {
                proximoCharac = text.charAt(i + 1);
            }

            //Se tem um espaco ou se tiver um enter
            if (charac == '\n') {
                lin++;
            }
            //se um espaco ou enter e nao seja \n
            if (charac == ' ' || charac == '\n' || charac == '\r'
                    || charac == '\t') {
                token = "";
                continue;
            }
            //adiciona na string token cada caracter
            token += charac;

            //Tenta encontrar o nó da letra
            Node nTmp = g.Find(charac);
            //Não encontrou = erro
            if (nTmp != null) {
                //Se nao chegou ao fim
                if (!Fim(i, text)) {
                    //Encotrou a proxima letra no grafo ?
                    if (g.FindFromNode(proximoCharac,nTmp) != null) {
                        //O atual = proximo
                        nTmp = g.Find(proximoCharac);
                    } else {
                        //Verificar Variavel
                        i = CheckAndAddVariable(text, i, token);
                        token = "";
                        if (i == -1) {
                            break;
                        }
                    }
                } //Se é fim
                else {
                    //O token é final ?
                    if (nTmp.getFim()) {
                        tokens.add(tk.getValue(token));
                        token = "";
                    } else {
                        //Verificar Variavel
                        i = CheckAndAddVariable(text, i, token);
                        token = "";
                        if (i == -1) {
                            break;
                        }
                    }
                }
            } else {
                //Verifica Variavel
                i = CheckAndAddVariable(text, i, token);
                token = "";
                if (i == -1) {
                    break;
                }
            }

        }

        //Se o i correu o texto inteiro
        if (i >= text.length()) {
            return true;
        }

        //Coloca a tag [erro] na primeira linha com erro
        textoComErro = textoComErro(text);

        return false;
    }

    /**
     * Refaz o texto adicionando a tag [erro] na primeira linha com erro
     * @param text
     * @return 
     */
    private String textoComErro(String text) {
        int countLinha = 0;
        String texto = "";
        if (!text.contains("\n")) {
            return "[erro]" + text + "[/erro]";
        }
        for (int i = 0; i < text.length(); i++) {
            if (countLinha < lin) {
                texto += text.charAt(i);
                if (text.charAt(i) == '\n') {
                    countLinha++;
                }
            } else {
                if (countLinha == lin
                        && !texto.contains("[erro]")) {
                    texto += "[erro]";
                }
                texto += text.charAt(i);
                countLinha++;
                if (i + 1 == text.length()
                        && !texto.contains("[/erro]")) {
                    break;
                }
                if (i + 2 < text.length()
                        && text.charAt(i + 2) == '\n'
                        && !texto.contains("[/erro]")) {
                    texto += "[/erro]";
                }
            }
        }
        return texto;
    }

    /**
     * Mount the graph with the words of the program
     */
    private void mountGraph() {
        Tokens tk = new Tokens();
        Enumeration e = tk.getTokens().keys();
        while (e.hasMoreElements()) {
            String value = (String) e.nextElement();
            AddWord(value);
        }
    }

    /**
     * Adiciona uma palavra ao grafo ?
     * @param word 
     */
    public void AddWord(String word) {
        Node nFrom, nTo;
        nFrom = nTo = null;
        for (int i = 0; i < word.length(); i++) {
            if (g.Find(word.charAt(i)) != null && i == 0) {
                nFrom = g.Find(word.charAt(i));
            }
            if (nFrom == null) {
                g.AddNode(word.charAt(i));
                nFrom = g.Find(word.charAt(i));
            } else {
                g.AddNode(word.charAt(i));
                nTo = g.Find(word.charAt(i));
                g.AddArc(nFrom.getInfo(), nTo.getInfo());
                nFrom = nTo;
            }
        }
        g.Find(nFrom.getInfo()).setFim(true);
    }

    private int CheckAndAddVariable(String text, int iAtual, String tokenSoFar) {
        RetornoCheckVariable rCk = AddVariable(text, iAtual, tokenSoFar);
        if (rCk.isEhVariavel()) {
            if (rCk.getVariavel().matches("[0-9]*\\.[0-9]*")) {
                tokens.add("tkReal"); 
                tokenValues.add(new TokenValue(rCk.getVariavel(),TokenValue.Tipo.REAL,Float.valueOf(rCk.getVariavel()),false));
            } else if (rCk.getVariavel().matches("[0-9]*")) {
                tokens.add("tkInt");
                tokenValues.add(new TokenValue(rCk.getVariavel(),TokenValue.Tipo.INTEGER, Integer.valueOf(rCk.getVariavel()),false));
            } else {
                tokens.add("tkId");
                tokenValues.add(new TokenValue(rCk.getVariavel(),TokenValue.Tipo.NONE,null,false));
            }   
            iAtual = rCk.getI();
            return iAtual;
        } else {
            return -1;
        }
    }

    /**
     * Adiciona a variavel e verifica
     */
    private RetornoCheckVariable AddVariable(String texto, int i, String tokenSoFar) {
        RetornoCheckVariable retorno = null;
        String var = tokenSoFar;
        int icopy = i + tokenSoFar.length();
        for (; icopy < texto.length(); icopy++) {
            if (Fim(icopy-1, texto)) {
                break;
            }
            var += texto.charAt(icopy);
        }
        return retorno = new RetornoCheckVariable(icopy, var.trim(), CheckVar(var.trim()));
    }

    /**
     * Verifica se é uma variável e adiciona ao grafo
     * @param word 
     */
    private boolean CheckVar(String word) {
        if (word.matches("(?im)(\\b(([A-z]+[0-9]*[A-z]*)|\\b([0-9]|\\.)*\\b|)\\b)")) {
            return true;
        }
        return false;
    }
}
