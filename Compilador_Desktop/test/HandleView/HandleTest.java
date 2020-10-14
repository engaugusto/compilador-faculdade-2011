/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package HandleView;

import model.vmTela_Compilador;
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
public class HandleTest {

    public HandleTest() {
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
     * Test of getTela method, of class Handle.
     */
    @Test
    public void testGetTela() {
        System.out.println("getTela");
        Handle instance = new Handle();
        vmTela_Compilador expResult = null;
        vmTela_Compilador result = instance.getTela();
        assertEquals(expResult, result);
    }

    /**
     * Test of Analise method, of class Handle.
     */
    @Test
    public void testAnalise() throws Exception {
        System.out.println("Analise");
        String Codigo = "Estarte\r\n$\r\n_$\r\n";
        String Output = "";
        String Debug = "";
        boolean AnaliseLexica = true;
        boolean AnaliseSemantica = false;
        boolean AnaliseSintatica = false;
        boolean Assembly = false;
        Handle instance = new Handle();
        instance.Analise(Codigo, Output, Debug, AnaliseLexica, AnaliseSemantica, AnaliseSintatica, Assembly);

        assertEquals(instance.getTela().getCodigo(), Codigo);
        assertTrue(instance.getTela().getOutput().contains("Sucesso !"));
        assertTrue(instance.getTela().getOutput().contains("tkMain"));
        assertTrue(instance.getTela().getOutput().contains("tkOpenFunc"));
        assertTrue(instance.getTela().getOutput().contains("tkCloseFunc"));
        assertEquals(instance.getTela().getDebug(), "");
    }

    /**
     * Teste da analise quando ela encontra tokens invalidos
     * @throws Exception
     */
    @Test
    public void testAnaliseFail() throws Exception {
        System.out.println("Analise com token errado");
        String Codigo = "Estarte\r\n$asdasdasd\r\n_$\r\n";
        String Output = "";
        String Debug = "";
        boolean AnaliseLexica = true;
        boolean AnaliseSemantica = false;
        boolean AnaliseSintatica = false;
        boolean Assembly = false;
        Handle instance = new Handle();
        instance.Analise(Codigo, Output, Debug, AnaliseLexica, AnaliseSemantica, AnaliseSintatica, Assembly);
        assertTrue(instance.getTela().getOutput().contains("Erro"));
        assertTrue(instance.getTela().getOutput().contains("tkMain"));
        assertTrue(instance.getTela().getOutput().contains("tkOpenFunc"));
        assertTrue(instance.getTela().getOutput().contains("tkCloseFunc"));
        assertEquals(instance.getTela().getDebug(), "");
    }
}
