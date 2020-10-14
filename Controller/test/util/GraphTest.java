/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Hashtable;
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
public class GraphTest {
    
    public GraphTest() {
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
     * Test of getNodes method, of class Graph.
     */
    @Test
    public void testGetNodes() {
        System.out.println("getNodes");
        Graph instance = new Graph();
        Hashtable result = instance.getNodes();
        assertNotNull(result);

    }

    /**
     * Test of Find method, of class Graph.
     */
    @Test
    public void testFind() {
        System.out.println("Find");
        String name = "test";
        Graph instance = new Graph();
        instance.AddNode(name);
        Node result = instance.Find(name);
        assertEquals(result.getInfo(), name);
    }

    /**
     * Test of AddArc method, of class Graph.
     */
    @Test
    public void testAddArc_String_String() {
        System.out.println("AddArc");
        String from = "nFrom";
        String to = "nTo";
        Graph instance = new Graph();
        instance.AddNode(from);
        instance.AddNode(to);
        instance.AddArc(from, to);
        assertEquals(instance.Find(from).getArcs().get(0).getTarget().getInfo(), to);

    }

    /**
     * Test of AddArc method, of class Graph.
     */
    @Test
    public void testAddArc_3args() {
        System.out.println("AddArc");
        String from = "nFrom";
        String to = "nTo";
        Graph instance = new Graph();
        instance.AddNode(from);
        instance.AddNode(to);
        instance.AddArc(from, to, 1);
        assertEquals(instance.Find(from).getArcs().get(0).getWeigth(), 1);
    }

    /**
     * Test of AddNode method, of class Graph.
     */
    @Test
    public void testAddNode() {
        System.out.println("AddNode");
        String info = "test";
        Graph instance = new Graph();
        instance.AddNode(info);

        assertTrue(instance.getNodes().size() > 0);
    }

    /**
     * Test of ClearNodeInfo method, of class Graph.
     */
    @Test
    public void testClearNodeInfo() {
        System.out.println("ClearNodeInfo");
        Graph instance = new Graph();
        String info = "test";
        instance.AddNode(info);
        instance.getNodes().get(info).setMarked(true);
        assertEquals(true, instance.getNodes().get(info).getMarked());
        instance.ClearNodeInfo();
        assertEquals(false, instance.getNodes().get(info).getMarked());

        //fail("The test case is a prototype.");
    }
}
