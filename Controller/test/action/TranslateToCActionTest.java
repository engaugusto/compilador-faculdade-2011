/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.util.ArrayList;
import model.TokenValue;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Admin
 */
public class TranslateToCActionTest {
    
    public TranslateToCActionTest() {
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
     * Test of Translate method, of class TranslateToCAction.
     */
    @Test
    public void testTranslate() {
        System.out.println("Translate");
        ArrayList<String> tokens = new ArrayList<String>();
        ArrayList<TokenValue> tokenV = new ArrayList<TokenValue>();
        
        tokens.add("tkMain");
        tokens.add("tkOpenFunc");
        
        tokens.add("tkI");
        tokens.add("tkId");
        tokenV.add(new TokenValue("i", TokenValue.Tipo.INTEGER, null, true));
        tokens.add("tkEndLine");
        
        tokens.add("tkFor");
        tokens.add("tkId");
        tokenV.add(new TokenValue("i", TokenValue.Tipo.INTEGER, null, false));
        tokens.add("tkAtrib");
        tokens.add("tkInt");
        tokenV.add(new TokenValue("0", TokenValue.Tipo.INTEGER, 0, true));
        tokens.add("tkEndLine");
        tokens.add("tkId");
        tokenV.add(new TokenValue("i", TokenValue.Tipo.INTEGER, null, true));
        tokens.add("tkGreater");
        tokens.add("tkInt");
        tokenV.add(new TokenValue("10", TokenValue.Tipo.INTEGER, 10, true));
        tokens.add("tkEndLine");
        tokens.add("tkId");
        tokenV.add(new TokenValue("i", TokenValue.Tipo.INTEGER, null, true));
        tokens.add("tkAtrib");
        tokens.add("tkId");
        tokenV.add(new TokenValue("i", TokenValue.Tipo.INTEGER, null, true));
        tokens.add("tkSoma");
        tokens.add("tkInt");
        tokenV.add(new TokenValue("1", TokenValue.Tipo.INTEGER, 1, true));
        tokens.add("tkOpenFunc");
        tokens.add("tkCloseFunc");
        tokens.add("tkCloseFunc");
        
        TranslateToCAction instance = new TranslateToCAction();
        String expResult =  "#include<stdio.h>\r\n"+
                            "#include<stdlib.h>\r\n"+
                            "\r\n"+
                            "int main()\r\n"+
                            "{\r\n"+
                            "int\r\n"+
                            "i;\r\n"+
                            "for (\r\n"+
                            "i=\r\n"+
                            "0;\r\n"+
                            "i>\r\n"+
                            "10;\r\n"+
                            "i=\r\n"+
                            "i+\r\n"+
                            "1){\r\n"+
                            "}\r\n"+
                            "}\r\n"+
                            "\r\n";


        String result = instance.Translate(tokens,tokenV);
        
        System.out.println(result);
        System.out.println("Expected:\r\n"+expResult);
        assertEquals(expResult, result);
        
        
    }
}
