/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import appCode.*;
import config.Tokens;
import db.Usuario;
import hibernate.query.QUsuario;
import hibernate.query.QVersao;
import java.util.ArrayList;
import model.*;

/**
 *
 * @author Admin
 */
public class AnaliseAction implements controller.Action {

    private AnaliseLexica _analiseLexica;
    private AnaliseSintatica _analiseSintatica;
    private AnaliseSemantica _analiseSemantica;

    public ArrayList<String> GetTokens() {
        return _analiseLexica.getTokens();
    }

    public ArrayList<TokenValue> GetTokenInfo() {
        return _analiseLexica.getTokenValues();
    }

    public AnaliseAction() {
    }

    public void execute(vmTela dados) {

        boolean passouAnalise = false;
        //Recebo a classe genérica de tela, casto ela para o tipo correto e vejo os parametros das radios selecionadas para
        //determinar qual analise será feita
        if (((vmTela_Compilador) dados).isLexica()) {
            passouAnalise = AnaliseLexica((vmTela_Compilador) dados);
        } else if (((vmTela_Compilador) dados).isSintatica()) {
            passouAnalise = AnaliseSintatica((vmTela_Compilador) dados);
        } else if (((vmTela_Compilador) dados).isSemantica()) {
            passouAnalise = AnaliseSemantica((vmTela_Compilador) dados);
        } else if (((vmTela_Compilador) dados).isAssembly()) {
            passouAnalise = GeraAssembly((vmTela_Compilador) dados);
        }
        if (passouAnalise) {
            try{
            QUsuario usuarioDAO = new QUsuario();
            QVersao versaoDAO = new QVersao();
            //MUDAR PARA BUSCAR USUARIO LOGADO NO SISTEMA ALGUM DIA !!!!!
            Usuario user = usuarioDAO.BuscaUsuario(1);
            versaoDAO.GravarVersao(((vmTela_Compilador) dados).getCodigo(), user);
            }catch(Exception e){
                e.printStackTrace();                
            }
        }
    }

    private boolean AnaliseLexica(vmTela_Compilador dados) {
        //Realizando a analise léxica
        _analiseLexica = new AnaliseLexica();
        boolean resultado = false;

        resultado = _analiseLexica.Analyse(dados.getCodigo());

        if (resultado) {
            //montando o output do compilador
            String saida = "Sucesso na analise Lexica !\r\n\r\nTokens encontrados:\r\n";
            ArrayList<String> tokens = _analiseLexica.getTokens();

            for (String token : tokens) {
                saida += token + "\r\n";
            }
            dados.setOutput(saida + "\r\n\r\n");
        } else {
            //Se a analise falhar/der erro
            dados.setOutput("Erro na linha: " + String.valueOf(_analiseLexica.getLinhaErro() + 1) + "\r\n\r\n");
            //colocando o código com as tags de erro
            dados.setCodigo(_analiseLexica.getTextoComErro());
        }
        //_tokens = analise.getTokens();
        //_tokenValues = analise.getTokenValues();
        return resultado;
    }

    private boolean AnaliseSintatica(vmTela_Compilador dados) {
        boolean sucesso = true;

        if (AnaliseLexica(dados)) {
            AnaliseSintatica analise = new AnaliseSintatica();

            String Output = "";
            ArrayList<String> tokensCpy = CopyTokens(_analiseLexica.getTokens());
            if (analise.Analyse(tokensCpy)) {
                Output = "Sucesso na Analise Sintatica !\r\n\r\n";
            } else {
                sucesso = false;
                Tokens tkManager = new Tokens();
                Output = "Houve um erro de sintaxe:\n";
                Output += "\nFoi encontrado: " + tkManager.getKeyWord(analise.getTokenEncontrado());
                Output += "\nEra esperado: ";
                int count = 0;
                for (String s : analise.getTokenEsperado()) {
                    if (count < analise.getTokenEsperado().size() - 1) {
                        Output += tkManager.getKeyWord(s) + ", ";
                    } else {
                        Output += tkManager.getKeyWord(s);
                    }
                }
            }
            dados.setOutput(Output);
        } else {
            sucesso = false;
        }
        return sucesso;
    }

    private boolean AnaliseSemantica(vmTela_Compilador dados) {
        boolean sucesso = true;
        if (AnaliseSintatica(dados)) {
            AnaliseSemantica analise = new AnaliseSemantica();
            analise.setTokens(_analiseLexica.getTokens());
            analise.setTokenValues(_analiseLexica.getTokenValues());
            if (analise.AnalisaCodigo()) {
                dados.setOutput("Sucesso na analise Semantica!\r\n\r\n");
            } else {
                dados.setOutput(analise.MensagemErro() + "\r\n\r\n");
            }
        } else {
            sucesso = false;
        }
        return sucesso;
    }

    private boolean GeraAssembly(vmTela_Compilador dados) {
        boolean sucesso = true;
        if (AnaliseSemantica(dados)) {
            AnaliseSintatica analise = new AnaliseSintatica();
            if (analise.Analyse(_analiseLexica.getTokens(), _analiseLexica.getTokenValues())) {
                dados.setOutput(analise.GeneratedAssembly());
            } else {
                sucesso = false;
            }
        } else {
            sucesso = false;
        }
        return sucesso;
    }

    private ArrayList<String> CopyTokens(ArrayList<String> Tokens) {
        ArrayList<String> TokensCpy = new ArrayList<String>();

        for (int i = 0; i < Tokens.size(); i++) {
            TokensCpy.add(Tokens.get(i));
        }

        return TokensCpy;
    }
}
