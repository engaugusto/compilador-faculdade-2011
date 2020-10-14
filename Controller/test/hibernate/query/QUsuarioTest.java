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
 * @author Augusto
 */
public class QUsuarioTest {
    
    public QUsuarioTest() {
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
     * Test of list method, of class QUsuario.
     */
    @Test
    public void testList() {
        System.out.println("list");
        QUsuario instance = new QUsuario();
        instance.list();
    }

    /**
     * Test of insert method, of class QUsuario.
     */
    @Test
    public void testInsert() {
        //fail("");
        System.out.println("insert");
        String nome = "tst";
        String login = "tst";
        String pwd = "tst";
        QUsuario instance = new QUsuario();
        int expResult = -1;
        int result = instance.insert(nome, login, pwd);
        assertNotSame(expResult, result);
    }

    @Test
    public void testSelect(){
        System.out.println("Select");
        int user_id = 1;
        QUsuario instance = new QUsuario();
        Usuario retorno = null;
        retorno = instance.BuscaUsuario(user_id);
        assertNotNull(retorno);
    }
}
