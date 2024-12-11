package classes;

import java.util.Arrays;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author distr
 */
public class RubricaTest {
    Rubrica r;
    
    public RubricaTest() {
    }
    
    /*
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    */
    @BeforeEach
    public void setUp() {
       r = new Rubrica();
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of aggiungiContattoRubrica method, of class Rubrica.
     */
    @Test
    public void testAggiungiContattoRubrica1() {
        String nome = "Michele";
        String cognome = "Adinolfi";
        String numeriTelefono[] = {"343434", "43434343", "44444"};
        String indirizziEmail[] = {"dadada@gmail.com", "dadadadadadad@gmail.com"};
        
        String expectedValori[] = {nome, cognome, Arrays.toString(numeriTelefono), Arrays.toString(indirizziEmail)};
        
        r.aggiungiContattoRubrica("Michele", "Adinolfi", new String[]{"343434", "43434343", "44444"}, new String[]{"dadada@gmail.com", "dadadadadadad@gmail.com"});
        
        Contatto c = r.getContatti().get(1);
        String actualValori[] = {c.getNome(), c.getCognome(), Arrays.toString(c.getNumeriTelefono()), Arrays.toString(c.getIndirizziEmail())};
        assertArrayEquals(expectedValori, actualValori);
    }
    @Test
    public void testAggiungiContattoRubrica2() {
        String nome = "Michele";
        String cognome = "Adinolfi";
        String numeriTelefono[] = {"343434", "43434343", "44444"};
        String indirizziEmail[] = {"dadada@gmail.com", "dadadadadadad@gmail.com", "adada@gmail.com"};
        
        String expectedValori[] = {nome, cognome, Arrays.toString(numeriTelefono), Arrays.toString(indirizziEmail)};
        
        r.aggiungiContattoRubrica("Michele", "Adinolfi", new String[]{"343434", "43434343", "44444", "44444"}, new String[]{"dadada@gmail.com", "dadadadadadad@gmail.com", "adada@gmail.com", "adadada@gmail.com"});
        Contatto c = r.getContatti().get(2);
        String actualValori[] = {c.getNome(), c.getCognome(), Arrays.toString(c.getNumeriTelefono()), Arrays.toString(c.getIndirizziEmail())};
        
        assertArrayEquals(expectedValori, actualValori);
    }
    @Test
    public void testAggiungiContattoRubrica3(){
        RuntimeException e = assertThrows(RuntimeException.class, () -> r.aggiungiContattoRubrica("", "Adinolfi", new String[]{"343434", "43434343", "44444", "44444"}, new String[]{"dadada@gmail.com", "dadadadadadad@gmail.com", "adada@gmail.com", "adadada@gmail.com"}));
        assertTrue(e.getMessage().contains("No"));
    }
    
    
    /**
     * Test of aggiungiContattoFile method, of class Rubrica.
     */
     /*
    @Test
    public void testAggiungiContattoFile() {
        System.out.println("aggiungiContattoFile");
        String filename = "";
        Rubrica instance = new Rubrica();
        instance.aggiungiContattoFile(filename);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
     */
    /**
     * Test of modificaContatto method, of class Rubrica.
     */
     /*
    @Test
    public void testModificaContatto() {
        System.out.println("modificaContatto");
        String nome = "";
        String cognome = "";
        String[] numeriTelefono = null;
        String[] indirizziEmail = null;
        int ID = 0;
        Rubrica instance = new Rubrica();
        instance.modificaContatto(nome, cognome, numeriTelefono, indirizziEmail, ID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
     */
    /**
     * Test of rimuoviContatto method, of class Rubrica.
     */
     /*
    @Test
    public void testRimuoviContatto() {
        System.out.println("rimuoviContatto");
        Contatto c = null;
        Rubrica instance = new Rubrica();
        instance.rimuoviContatto(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getContatti method, of class Rubrica.
     */
     /*
    @Test
    public void testGetContatti() {
        System.out.println("getContatti");
        Rubrica instance = new Rubrica();
        Map<Integer, Contatto> expResult = null;
        Map<Integer, Contatto> result = instance.getContatti();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eseguiRicerca method, of class Rubrica.
     */
     /*
    @Test
    public void testEseguiRicerca() {
        
        System.out.println("eseguiRicerca");
        String searchValue = "";
        Rubrica instance = new Rubrica();
        List<Contatto> expResult = null;
        List<Contatto> result = instance.eseguiRicerca(searchValue);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of applicaOrdinamento method, of class Rubrica.
     */
     /*
    @Test
    public void testApplicaOrdinamento() {
        System.out.println("applicaOrdinamento");
        Ordinamento o = null;
        Rubrica instance = new Rubrica();
        instance.applicaOrdinamento(o);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */
}
