package classes;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author distr
 */
public class TrasferimentoContattiTest {
    
    public TrasferimentoContattiTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of esportaContatto method, of class TrasferimentoContatti.
     */
    @Test
    public void testEsportaContatto() {
        System.out.println("esportaContatto");
        Map<Integer, Contatto> contatti = null;
        String filename = "";
        TrasferimentoContatti.esportaContatto(contatti, filename);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of importaContatto method, of class TrasferimentoContatti.
     */
    @Test
    public void testImportaContatto() {
        System.out.println("importaContatto");
        String filename = "";
        List<Contatto> expResult = null;
        List<Contatto> result = TrasferimentoContatti.importaContatto(filename);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
