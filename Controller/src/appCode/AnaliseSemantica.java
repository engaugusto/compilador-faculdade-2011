/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package appCode;

import java.util.ArrayList;
import model.TokenValue;

/**
 *
 * @author Queiroz
 */
public class AnaliseSemantica {

    private ArrayList<TokenValue> _tokenValues;
    private ArrayList<String> _tokens;
    private String _erro;

    /**
     * @param tokenValues the tokenValues to set
     */
    public void setTokenValues(ArrayList<TokenValue> tokenValues) {
        this._tokenValues = tokenValues;
    }

    /**
     * @param tokens the tokens to set
     */
    public void setTokens(ArrayList<String> tokens) {
        this._tokens = tokens;
    }

    public String MensagemErro() {
        return this._erro;
    }

    public AnaliseSemantica() {
    }

    public boolean AnalisaCodigo() {
        int count = 0;
        int countVars = 0;
        boolean sucesso = true;
        //se nao tiver nenhuma variavel ou constante ou token da erro
        if (_tokens.size() > 0 || _tokenValues.size() > 0) {
            while (count < _tokens.size()) {
                //marcando que a variavel foi declarada com o tipo inteiro
                if (_tokens.get(count).equals("tkI")) {
                    if (!MarcaDeclaracao(_tokenValues.get(countVars).getTokenName(), TokenValue.Tipo.INTEGER)) {
                        sucesso = false;
                        break;
                    }
                    //marcando que a variavel foi declarada com o tipo real
                } else if (_tokens.get(count).equals("tkR")) {
                    if (!MarcaDeclaracao(_tokenValues.get(countVars).getTokenName(), TokenValue.Tipo.REAL)) {
                        sucesso = false;
                        break;
                    }
                }
                //se for uma variavel e o proximo token for de atribuição, checa se sao tipos compativeis
                if (_tokens.get(count).equals("tkId")) {
                    if (_tokenValues.get(countVars).isDeclarado()) {
                        //se foi encontrada junta de um operador a frente ou atras, marca todas as ocorrencias como ja usada uma vez
                        if (OperadorDeVariavel(_tokens.get(count + 1))
                                || OperadorDeVariavel(_tokens.get(count - 1))) {
                            if (!_tokenValues.get(countVars).isUsedOnce()) {
                                MarcaUsada(_tokenValues.get(countVars).getTokenName());
                            }
                        }
                        if (_tokens.get(count + 1).equals("tkAtrib")) {
                            //se for uma constante o proximo token, compara o tipo com o da constante
                            if (_tokens.get(count + 2).equals("tkInt") || _tokens.get(count + 2).equals("tkReal")) {
                                if (!ComparaTiposConstantes(countVars)) {
                                    sucesso = false;
                                    break;
                                }
                                //senao compara com o tipo de outra variavel
                            } else {
                                if (!ComparaTiposVariaveis(countVars)) {
                                    sucesso = false;
                                    break;
                                }
                            }
                        }
                    }else
                    {
                        sucesso = false;
                        _erro = "Variavel " + _tokenValues.get(countVars).getTokenName() + " não declarada !";
                        break;
                    }
                    countVars++;
                }

                if (_tokens.get(count).equals("tkInt") || _tokens.get(count).equals("tkReal")) {
                    countVars++;
                }

                count++;
            }
        }
        return sucesso;
    }

    private boolean MarcaDeclaracao(String nome, TokenValue.Tipo tipo) {

        boolean ok = true;
        //correndo variaveis marcando se foram declaradas e o tipo que foi declarada
        for (int i = 0; i < _tokenValues.size(); i++) {
            if (_tokenValues.get(i).getTokenName().equals(nome)) {
                //se ja foi declarada da erro e sai da função
                if (_tokenValues.get(i).isDeclarado()) {
                    this._erro = "Variavel " + _tokenValues.get(i).getTokenName() + " já foi declarada !";
                    ok = false;
                    break;
                }
                _tokenValues.get(i).setDeclarado(true);
                _tokenValues.get(i).setType(tipo);
            }
        }
        return ok;
    }

    private boolean ComparaTiposConstantes(int PosVar) {
        //compara tipo da variavel com a constante
        if (_tokenValues.get(PosVar).getType().equals(TokenValue.Tipo.INTEGER) && _tokenValues.get(PosVar + 1).isInteger()) {
            return true;
        } else if (_tokenValues.get(PosVar).getType().equals(TokenValue.Tipo.REAL) && _tokenValues.get(PosVar + 1).isReal()) {
            return true;
        } else {
            this._erro = "Atribuição de tipo incorreto para variavel: " + _tokenValues.get(PosVar).getTokenName();
            return false;
        }
    }

    private boolean ComparaTiposVariaveis(int PosVar) {
        //compara tipo da variavel com a proxima variavel da lista
        if (_tokenValues.get(PosVar).getType().equals(_tokenValues.get(PosVar + 1).getType())) {
            return true;
        } else {
            this._erro = "Atribuição de tipo incorreto para variavel: " + _tokenValues.get(PosVar).getTokenName();
            return false;
        }
    }

    private void MarcaUsada(String Nome) {
        for (int i = 0; i < _tokenValues.size(); i++) {
            if (_tokenValues.get(i).getTokenName().equals(Nome)) {
                _tokenValues.get(i).setUsedOnce(true);
            }
        }
    }

    private boolean OperadorDeVariavel(String Token) {
        if (Token.equals("tkSoma")
                || Token.equals("tkSub")
                || Token.equals("tkMult")
                || Token.equals("tkDiv")
                || Token.equals("tkAtrib")
                || Token.equals("tkGreater")
                || Token.equals("tkMinor")
                || Token.equals("tkEquals")
                || Token.equals("tkGreatEq")
                || Token.equals("tkMinorEq")) {
            return true;
        } else {
            return false;
        }
    }
}
