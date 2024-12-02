package classes;



import classes.Rubrica;
import classes.Contatto;
import java.util.Map;

/**
 * @brief Classe che si occupa del trasferimento dei contatti da file esterno a rubrica e viceversa. Fornisce 2 metodi statici a tale scopo.
 * 
 */
public class TrasferimentoContatti {
    
    /**
     * @brief Riceve una mappa di contatti e la trasferisce sul file specificato.
     * 
     * @pre I parametri forniti siano validi
     * @post I contatti vengono esportati con successo sul file
     * 
     * @param contatti, l'insieme di contatti da esportare.
     * @param filename, il file su cui esportare i contatti.
     */
    public static void esportaContatto(Map<Integer, Contatto> contatti, String filename){
        
    }
    
    /**
     * @brief Riceve una mappa di contatti e aggiunge i contatti presi dal file specificato alla mappa
     * 
     * @pre I parametri forniti siano validil
     * @post I contatti vengono aggiunti alla rubrica.
     * 
     * @param contatti
     * @param filename
     * @return Rubrica
     */
    public static Rubrica importaContatto(Map<Integer, Contatto> contatti, String filename){
        return new Rubrica();
    }
}
