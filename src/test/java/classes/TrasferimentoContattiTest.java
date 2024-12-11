package classes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

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
    Rubrica r;

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
        r = new Rubrica();
        
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of esportaContatto method, of class TrasferimentoContatti.
     * @throws FileNotFoundException 
     */
    @Test
    public void testEsportaContatto1() throws FileNotFoundException {
        String filename = "file.csv";
        String expectedvalue = "";
        Map<Integer, Contatto> mappa = new LinkedHashMap<>();
        Contatto c = new Contatto("Michele", "Adinolfi", new String[]{"343434", "43434343", "44444", "44444"}, new String[]{"dadada@gmail.com", "dadadadadadad@gmail.com", "adada@gmail.com", "adadada@gmail.com"});
        mappa.put(c.getID(), c);
        TrasferimentoContatti.esportaContatto(mappa, filename);
        try(Scanner s = new Scanner(new BufferedReader(new FileReader(filename)))){
            while (s.hasNext()){
                expectedvalue = s.nextLine();            
            }
        }
        assertEquals(expectedvalue.replace(";", "").trim(), mappa.get(1).toString());
        
    }

    /**
    * Test of importaContatto method, of class TrasferimentoContatti.
    */
    @Test
    public void testImportaContatto1() {
        String filename = "file.csv";
        Map<Integer, Contatto> mappa = new LinkedHashMap<>();
        Contatto c = new Contatto("Michele", "Adinolfi", new String[]{"343434", "43434343", "44444"}, new String[]{"dadada@gmail.com", "dadadadadadad@gmail.com", "adada@gmail.com"});
        mappa.put(c.getID(), c);
        TrasferimentoContatti.esportaContatto(mappa, filename);
        List<Contatto> lista = TrasferimentoContatti.importaContatto(filename);
        assertEquals(lista.get(0).toString(), c.toString());
    }
    
    /**
    * Test of regexContatto method, of class TrasferimentoContatti.
    */
    @Test
    public void testRegexContatto(){
         String expectedValori[] = {"Nome: Michele", "Cognome: Adinolfi", "Numeri di telefono: " + Arrays.toString(new String[]{"222333344455", "7777788", "99999"}), "Indirizzi email: " + Arrays.toString(new String[]{"aaab@gmail.com", "cccd@gmail.com", "pdf@gmail.com"})+";"};
         Contatto c = TrasferimentoContatti.regexContatto(Arrays.toString(expectedValori));
         assertEquals(Arrays.toString(expectedValori).replaceAll("^\\[|\\]$", ""), c.toString()+";");
    }

}
