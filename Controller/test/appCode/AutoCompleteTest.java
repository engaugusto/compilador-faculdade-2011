/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package appCode;

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
public class AutoCompleteTest {
    
    public AutoCompleteTest() {
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
    public void testReturnAutoComplete() {
        String word = "d";
        AutoComplete aC = new AutoComplete();        
        for(String s : aC.ReturnAutoComplete(word)){
            System.out.println(s);
        }
    }
}
