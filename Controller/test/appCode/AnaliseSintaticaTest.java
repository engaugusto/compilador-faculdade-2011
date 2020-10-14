/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package appCode;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Queiroz
 */
public class AnaliseSintaticaTest {
    
    public AnaliseSintaticaTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Teste da analise padrão
     */
    @Test
    public void testAnalyse() {
        System.out.println("Analise Sintatica");
        ArrayList<String> tokens = new ArrayList<String>();
        tokens.add("tkMain");
            tokens.add("tkAbreComent");
            tokens.add("tkOpenFunc");        
            tokens.add("tkOpenFunc");        
            tokens.add("tkMain");
            tokens.add("tkFechaComent");
            tokens.add("tkOpenFunc");        
            tokens.add("tkI");
            tokens.add("tkId");
            tokens.add("tkEndLine");
        tokens.add("tkCloseFunc"); 
        
        AnaliseSintatica instance = new AnaliseSintatica();
        boolean expResult = true;
        boolean result = instance.Analyse(tokens);
        assertEquals(expResult, result);        
    }    
    
    /**
     * Teste de um fonte com erros
     */
    @Test
    public void testAnalyseFail() {
        System.out.println("Analise Codigo com Erro");
        ArrayList<String> tokens = new ArrayList<String>();
        tokens.add("tkMain");
            tokens.add("tkAbreComent");
            tokens.add("tkOpenFunc");        
            tokens.add("tkOpenFunc");        
            tokens.add("tkMain");
            tokens.add("tkFechaComent");
        tokens.add("tkOpenFunc");        
            tokens.add("tkInt");            
            tokens.add("tkEndLine");
        tokens.add("tkCloseFunc"); 
        
        AnaliseSintatica instance = new AnaliseSintatica();
        boolean expResult = false;
        boolean result = instance.Analyse(tokens);
        assertEquals(expResult, result);        
    }
    
    /**
     * Teste de um fonte com IF
     */
    @Test
     public void testAnalyseIF() {
        System.out.println("Analise IF");
        ArrayList<String> tokens = new ArrayList<String>();
        tokens.add("tkMain");
            tokens.add("tkAbreComent");
            tokens.add("tkOpenFunc");        
            tokens.add("tkOpenFunc");        
            tokens.add("tkMain");
            tokens.add("tkFechaComent");
        tokens.add("tkOpenFunc");        
            tokens.add("tkI"); 
            tokens.add("tkId");
            tokens.add("tkEndLine");
            tokens.add("tkIf");
                tokens.add("tkId");
                tokens.add("tkEquals");
                tokens.add("tkId");
                tokens.add("tkOpenFunc");
                    tokens.add("tkAbreComent");
                    tokens.add("tkOpenFunc");        
                    tokens.add("tkOpenFunc");        
                    tokens.add("tkMain");
                    tokens.add("tkFechaComent");
                tokens.add("tkCloseFunc");
        tokens.add("tkCloseFunc"); 
        
        AnaliseSintatica instance = new AnaliseSintatica();
        boolean expResult = true;
        boolean result = instance.Analyse(tokens);
        assertEquals(expResult, result);        
    }
    
     /**
     * Teste de um fonte com IF acabando pela metade
     */
    @Test
     public void testAnalyseIF2() {
        System.out.println("Analise IF com fim inesperado de arquivo");
        ArrayList<String> tokens = new ArrayList<String>();
        tokens.add("tkMain");
            tokens.add("tkAbreComent");
            tokens.add("tkOpenFunc");        
            tokens.add("tkOpenFunc");        
            tokens.add("tkMain");
            tokens.add("tkFechaComent");
        tokens.add("tkOpenFunc");        
            tokens.add("tkInt"); 
            tokens.add("tkId");
            tokens.add("tkEndLine");
            tokens.add("tkIf");
                tokens.add("tkId");
                tokens.add("tkEquals");
                /*tokens.add("tkId");
                tokens.add("tkOpenFunc");
                    tokens.add("tkAbreComent");
                    tokens.add("tkOpenFunc");        
                    tokens.add("tkOpenFunc");        
                    tokens.add("tkMain");
                    tokens.add("tkFechaComent");
                tokens.add("tkCloseFunc");
        tokens.add("tkCloseFunc");*/ 
        
        AnaliseSintatica instance = new AnaliseSintatica();
        boolean expResult = false;
        boolean result = instance.Analyse(tokens);
        assertEquals(expResult, result);        
    }
    
     /**
     * Teste de um fonte com laço de repetição
     */
    @Test
     public void testAnalyseFor() {
        System.out.println("Analise For");
        ArrayList<String> tokens = new ArrayList<String>();
        tokens.add("tkMain");
            tokens.add("tkAbreComent");
            tokens.add("tkOpenFunc");        
            tokens.add("tkOpenFunc");        
            tokens.add("tkMain");
            tokens.add("tkFechaComent");
        tokens.add("tkOpenFunc");        
            tokens.add("tkI"); 
            tokens.add("tkId");
            tokens.add("tkEndLine");
            tokens.add("tkIf");
                tokens.add("tkId");
                tokens.add("tkEquals");
                tokens.add("tkId");
                tokens.add("tkOpenFunc");
                    tokens.add("tkAbreComent");
                    tokens.add("tkOpenFunc");        
                    tokens.add("tkOpenFunc");        
                    tokens.add("tkMain");
                    tokens.add("tkFechaComent");
                tokens.add("tkCloseFunc");
            tokens.add("tkFor");
            tokens.add("tkId");
            tokens.add("tkAtrib");
            tokens.add("tkInt");
            tokens.add("tkEndLine");
            tokens.add("tkId");
            tokens.add("tkMinor");
            tokens.add("tkInt");
            tokens.add("tkEndLine");
            tokens.add("tkId");
            tokens.add("tkAtrib");
            tokens.add("tkId");
            tokens.add("tkSoma");
            tokens.add("tkInt");
            tokens.add("tkEndLine");
            tokens.add("tkOpenFunc");
                tokens.add("tkIf");
                    tokens.add("tkId");
                    tokens.add("tkEquals");
                    tokens.add("tkId");
                    tokens.add("tkOpenFunc");
                        tokens.add("tkAbreComent");
                        tokens.add("tkOpenFunc");        
                        tokens.add("tkOpenFunc");        
                        tokens.add("tkMain");
                        tokens.add("tkFechaComent");
                    tokens.add("tkCloseFunc");
            tokens.add("tkCloseFunc");
        tokens.add("tkCloseFunc");
        
        AnaliseSintatica instance = new AnaliseSintatica();
        boolean expResult = true;
        boolean result = instance.Analyse(tokens);
        assertEquals(expResult,result);
    }
    
     /**
     * Teste de um fonte com laço de repetição acabando de repente
     */
    @Test
     public void testAnalyseFor2() {
        System.out.println("Analise For com fim inesperado do arquivo");
        ArrayList<String> tokens = new ArrayList<String>();
        tokens.add("tkMain");
            tokens.add("tkAbreComent");
            tokens.add("tkOpenFunc");        
            tokens.add("tkOpenFunc");        
            tokens.add("tkMain");
            tokens.add("tkFechaComent");
        tokens.add("tkOpenFunc");        
            tokens.add("tkInt"); 
            tokens.add("tkId");
            tokens.add("tkEndLine");
            tokens.add("tkIf");
                tokens.add("tkId");
                tokens.add("tkEquals");
                tokens.add("tkId");
                tokens.add("tkOpenFunc");
                    tokens.add("tkAbreComent");
                    tokens.add("tkOpenFunc");        
                    tokens.add("tkOpenFunc");        
                    tokens.add("tkMain");
                    tokens.add("tkFechaComent");
                tokens.add("tkCloseFunc");
            tokens.add("tkFor");
            tokens.add("tkId");
            /*tokens.add("tkAtrib");
            tokens.add("tkI");
            tokens.add("tkEndLine");
            tokens.add("tkId");
            tokens.add("tkMinor");
            tokens.add("tkI");
            tokens.add("tkEndLine");
            tokens.add("tkId");
            tokens.add("tkAtrib");
            tokens.add("tkId");
            tokens.add("tkSoma");
            tokens.add("tkI");
            tokens.add("tkEndLine");
            tokens.add("tkOpenFunc");
                tokens.add("tkIf");
                    tokens.add("tkId");
                    tokens.add("tkEquals");
                    tokens.add("tkId");
                    tokens.add("tkOpenFunc");
                        tokens.add("tkAbreComent");
                        tokens.add("tkOpenFunc");        
                        tokens.add("tkOpenFunc");        
                        tokens.add("tkMain");
                        tokens.add("tkFechaComent");
                    tokens.add("tkCloseFunc");
            tokens.add("tkCloseFunc");
        tokens.add("tkCloseFunc");*/ 
        
        AnaliseSintatica instance = new AnaliseSintatica();
        boolean expResult = false;
        boolean result = instance.Analyse(tokens);
        assertEquals(expResult, result);        
    }
    
    /**
     * Teste do comando print
     */
    @Test
    public void testPrint() {
        System.out.println("Analise Print");
        ArrayList<String> tokens = new ArrayList<String>();
        tokens.add("tkMain");
            tokens.add("tkAbreComent");
            tokens.add("tkOpenFunc");        
            tokens.add("tkOpenFunc");        
            tokens.add("tkMain");
            tokens.add("tkFechaComent");
        tokens.add("tkOpenFunc");        
            tokens.add("tkI");
            tokens.add("tkId");
            tokens.add("tkEndLine");
            tokens.add("tkPrint");
                tokens.add("tkId");
                //tokens.add("tkId");
                //tokens.add("tkId");
            tokens.add("tkEndLine");
        tokens.add("tkCloseFunc"); 
        
        AnaliseSintatica instance = new AnaliseSintatica();
        boolean expResult = true;
        boolean result = instance.Analyse(tokens);
        assertEquals(expResult, result);        
    }
    
     /**
     * Teste do comando atribuicao
     */
    @Test
    public void testOperacao() {
        System.out.println("Analise Atribuição");
        ArrayList<String> tokens = new ArrayList<String>();
        tokens.add("tkMain");
            tokens.add("tkAbreComent");
            tokens.add("tkOpenFunc");        
            tokens.add("tkOpenFunc");        
            tokens.add("tkMain");
            tokens.add("tkFechaComent");
        tokens.add("tkOpenFunc");        
            tokens.add("tkI");
            tokens.add("tkId");
            tokens.add("tkEndLine");
            tokens.add("tkI");
            tokens.add("tkId");
            tokens.add("tkEndLine");            
            tokens.add("tkId");
            tokens.add("tkAtrib");
            tokens.add("tkId");
            tokens.add("tkEndLine");
        tokens.add("tkCloseFunc"); 
        
        AnaliseSintatica instance = new AnaliseSintatica();
        boolean expResult = true;
        boolean result = instance.Analyse(tokens);
        assertEquals(expResult, result);        
    }
    
    /**
     * Teste do comando atribuicao com calculo
     */
    @Test
    public void testOperacao2() {
        System.out.println("Analise Calculo");
        ArrayList<String> tokens = new ArrayList<String>();
        tokens.add("tkMain");
            tokens.add("tkAbreComent");
            tokens.add("tkOpenFunc");        
            tokens.add("tkOpenFunc");        
            tokens.add("tkMain");
            tokens.add("tkFechaComent");
        tokens.add("tkOpenFunc");        
            tokens.add("tkInt");
            tokens.add("tkId");
            tokens.add("tkEndLine");
            tokens.add("tkInt");
            tokens.add("tkId");            
            tokens.add("tkEndLine");
            tokens.add("tkId");
            tokens.add("tkAtrib");
            tokens.add("tkId");
            tokens.add("tkSoma");
            tokens.add("tkI");
            tokens.add("tkEndLine");
        tokens.add("tkCloseFunc"); 
        
        AnaliseSintatica instance = new AnaliseSintatica();
        boolean expResult = true;
        boolean result = instance.Analyse(tokens);
        //assertEquals(expResult, result);
    }
}
