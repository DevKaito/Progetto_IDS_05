package classes;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import enumerators.Ordinamento;

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
     
    @Test
    public void testEseguiRicerca1(){
        String numeriTelefono[] = {"343434", "43434343", "44444"};
        String indirizziEmail[] = {"dadada@gmail.com", "dadadadadadad@gmail.com"};
        
        r.aggiungiContattoRubrica("Michele", "Adinolfi", numeriTelefono, indirizziEmail);
        r.aggiungiContattoRubrica("Mario", "D'acunto", numeriTelefono, indirizziEmail);
        r.aggiungiContattoRubrica("Giovanni", "Del Nero", numeriTelefono, indirizziEmail);

        List <Contatto> risultati = r.eseguiRicerca("M");
        assertEquals(2, risultati.size());
        
    }

    @Test
    public void testEseguiRicerca2(){
        String numeriTelefono[] = {"343434", "43434343", "44444"};
        String indirizziEmail[] = {"dadada@gmail.com", "dadadadadadad@gmail.com"};
        
        r.aggiungiContattoRubrica("Michele", "Adinolfi", numeriTelefono, indirizziEmail);
        r.aggiungiContattoRubrica("Mario", "d'acunto", numeriTelefono, indirizziEmail);
        r.aggiungiContattoRubrica("Giovanni", "DEL NERO", numeriTelefono, indirizziEmail);

        List <Contatto> risultati = r.eseguiRicerca("D'ACUNTO");
        assertEquals(1, risultati.size());
        
    }

    @Test
    public void testEseguiRicerca3(){
        String numeriTelefono[] = {"343434", "43434343", "44444"};
        String indirizziEmail[] = {"dadada@gmail.com", "dadadadadadad@gmail.com"};
        
        r.aggiungiContattoRubrica("Michele", "Adinolfi", numeriTelefono, indirizziEmail);
        r.aggiungiContattoRubrica("Mario", "d'acunto", numeriTelefono, indirizziEmail);
        r.aggiungiContattoRubrica("Giovanni", "DEL NERO", numeriTelefono, indirizziEmail);

        ByteArrayOutputStream error = new ByteArrayOutputStream();
        System.setErr(new PrintStream(error));

        List <Contatto> risultati = r.eseguiRicerca("Di lieto");
        assertTrue(risultati.isEmpty());

        String messaggioAtteso = "Nessun risultato per 'Di lieto'";
        assertTrue(error.toString().contains(messaggioAtteso));

        System.setErr(System.err);
        
    }

    /**
     * Test of applicaOrdinamento method, of class Rubrica.
     */
     
    @Test
    public void testApplicaOrdinamento1() {
        String numeriTelefono[] = {"343434", "43434343", "44444"};
        String indirizziEmail[] = {"dadada@gmail.com", "dadadadadadad@gmail.com"};
        
        r.aggiungiContattoRubrica("Michele", "Adinolfi", numeriTelefono, indirizziEmail);
        r.aggiungiContattoRubrica("Mario", "d'acunto", numeriTelefono, indirizziEmail);
        r.aggiungiContattoRubrica("Giovanni", "DEL NERO", numeriTelefono, indirizziEmail);
        r.aggiungiContattoRubrica("Michele", "Kaito", numeriTelefono, indirizziEmail);

        r.applicaOrdinamento(Ordinamento.OrdineAlfabetico);

        List<Contatto> risultati = new ArrayList<>(r.getContatti().values());

        assertEquals("Giovanni", risultati.get(0).getNome());
        assertEquals("Mario", risultati.get(1).getNome());
        assertEquals("Michele", risultati.get(2).getNome());
        assertEquals("Michele", risultati.get(3).getNome());

        assertEquals("Adinolfi", risultati.get(2).getCognome());
        assertEquals("Kaito", risultati.get(3).getCognome());
    }

    @Test
    public void testApplicaOrdinamento2() {
        String numeriTelefono[] = {"343434", "43434343", "44444"};
        String indirizziEmail[] = {"dadada@gmail.com", "dadadadadadad@gmail.com"};
        
        r.aggiungiContattoRubrica("Michele", "Adinolfi", numeriTelefono, indirizziEmail);
        r.aggiungiContattoRubrica("Mario", "d'acunto", numeriTelefono, indirizziEmail);
        r.aggiungiContattoRubrica("Giovanni", "DEL NERO", numeriTelefono, indirizziEmail);
        r.aggiungiContattoRubrica("Michele", "Kaito", numeriTelefono, indirizziEmail);

        r.applicaOrdinamento(Ordinamento.OrdineStandard);

        List<Contatto> risultati = new ArrayList<>(r.getContatti().values());

        assertEquals("Michele", risultati.get(0).getNome());
        assertEquals("Mario", risultati.get(1).getNome());
        assertEquals("Giovanni", risultati.get(2).getNome());
        assertEquals("Michele", risultati.get(3).getNome());

        assertEquals("Adinolfi", risultati.get(0).getCognome());
        assertEquals("Kaito", risultati.get(3).getCognome());
    }
}
