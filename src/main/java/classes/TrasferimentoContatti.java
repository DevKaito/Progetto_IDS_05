package classes;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @brief Classe che si occupa del trasferimento dei contatti da file esterno a rubrica e viceversa. Fornisce 2 metodi statici a tale scopo.
 * 
 */
public class TrasferimentoContatti {
    
    /**
     * @brief Riceve una mappa di contatti e la trasferisce sul file specificato.
     * 
     * @pre I parametri forniti siano validi.
     * @post I contatti vengono esportati con successo sul file.
     * 
     * @param contatti, l'insieme di contatti da esportare.
     * @param filename, il file su cui esportare i contatti.
     */
    public static void esportaContatto(Map<Integer, Contatto> contatti, String filename){
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(filename)))){
            for (Contatto contatto : contatti.values()){
                pw.print(contatto.toString());
                pw.append("; \n");
            }
        }catch (IOException ex){
            System.err.println("File non trovato: " + ex.getMessage());
        }
    }
    
    /**
     * @brief Riceve una mappa di contatti e aggiunge i contatti presi dal file specificato alla mappa.
     * 
     * @pre I parametri forniti siano validi, il file abbia ogni parametro contrassegnato da identificativo.
     * @post I contatti vengono importati con successo sulla rubrica.
     * 
     * @param filename
     * @return List<Contatto>
     */
    public static List<Contatto> importaContatto(String filename){
        List<Contatto> contatti = new LinkedList<>();
        try (Scanner s = new Scanner(new BufferedReader(new FileReader(filename)))){
           
            if (s.nextLine()==null) throw new IOException("File vuoto");
            s.useDelimiter("[\n]");

            while(s.hasNext()){
                Contatto contatto = regexContatto(s.nextLine());
                contatti.add(contatto);
            }
        }catch (IOException ex){
            System.err.println("File non trovato: " + ex.getMessage());
        }

        return contatti;
    }

    private static Contatto regexContatto (String line){
        String nomeRegex = "Nome:\\s*(\\w+)";
        String cognomeRegex = "Cognome:\\s*(\\w+)";
        String numRegex = "Numeri di telefono:\\s*\\[(.*?)\\]";
        String emailRegex = "Indirizzi email:\\s*\\[(.*?)\\] ";

        Pattern patternNome = Pattern.compile(nomeRegex);
        Pattern patternCognome = Pattern.compile(cognomeRegex);
        Pattern patternNum = Pattern.compile(numRegex);
        Pattern patternEmail = Pattern.compile(emailRegex);

        Matcher matcherNome = patternNome.matcher(line);
        Matcher matcherCognome = patternCognome.matcher(line);
        Matcher matcherNum = patternNum.matcher(line);
        Matcher matcherEmail = patternEmail.matcher(line);

        String nome = matcherNome.find() ? matcherNome.group(1) : null;
        String cognome = matcherCognome.find() ? matcherCognome.group(1) : null;
        String[] numTelefono = matcherNum.find() ? matcherNum.group(1).split(",\\s*") : new String[0];
        String[] email = matcherEmail.find() ? matcherEmail.group(1).split(",\\s*") : new String[0];

        if (numTelefono.length > 3){
            numTelefono = Arrays.copyOf(numTelefono, 3);
            System.err.println("Superato il limite di numeri di telefono possedibili. Sono stati selezionati i primi 3");
        }
        if (email.length > 3){
            email = Arrays.copyOf(email, 3);
            System.err.println("Superato il limite di numeri di telefono possedibili. Sono stati selezionati i primi 3");
        }

        if (nome!=null && cognome != null){
            return new Contatto(nome, cognome, numTelefono, email);
        }
        else return null;
    }
}
