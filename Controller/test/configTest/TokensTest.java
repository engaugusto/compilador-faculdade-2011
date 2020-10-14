/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package configTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import config.*;

/**
 *
 * @author Carlos Augusto
 */
public class TokensTest {
    
    public TokensTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void readTokens(){
        Tokens t = new Tokens();
        t.readFile();
        if (t.getTokens().size() > 0){
            assertEquals(true, true);
        }else
            assertEquals(true, false);
    }
}
