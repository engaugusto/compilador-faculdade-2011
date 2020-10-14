/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package appCode;

import antlr.collections.Stack;
import config.Tokens;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import model.TokenValue;
import util.Compiler_Stack;

/**
 *
 * @author Queiroz
 */
public class AnaliseSintatica {

    private String erro;
    private ArrayList<String> tokenEsperado;
    private String tokenEncontrado;
    private int cont;
    //usados para o assembly
    private ArrayList<TokenValue> _variaveis;
    private Compiler_Stack elementos;
    private int AssemblyIds;
    private boolean assembly;
    private String generatedAssembly;
    private int countVars;

    /**
     * 
     * @return Token esperado pela analise (somente é preenchido se houver erro durante a analise)
     */
    public ArrayList<String> getTokenEsperado() {
        return tokenEsperado;
    }

    /**
     * 
     * @return Token encontrado (somente é preenchido se houver erro durante a analise)
     */
    public String getTokenEncontrado() {
        return tokenEncontrado;
    }
    
    public String GeneratedAssembly(){
        return generatedAssembly;
    }

    public AnaliseSintatica() {
        tokenEncontrado = "";
        tokenEsperado = new ArrayList<String>();
        cont = 0;
        //usado para o assembly
        elementos = new Compiler_Stack();
        AssemblyIds = 0;
        assembly = false;
        generatedAssembly = "";
        countVars = 0;
    }

    public boolean Analyse(ArrayList<String> tokens) {
        return Inicio(tokens);
    }
    //sobrecarca para gerar o assembly

    public boolean Analyse(ArrayList<String> tokens, ArrayList<TokenValue> Variaveis) {
        this._variaveis = Variaveis;
        assembly = true;
        return Inicio(tokens);
    }

    /**
     * 
     * @param tokens para analise
     * @return Se o codigo esta sintaticamente correto
     */
    private boolean Inicio(ArrayList<String> tokens) {
        boolean retorno = true;
        //Checando se nao for o inicio, verifica se é comentario
        if (!tokens.get(0).equals("tkMain")) {
            if (!ChecaComentarios(tokens)) {
                retorno = false;
                tokenEncontrado = tokens.get(0);
                tokenEsperado.add("tkMain");
            }
        } else {
            //se nao for comentario, remove o token incial, e checa novamente por comentarios
            tokens.remove(0);
            if (tokens.size() > 0) {
                if (tokens.get(0).equals("tkOpenFunc")) {
                    tokens.remove(0);
                    //checando se ainda tem coisas para analisar no meio do codigo
                    if (tokens.size() > 0) {
                        retorno = ChecaBlocoPrincipal(tokens);

                    } else {
                        retorno = false;
                    }
                } else {
                    if (!ChecaComentarios(tokens)) {
                        tokenEncontrado = tokens.get(0);
                        tokenEsperado.add("tkOpenFunc");
                        retorno = false;
                    } else {
                        if (tokens.size() > 0) {
                            if (tokens.get(0).equals("tkOpenFunc")) {
                                tokens.remove(0);
                                retorno = ChecaBlocoPrincipal(tokens);
                            }
                        }
                    }
                }
            }
        }
        return retorno;
    }

    /**
     * 
     * @param tokens para analise
     * @return se o corpo do codigo corresponde a sintaxe da linguagem
     */
    private boolean ChecaBlocoPrincipal(ArrayList<String> tokens) {
        boolean retorno = false;

        do {
            if (tokens.size() > 0) {
                //checnado declaracao de inteiros e reais
                if (tokens.get(0).equals("tkI") || tokens.get(0).equals("tkR")) {
                    retorno = ChecaDeclaracao(tokens);
                    if (!retorno) {
                        break;
                    }
                } //checando decalaracao de ifs
                else if (tokens.get(0).equals("tkIf")) {
                    retorno = ChecaIf(tokens);
                    if (!retorno) {
                        break;
                    }
                } //checando os prints
                else if (tokens.get(0).equals("tkPrint")) {
                    retorno = ChecaPrint(tokens);
                    if (!retorno) {
                        break;
                    }
                } //checando os FORs
                else if (tokens.get(0).equals("tkFor")) {
                    retorno = ChecaFor(tokens);
                    if (!retorno) {
                        break;
                    }
                } else if (ChecaComentarios(tokens)) {
                    retorno = true;
                    if (!retorno) {
                        break;
                    }

                } //checando se algum calculo é feito durante o codigo
                else if (tokens.get(0).equals("tkId")) {
                    retorno = ChecaAtribuicaoOuOperacao(tokens);
                    if (!retorno) {
                        break;
                    }

                }//checando se fechou o corpo do codigo 
                else if (tokens.get(0).equals("tkCloseFunc")) {
                    tokens.remove(0);
                    retorno = true;
                    break;
                }//Tratando se tiver um else solto no codigo
                else if (tokens.get(0).equals("tkElse")) {
                    tokenEncontrado = tokens.get(0);
                    tokenEsperado.add("tkIf");
                    //tokenEsperado.add("tkFor");
                    //tokenEsperado.add("tkPrint");          
                    tokens.remove(0);
                    retorno = false;
                    break;
                } else {
                    if (tokens.get(1).equals("tkId")) {
                        tokenEncontrado = tokens.get(0);
                        tokenEsperado.add("tkI");
                        tokenEsperado.add("tkR");
                    }
                    tokenEncontrado = tokens.get(0);
                    tokenEsperado.add("tkCloseFunc");
                    retorno = false;
                    break;
                }
            }
        } while (tokens.size() > 0);

        return retorno;
    }

    /**
     * 
     * @param tokens para serem analisados
     * @return Se abriu e fechou os comentarios
     */
    private boolean ChecaComentarios(ArrayList<String> tokens) {
        if (tokens.size() > 0) {
            if (tokens.get(0).equals("tkAbreComent")) {
                do {
                    //consumindo tudo que tiver dentro dos tokens de comentario
                    if (tokens.size() > 0) {
                        if (tokens.get(0).equals("tkFechaComent")) {
                            tokens.remove(0);
                            return true;
                        }
                        tokens.remove(0);
                    }
                } while (tokens.size() > 0);
            }
        }
        return false;
    }

    /**
     * 
     * @param tokens para serem analisados
     * @return Se a sintaxe do comando de exibir está correta
     */
    private boolean ChecaPrint(ArrayList<String> tokens) {
        boolean retorno = false;
        //consumindo o token de print
        tokens.remove(0);
        //checando se o conteudo a ser exibido é valido        
        if (tokens.size() > 0) {
            if (tokens.get(0).equals("tkId")) {
                tokens.remove(0);
                cont++;
                if (assembly) {
                    elementos.push(_variaveis.get(countVars));
                    countVars++;
                    generatedAssembly += ConvertOperationsToAssembly(AssemblyIds);
                    AssemblyIds++;
                }
            } else if (tokens.get(0).equals("tkInt") || tokens.get(0).equals("tkReal")) {
                tokens.remove(0);
                if (assembly) {
                    elementos.push(_variaveis.get(countVars));
                    countVars++;
                    generatedAssembly += ConvertOperationsToAssembly(AssemblyIds);
                    AssemblyIds++;
                }
            } else {
                retorno = false;
                tokenEncontrado = tokens.get(0);
                tokenEsperado.add("tkId");
                tokenEsperado.add("tkI");
                tokenEsperado.add("tkR");
            }
        }
        //vendo se terminou a linha do print
        if (tokens.size() > 0) {
            if (tokens.get(0).equals("tkEndLine")) {
                retorno = true;
                tokens.remove(0);
            } else {
                tokenEncontrado = tokens.get(0);
                tokenEsperado.add("tkEndLine");
            }
        }

        return retorno;
    }

    /**
     * 
     * @param tokens para serem analisados
     * @return Se a declaração da variavel está no padrao da linguagem
     */
    private boolean ChecaDeclaracao(ArrayList<String> tokens) {

        boolean retorno = false;
        //consumindo o token de declaração
        tokens.remove(0);
        if (tokens.size() > 0) {
            if (tokens.get(0).equals("tkId")) {
                if (assembly) {
                    countVars++;
                }
                tokens.remove(0);
                if (tokens.size() > 0) {
                    if (tokens.get(0).equals("tkEndLine")) {
                        tokens.remove(0);
                        retorno = true;
                    } else {
                        tokenEncontrado = tokens.get(0);
                        tokenEsperado.add("tkEndLine");
                        retorno = false;
                    }
                }
            } else {
                tokenEncontrado = tokens.get(0);
                tokenEsperado.add("tkId");
                retorno = false;
            }
        }
        return retorno;
    }

    /**
     * 
     * @param tokens para serem analisados
     * @return Se a sintaxe do If esta correta
     */
    private boolean ChecaIf(ArrayList<String> tokens) {
        boolean retorno = false;
        //consumindo token de if        
        tokens.remove(0);
        //checando se existe uma comparacao valida
        if (ChecaComparacao(tokens)) {
            //se abriu a função
            if (tokens.get(0).equals("tkOpenFunc")) {
                tokens.remove(0);
                //checando conteudo do if
                if (ChecaBlocoPrincipal(tokens)) {
                    retorno = true;
                    //se existe um else
                    if (tokens.get(0).equals("tkElse")) {
                        tokens.remove(0);
                        if (tokens.get(0).equals("tkOpenFunc")) {
                            tokens.remove(0);
                            //checando conteudo do else
                            retorno = ChecaBlocoPrincipal(tokens);
                        } else {
                            tokenEncontrado = tokens.get(0);
                            tokenEsperado.add("tkOpenFunc");
                            retorno = false;
                        }
                    }
                } else {
                    retorno = false;
                }
            } else {
                tokenEncontrado = tokens.get(0);
                tokenEsperado.add("tkOpenFunc");
                retorno = false;
            }
        }


        return retorno;
    }

    /**
     * 
     * @param Token a ser analisado
     * @return se o token analisado é para comparaçoes
     */
    private boolean ChecaOperadorComparacao(String get) {
        boolean retorno = false;
        //vendo se o token é um token de comparação
        if (get.equals("tkMinor")) {
            retorno = true;
        } else if (get.equals("tkGreater")) {
            retorno = true;
        } else if (get.equals("tkEquals")) {
            retorno = true;
        } else if (get.equals("tkGreatEq")) {
            retorno = true;
        } else if (get.equals("tkMinorEq")) {
            retorno = true;
        } else if (get.equals("tkDif")) {
            retorno = true;
        }
        return retorno;
    }

    /**
     * 
     * @param tokens para anlise
     * @return se o laço de repetição está no padrao da sintaxe
     */
    private boolean ChecaFor(ArrayList<String> tokens) {
        boolean retorno = false;
        //consumindo token for
        tokens.remove(0);
        //checando inicializacao do for
        if (ChecaAtribuicaoOuOperacao(tokens)) {
            //checando comparação de parada do for
            if (ChecaComparacao(tokens)) {
                if (tokens.get(0).equals("tkEndLine")) {
                    tokens.remove(0);
                    //checando incremento do for
                    if (ChecaAtribuicaoOuOperacao(tokens)) {
                        if (tokens.get(0).equals("tkOpenFunc")) {
                            tokens.remove(0);
                            //checando conteudo do for
                            retorno = ChecaBlocoPrincipal(tokens);
                        } else {
                            tokenEncontrado = tokens.get(0);
                            tokenEsperado.add("tkOpenFunc");
                            retorno = false;
                        }
                    }
                } else {
                    tokenEncontrado = tokens.get(0);
                    tokenEsperado.add("tkEndLine");
                    retorno = false;
                }
            }
        }


        return retorno;
    }

    /**
     * 
     * @param tokens para anlise
     * @return checa se é uma comparaçao de variaveis e/ou constantes
     */
    private boolean ChecaComparacao(ArrayList<String> tokens) {
        boolean retorno = false;

        if (tokens.size() > 0) {
            //checando se é uma variavel ou constante
            if (tokens.get(0).equals("tkInt") || tokens.get(0).equals("tkReal") || tokens.get(0).contains("tkId")) {
                if (assembly) {
                    elementos.push(_variaveis.get(countVars));
                    countVars++;
                }
                tokens.remove(0);
                if (tokens.size() > 0) {
                    //vendo se é um token de comparacao
                    if (ChecaOperadorComparacao(tokens.get(0))) {
                        if (assembly) {
                            elementos.push(tokens.get(0));
                        }
                        tokens.remove(0);
                        if (tokens.size() > 0) {
                            //checando se é outra variavel ou constante
                            if (tokens.get(0).equals("tkInt") || tokens.get(0).equals("tkReal") || tokens.get(0).contains("tkId")) {
                                if (assembly) {
                                    elementos.push(_variaveis.get(countVars));
                                    countVars++;
                                    generatedAssembly += ConvertOperationsToAssembly(AssemblyIds);
                                    AssemblyIds++;
                                }
                                tokens.remove(0);
                                retorno = true;
                            } else {
                                tokenEncontrado = tokens.get(0);
                                tokenEsperado.add("tkI");
                                tokenEsperado.add("tkR");
                                tokenEsperado.add("tkId");
                            }
                        }
                    } else {
                        tokenEncontrado = tokens.get(0);
                        ConcatenaOperadoresLogicos();
                    }
                }
            } else {
                tokenEncontrado = tokens.get(0);
                tokenEsperado.add("tkI");
                tokenEsperado.add("tkR");
                tokenEsperado.add("tkId");
                ConcatenaOperadoresLogicos();
            }

        }
        return retorno;
    }

    /**
     * 
     * @param tokens para analise
     * @return se a sintaxe de atribuição de valores ou soma de valores esta certa
     */
    private boolean ChecaAtribuicaoOuOperacao(ArrayList<String> tokens) {
        boolean retorno = false;

        if (tokens.size() > 1) {
            //checando se o proximo token é de atribuição
            if (!tokens.get(1).equals("tkAtrib")) {
                retorno = false;
                tokenEncontrado = tokens.get(0);
                tokenEsperado.add("tkAtrib");
            } else {
                //removendo o token var
                if (assembly) {
                    elementos.push(_variaveis.get(countVars));
                    countVars++;
                }
                tokens.remove(0);
                if (tokens.size() > 0) {
                    //removendo o token de atrib
                    tokens.remove(0);
                    if (tokens.size() > 0) {
                        //verifica se é constante ou variavel
                        if (tokens.get(0).equals("tkInt") || tokens.get(0).equals("tkReal") || tokens.get(0).contains("tkId")) {
                            if (assembly) {
                                elementos.push(_variaveis.get(countVars));
                                countVars++;
                            }
                            tokens.remove(0);
                            if (tokens.size() > 0) {
                                //verifica se esta sendo feita uma operacao com outra coisa
                                if (ChecaOperadorMatematico(tokens.get(0))) {
                                    if (assembly) {
                                        elementos.push(tokens.get(0));
                                    }
                                    tokens.remove(0);
                                    if (tokens.size() > 0) {
                                        //verifica o outro membro da operacao
                                        if (tokens.get(0).equals("tkInt") || tokens.get(0).equals("tkReal") || tokens.get(0).contains("tkId")) {
                                            if (assembly) {
                                                elementos.push(_variaveis.get(countVars));
                                                countVars++;
                                            }
                                            tokens.remove(0);
                                            if (tokens.size() > 0) {
                                                //verifica se terminou a linha
                                                if (tokens.get(0).equals("tkEndLine")) {
                                                    tokens.remove(0);
                                                    retorno = true;
                                                    if (assembly) {
                                                        generatedAssembly += ConvertOperationsToAssembly(AssemblyIds);
                                                        AssemblyIds++;
                                                    }
                                                } else {
                                                    tokenEncontrado = tokens.get(0);
                                                    tokenEsperado.add("tkEndLine");
                                                }
                                            }
                                        } else {
                                            tokenEncontrado = tokens.get(0);
                                            tokenEsperado.add("tkI");
                                            tokenEsperado.add("tkR");
                                            tokenEsperado.add("tkId");
                                        }
                                    }
                                }//se nao for uma operação, somente uma atribuição
                                //checa se terminou a linha 
                                else if (tokens.get(0).equals("tkEndLine")) {
                                    tokens.remove(0);
                                    retorno = true;
                                } else {
                                    tokenEncontrado = tokens.get(0);
                                    ConcatenaOperadoresMatematicos();
                                    tokenEsperado.add("tkEndLine");
                                }
                            }
                        } else {
                            tokenEncontrado = tokens.get(0);
                            tokenEsperado.add("tkI");
                            tokenEsperado.add("tkR");
                            tokenEsperado.add("tkId");
                        }
                    }
                }
            }
        }
        return retorno;
    }

    /**
     * 
     * @param token para anlise
     * @return se o token é um operador matematico
     */
    private boolean ChecaOperadorMatematico(String token) {
        boolean retorno = false;
        //checa se o token é um operador matematico
        if (token.equals("tkSoma")) {
            retorno = true;
        } else if (token.equals("tkSub")) {
            retorno = true;
        } else if (token.equals("tkMult")) {
            retorno = true;
        } else if (token.equals("tkDiv")) {
            retorno = true;
        }
        return retorno;
    }

    private void ConcatenaOperadoresMatematicos() {
        tokenEsperado.add("tkSoma");
        tokenEsperado.add("tkSub");
        tokenEsperado.add("tkMult");
        tokenEsperado.add("tkDiv");
    }

    private void ConcatenaOperadoresLogicos() {
        tokenEsperado.add("tkMinor");
        tokenEsperado.add("tkGreater");
        tokenEsperado.add("tkEquals");
        tokenEsperado.add("tkGreatEq");
        tokenEsperado.add("tkMinorEq");
        tokenEsperado.add("tkDif");
    }

    private String ConvertOperationsToAssembly(int cont) {

        String aux = "\r\n\r\n";
        //pra nao complicar criei aqui mesmo os responsaveis por serem os registradores
        //ao inves de adicionar a lista como estava na logica do renam. 
        //Espero que nao precise preencher mais nada deles alem do endereco.
        TokenValue ax = new TokenValue();
        ax.setAssemblyAddress(100);
        TokenValue bx = new TokenValue();
        bx.setAssemblyAddress(101);
        TokenValue cx = new TokenValue();
        cx.setAssemblyAddress(102);
        //_simbolos eh a minha tabela de simbolos da analise semantica
        //(cada variavel ou valor encontrado no meu codigo esta nessa tabela jah 
        //tambem com o endereco em assembly...assim cada vez que eu encontro uma 
        //eu substituo pelo endereco..entendeu? essas variaveis ax, bx... foram 
        //colocadas na tabela de simbolos pela propria analise e nao pelo programador...
        //elas nao sao vistas no codigo)
        //Variavel zx = (Variavel)_simbolos.GetVarByText("zx");//como esses registradores eu nao tenho no processador eu os criei na memoria

        if (elementos.height() == 1)// Exibir valor
        {
            TokenValue operando = (TokenValue) elementos.pop();
            aux += "org MIA_" + cont;
            aux += "\r\n";
            aux += "lda ac, " + operando.getEndereco();
            aux += "\r\n";
            aux += "str 4092, ac";
        } else if (elementos.height() == 3) // ifão
        {
            TokenValue operando2 = (TokenValue) elementos.pop();
            String operador = (String) elementos.pop();
            TokenValue operando1 = (TokenValue) elementos.pop();
            aux += "lda ac, " + operando1.getEndereco();
            aux += "\r\n";
            aux += "org CONDICAO_";
            aux += "\r\n";
            aux += "sub ac, " + operando2.getEndereco();
            aux += "\r\n";

            if ("tkEquals".equals(operador)) {
                aux += "je CODIGO_";
            } else if ("tkMinor".equals(operador)) {
                aux += "jl CODIGO_";
            } else if ("tkMinorEq".equals(operador)) {
                aux += "jl CODIGO_";
                aux += "\r\n";
                aux += "je CODIGO_";
            } else if ("tkGreater".equals(operador)) {
                aux += "jg CODIGO_";
            } else if ("tkGreaterEq".equals(operador)) {
                aux += "jg CODIGO_";
                aux += "\r\n";
                aux += "je CODIGO_";
            } else if ("tkDif".equals(operador)) {
                //alguma coisa
            }

        } else if (elementos.height() == 4)// Continhas !!!
        {
            TokenValue operando3 = (TokenValue) elementos.pop();
            String operador = (String) elementos.pop();
            TokenValue operando2 = (TokenValue) elementos.pop();
            TokenValue operando1 = (TokenValue) elementos.pop();
            if ("tkSoma".equals(operador)) {
                aux += "lda ac, " + operando3.getEndereco();
                aux += "\r\n";
                aux += "add ac, " + operando2.getEndereco();
                aux += "\r\n";
                aux += "str " + operando1.getEndereco() + ", ac";
            } else if ("tkSub".equals(operador)) {
                aux += "lda ac, " + operando2.getEndereco();
                aux += "\r\n";
                aux += "sub ac, " + operando3.getEndereco();
                aux += "\r\n";
                aux += "str " + operando1.getEndereco() + ", ac";
            } else if ("tkDiv".equals(operador)) {
                aux += "lda ac, " + operando1.getEndereco();
                aux += "\r\n";
                aux += "org DIV_" + cont;
                aux += "\r\n";
                aux += "je RES_" + cont;
                aux += "\r\n";
                aux += "jl RES_" + cont;
                aux += "\r\n";
                aux += "sub ac, " + operando3.getEndereco();
                aux += "\r\n";
                aux += "str " + ax.getEndereco() + ", ac";
                aux += "\r\n";
                aux += "lda ac, " + bx.getEndereco();
                aux += "\r\n";
                aux += "add ac, " + cx.getEndereco();
                aux += "\r\n";
                aux += "str " + bx.getEndereco() + ", ac";
                aux += "\r\n";
                aux += "lda ac, " + ax.getEndereco();
                aux += "\r\n";
                aux += "jmp DIV_" + cont;
                aux += "\r\n";
                aux += "org RES_" + cont;
                aux += "\r\n";
                aux += "lda ac, " + bx.getEndereco();
                aux += "\r\n";
                aux += "add ac, " + ax.getEndereco();
                aux += "\r\n";
                aux += "str " + operando1.getEndereco() + ", ac";
                aux += "\r\n";
                aux += "jmp ZERAR";
            } else if ("tkMult".equals(operador)) {
                aux += "lda ac, " + operando3.getEndereco();
                aux += "\r\n";
                aux += "org MUL_" + cont;
                aux += "\r\n";
                aux += "sub ac, " + cx.getEndereco();
                aux += "\r\n";
                aux += "str " + ax.getEndereco() + ", ac";
                aux += "\r\n";
                aux += "je RES_";
                aux += "\r\n";
                aux += "lda ac, " + operando1.getEndereco();
                aux += "\r\n";
                aux += "add ac, " + bx.getEndereco();
                aux += "\r\n";
                aux += "str " + bx.getEndereco() + ", ac";
                aux += "\r\n";
                aux += "lda ac, " + ax.getEndereco();
                aux += "\r\n";
                aux += "jmp MUL_" + cont;
                aux += "\r\n";
                aux += "org RES_" + cont;
                aux += "\r\n";
                aux += "lda ac, " + operando1.getEndereco();
                aux += "\r\n";
                aux += "add ac, " + bx.getEndereco();
                aux += "\r\n";
                aux += "str " + bx.getEndereco() + ", ac";
                aux += "\r\n";
                aux += "jmp ZERAR";
            }
        }
        elementos.clear();
        return aux;
    }
}
