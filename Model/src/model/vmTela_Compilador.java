/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author Queiroz
 * Classe responsavel pelo encapsulamento dos campos da tela para a controller
 */
public class vmTela_Compilador extends vmTela{
private boolean lexica;
    private boolean sintatica;
    private boolean semantica;
    private boolean assembly;
    private boolean convert2C;
    private String codigo;
    private String output;
    private String debug;
    public String _CCODE;

    /**
     *
     * @param Codigo digitado
     * @param Output da tela
     * @param Debug da tela
     * @param Se selecionou a analise Lexica
     * @param Se selecionou a analise Semantica
     * @param Se selecionou a analise Sintatica
     * @param Se selecionou para gerar Assembly
     */
    public vmTela_Compilador(String Codigo, String Output, String Debug, boolean Lexica, boolean Semantica, boolean Sintatica, boolean Assembly) {
        this.codigo = Codigo;
        this.debug = Debug;
        this.output = Output;
        this.assembly = Assembly;
        this.lexica = Lexica;
        this.semantica = Semantica;
        this.sintatica = Sintatica;

    }

    public vmTela_Compilador() {
        super();
        this.codigo = "";
        this.debug = "";
        this.output = "";        
        this.lexica = false;
        this.sintatica = false;
        this.semantica = false;
        this.assembly = false;        
    }

    /**
     * @return Código fonte digitado na tela
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param Código fonte digitado na tela
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return A saída do compilador
     */
    public String getOutput() {
        return output;
    }

    /**
     * @param A saída do compilador
     */
    public void setOutput(String output) {
        this.output += output;
    }

    /**
     * @return O texto com informações de debug
     */
    public String getDebug() {
        return debug;
    }

    /**
     * @param O texto com informações de debug
     */
    public void setDebug(String debug) {
        this.debug += debug;
    }


    /**
     * @return Se selecionou a analise léxica
     */
    public boolean isLexica() {
        return lexica;
    }

    /**
     * @param Se selecionou a analise léxica
     */
    public void setLexica(boolean lexica) {
        this.lexica = lexica;
    }

    /**
     * @return Se selecionou a analise sintática
     */
    public boolean isSintatica() {
        return sintatica;
    }

    /**
     * @param Se selecionou a analise Sintática
     */
    public void setSintatica(boolean sintatica) {
        this.sintatica = sintatica;
    }

    /**
     * @return Se selecionou a analise semantica
     */
    public boolean isSemantica() {
        return semantica;
    }

    /**
     * @param Se selecionou a analise semantica
     */
    public void setSemantica(boolean semantica) {
        this.semantica = semantica;
    }

    /**
     * @return Se selecionou para gerar assembly
     */
    public boolean isAssembly() {
        return assembly;
    }

    /**
     * @param Se selecionou para gerar assembly
     */
    public void setAssembly(boolean assembly) {
        this.assembly = assembly;
    }
}
