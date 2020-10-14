/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package appCode;

import appCode.AnaliseLexica;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Carlos Augusto
 */
public class analiseLexicaTest {
    
    public analiseLexicaTest() {
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

    @Test
    public void testAnalyse(){
        System.out.println("--Analyse");
        AnaliseLexica instance = new AnaliseLexica();
        ArrayList<String> tokens = new ArrayList<String>();
        
        tokens.add("tkMain");
        tokens.add("tkIf");
        tokens.add("tkOpenFunc");
        tokens.add("tkElse");
        tokens.add("tkI");
        tokens.add("tkR");
        tokens.add("tkInt");
        tokens.add("tkReal");
        tokens.add("tkId");
        tokens.add("tkCloseFunc");
        
        String text = "Estarte\r\n";//"  Estarte() {\r\n";
        text += "sepa $\r\n";
        text += "ehnao i r  121 1221.12 var\r\n";
        text += " _$";
        boolean result = false;
        try {
            result = instance.Analyse(text);
        } catch (Exception ex) {
            Logger.getLogger(analiseLexicaTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        int i = 0;
        for(String s : instance.getTokens()){
            System.out.println("Analyse Tokens: " + s);            
            assertEquals(tokens.get(i),s);
            i++;
        }
        
        
        
        assertEquals(true, result);
    }
    
    @Test
    public void test_NULL_Maldito_Analyse(){
        System.out.println("--Analyse Nullos malditos");
        AnaliseLexica instance = new AnaliseLexica();
        String text = "Estarte\r\n";//"  Estarte() {\r\n";
        text += "sepa $\r\n";
        text += "pqp <- a12* \r\n   121 1221.12 var\r\n";
        text += " _$";
        boolean result = false;
        try {
            result = instance.Analyse(text);
        } catch (Exception ex) {
            Logger.getLogger(analiseLexicaTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(String s : instance.getTokens()){
            System.out.println("Token: " + s);
        }
        assertEquals(true, result);
    }
}
