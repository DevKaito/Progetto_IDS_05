package classes;

import interfaces.CercaContatto;
import interfaces.OrdinaContatto;
import enumerators.Ordinamento;
import java.util.Map;
import java.util.TreeMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @brief Classe che si occupa della rappresentazione della rubrica contenente funzioni utili per i contatti che contiene.
 */
public class Rubrica implements CercaContatto, OrdinaContatto {
    private Map<Integer, Contatto> contatti;
    
    /**
    * @brief Inizializza la rubrica.
    */
    public Rubrica(){
        contatti = new TreeMap<>();
    }
    
    /**
    * @brief Crea e aggiunge un contatto alla rubrica.
    *
    * Prende come parametri i dati del contatto (nome, cognome, numero/i di telefono e indirizzo/i e-mail) da aggiungere alla rubrica.
    *
    * @pre none
    * @post Il contatto viene aggiunto alla rubrica.
    * 
    * @param nome.
    * @param cognome.
    * @param numeriTelefono[].
    * @param indirizzoEmail[].
    */
    public void aggiungiContattoRubrica(String nome, String cognome, String[] numeriTelefono, String[] indirizziEmail){

        if(nome == null){
            throw new IllegalArgumentException("Non è presente il dato del nome");
        }

        Contatto c = new Contatto(nome, cognome, numeriTelefono, indirizziEmail);
        
        contatti.put(c.getID(), c);

    }
    /**
    * @brief Aggiunge dei contatti alla rubrica.
    * 
    * Prende come parametro un array di contatti "Contatto[] c" da un file esterno da aggiungere alla rubrica.
    * 
    * @pre Venga scelto almeno un contatto e il file abbia almeno un contatto.
    * @post I contatti scelti dal file vengono aggiunti alla rubrica.
    * 
    * @param c[].
    */
    public void aggiungiContattoFile(Contatto[] c){
        TrasferimentoContatti.importaContatto(contatti, "contatti.ser");
    }
    
    /**
    * @brief Modifica un contatto dalla rubrica.
    *
    * Prende come parametri i dati del contatto (nome, cognome, numero/i di telefono e indirizzo/i e-mail) e li modifica. Inoltre prende l'ID e lo passa al contatto modificato.
    *
    * @pre I parametri siano validi.
    * @post I dati del contatto vengono modificati con successo.
    * 
    *
    * @param nome.
    * @param cognome.
    * @param numeroTelefoni[].
    * @param indirizzoEmail[].
    * @param ID.
    */
    public void modificaContatto(String nome, String cognome, String[] numeriTelefono, String[] indirizziEmail, int ID){
        Contatto nuovoContatto = contatti.get(ID);

        nuovoContatto.setNome(nome);
        nuovoContatto.setCognome(cognome);
        nuovoContatto.setNumeriTelefono(numeriTelefono);
        if(numeriTelefono.length > 3){
            System.err.println("Errore! Un contatto può avere massimo 3 numeri di telefono!");
        }
        nuovoContatto.setIndirizziEmail(indirizziEmail);
        if(indirizziEmail.length > 3){
            System.err.println("Errore! Un contatto può avere massimo 3 indirizzi email!");
        }

        contatti.put(ID, nuovoContatto);
        System.out.println("Contatto modificato con successo");
    }
    
    /**
    * @brief Rimuove un contatto dalla rubrica.
    *
    * Prende come parametro un contatto "Contatto c" e lo rimuove dalla rubrica
    *
    * @pre Il contatto che si ha intenzione di rimuovere esista in rubrica.
    * @post Il contatto viene rimosso con successo dalla rubrica.
    * 
    *
    * @param c Il contatto "Contatto c" da rimuovere dalla rubrica.
    */
    public void rimuoviContatto(Contatto c){
        
    }
    
    public Map<Integer, Contatto> getContatti(){
        return this.contatti;
    }
    
    /**
    * @brief Ricerca una stringa in rubrica.
    * 
    * Prende da parametro "String searchValue" e ritorna una lista di "Contatto", filtrata in base alla stringa inserita.
    * 
    * @pre Presenza di contatti in rubrica.
    * @post La rubrica viene filtrata in base al testo scritto nella barra di ricerca.
    * 
    * @param searchValue Stringa inserita dall'utente nella barra di ricerca.
    * @return List<Contatto>.
    */
    @Override
    public List<Contatto> eseguiRicerca(String searchValue){

        List<Contatto> risultati = new LinkedList<Contatto>();

        for (Contatto c : contatti.values()){
            if(c.getNome().contains(searchValue) | c.getCognome().contains(searchValue) ){
                risultati.add(c);
            }
            else System.err.println("Nessun risultato per '" + searchValue +"'" );

        }

        return risultati;
    }
    
    /**
    * @brief Applica un ordinamento alla rubrica.
    * 
    * Ordina la rubrica in base a "Ordinamento o" preso da parametro.
    * 
    * @pre Presenza di contatti in rubrica.
    * @post I contatti in rubrica vengono visualizzati in ordine alfabetico.
    * 
    * @param o Tipo di ordinamento.
    */
    @Override
    public void applicaOrdinamento(Ordinamento o){
        
    }
}
