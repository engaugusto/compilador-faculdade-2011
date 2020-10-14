/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package HandleView;

import controller.FrontController;
import model.*;

/**
 *
 * @author Admin
 */
public class Handle  {

    private static vmTela_Compilador tela;

    public vmTela_Compilador getTela() {
        return tela;
    }

    /**
     *
     * @param Codigo presente na tela
     * @param Output presente na tela
     * @param Debug presente na tela
     * @param Se selecionou a analise AnaliseLexica
     * @param Se selecionou a analise AnaliseSemantica
     * @param Se selecionou a analise AnaliseSintatica
     * @param Se selecionou para gerar Assembly
     * @throws Exception
     */
    public void Analise(String Codigo,
            String Output,
            String Debug,
            boolean AnaliseLexica,
            boolean AnaliseSemantica,
            boolean AnaliseSintatica,
            boolean Assembly) throws Exception {
        tela = new vmTela_Compilador();

        //Setando a action requisitada para Analisar o codigo
        tela.SetAction("Analise");

        //passando os parametros para a análise
        tela.setCodigo(Codigo);
        tela.setDebug(Debug);
        tela.setOutput(Output);

        tela.setLexica(AnaliseLexica);
        tela.setSintatica(AnaliseSintatica);
        tela.setSemantica(AnaliseSemantica);
        tela.setAssembly(Assembly);

        //invocando a analise
        FrontController controller = new FrontController();
        controller.processRequest(tela);
    }
    
    public void Convert2C(String codigo, String output, String debug) throws Exception{
        tela = new vmTela_Compilador(codigo, output, debug, false, false, false, false);
        tela.SetAction("TranslateToC");
        FrontController controller = new FrontController();
        controller.processRequest(tela);
    }
}
