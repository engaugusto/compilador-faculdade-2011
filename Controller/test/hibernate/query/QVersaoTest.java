/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.query;

import db.Usuario;
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
public class QVersaoTest {
    
    public QVersaoTest() {
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
     * Test of insert method, of class QVersao.
     */
    @Test
    public void testInsert() {
        System.out.println("insert");
        String codigo = "Teste de codigo !!";
        int user_id = 1;
        Usuario usr = null;
        QVersao instance = new QVersao();
        QUsuario instance2 = new QUsuario();
        usr = instance2.BuscaUsuario(user_id);
        int expResult = -1;
        int result = instance.GravarVersao(codigo, usr);
        assertNotSame(expResult, result);               
    }
}
