package classes;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

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
       Contatto.setNumeroSequenziale(0);
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
        Contatto c = r.getContatti().get(1);
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
    
    @Test
    public void testAggiungiContattoFile() {
        String filename = "file.csv";
        r.aggiungiContattoRubrica("Michele", "Adinolfi", new String[]{"343434", "43434343", "44444", "44444"}, new String[]{"dadada@gmail.com", "dadadadadadad@gmail.com", "adada@gmail.com", "adadada@gmail.com"});
        r.esportaRubricaFile(filename);
        r.aggiungiContattoFile(filename);
        assertEquals(r.getContatti().get(1).toString(), r.getContatti().get(2).toString());
    }
     
    /**
     * Test of modificaContatto method, of class Rubrica.
     */
    
    @Test
    public void testModificaContatto1() {
        String nome = "Mario";
        String cognome = "D'Acunto";
        String numeriTelefono[] = {"2223333444", "77777"};
        String indirizziEmail[] = {"aaa@gmail.com", "ccc@gmail.com"};
        String numeriTelefonoModificati[] = {"222333344455", "7777788", "99999", "54232"};
        String indirizziEmailModificati[] = {"aaab@gmail.com", "cccd@gmail.com", "pdf@gmail.com", "azerbaijan@gmail.com"};
        
        String expectedValori[] = {"Michele", "Adinolfi", Arrays.toString(new String[]{"222333344455", "7777788", "99999"}), Arrays.toString(new String[]{"aaab@gmail.com", "cccd@gmail.com", "pdf@gmail.com"})};
        
        r.aggiungiContattoRubrica(nome, cognome, numeriTelefono, indirizziEmail);
        
        r.modificaContatto("Michele", "Adinolfi", numeriTelefonoModificati , indirizziEmailModificati, 1);
        
        Contatto c = r.getContatti().get(1);
        String actualValori[] = {c.getNome(), c.getCognome(), Arrays.toString(c.getNumeriTelefono()), Arrays.toString(c.getIndirizziEmail())};
        assertArrayEquals(expectedValori, actualValori);
    }
    @Test
    public void testModificaContatto2() {
        String nome = "Mario";
        String cognome = "D'Acunto";
        String numeriTelefono[] = {"2223333444", "77777"};
        String indirizziEmail[] = {"aaa@gmail.com", "ccc@gmail.com"};
        String numeriTelefonoModificati[] = {"22333344455"};
        String indirizziEmailModificati[] = {"aaab@gmail.com"};
        
        String expectedValori[] = {"Michele", "", Arrays.toString(numeriTelefonoModificati), Arrays.toString(indirizziEmailModificati)};
        
        r.aggiungiContattoRubrica(nome, cognome, numeriTelefono, indirizziEmail);
        
        r.modificaContatto("Michele", "", new String[]{"22333344455"} , new String[]{"aaab@gmail.com"}, 1);
        
        Contatto c = r.getContatti().get(1);
        String actualValori[] = {c.getNome(), c.getCognome(), Arrays.toString(c.getNumeriTelefono()), Arrays.toString(c.getIndirizziEmail())};
        assertArrayEquals(expectedValori, actualValori);
    }
    @Test
    public void testModificaContatto3() {
        String nome = "Mario";
        String cognome = "D'Acunto";
        String numeriTelefono[] = {"2223333444", "77777"};
        String indirizziEmail[] = {"aaa@gmail.com", "ccc@gmail.com"};
        
        r.aggiungiContattoRubrica(nome, cognome, numeriTelefono, indirizziEmail);
        RuntimeException e = assertThrows(RuntimeException.class, () -> r.modificaContatto("", "Adinolfi", new String[]{"343434", "43434343", "44444", "44444"}, new String[]{"dadada@gmail.com", "dadadadadadad@gmail.com", "adada@gmail.com", "adadada@gmail.com"}, 1));
        assertTrue(e.getMessage().contains("No"));
    }
    
    /**
     * Test of rimuoviContatto method, of class Rubrica.
     */
    @Test
    public void testRimuoviContatto1() {
        String numeriTelefono[] = {"343434", "43434343", "44444"};
        String indirizziEmail[] = {"dadada@gmail.com", "dadadadadadad@gmail.com"};
        
        Contatto c1 = new Contatto("Michele", "Adinolfi", numeriTelefono, indirizziEmail);        
        Contatto[] cArray = new Contatto[]{c1};
        //Contatto c2 = new Contatto("Mario", "D'acunto", numeriTelefono, indirizziEmail);
        //expectedLista.put(c2.getID(), c2);
        
        Contatto.setNumeroSequenziale(0);
        
        r.aggiungiContattoRubrica("Michele", "Adinolfi", numeriTelefono, indirizziEmail);
        r.aggiungiContattoRubrica("Mario", "D'acunto", numeriTelefono, indirizziEmail);
        r.rimuoviContatto(2);
        
        assertEquals(Arrays.toString(cArray), Arrays.toString(r.getContatti().values().toArray()));
    }
    @Test
    public void testRimuoviContatto2() {
        String numeriTelefono[] = {"343434", "43434343", "44444"};
        String indirizziEmail[] = {"dadada@gmail.com", "dadadadadadad@gmail.com"};
        
        r.aggiungiContattoRubrica("Michele", "Adinolfi", numeriTelefono, indirizziEmail);
        r.aggiungiContattoRubrica("Mario", "D'acunto", numeriTelefono, indirizziEmail);
        
        RuntimeException e = assertThrows(RuntimeException.class, () -> r.rimuoviContatto(4));
        assertTrue(e.getMessage().contains("Non esiste tale contatto"));
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
