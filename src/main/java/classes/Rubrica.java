package classes;

import interfaces.CercaContatto;
import interfaces.OrdinaContatto;
import enumerators.Ordinamento;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @brief Classe che si occupa della rappresentazione della rubrica contenente funzioni utili per i contatti che contiene.
 */
public class Rubrica implements CercaContatto, OrdinaContatto{
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
    * Prende come parametro il nome del file esterno da cui estrarre i contatti da aggiungere alla rubrica.
    * 
    * @pre Venga scelto un file da cui estrarre i contatti 
    * @post I contatti scelti dal file vengono aggiunti alla rubrica.
    * 
    * @param filename.
    */
    public void aggiungiContattoFile(String filename){
        List<Contatto> c = TrasferimentoContatti.importaContatto(filename);
        for (Contatto contatto : c){
            contatti.put(contatto.getID(), contatto);
        }
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
        
        int currID = c.getID();
        contatti.remove(currID);
        
        for(int i = currID+1; i<contatti.size(); i++){
            Contatto ctemp = contatti.get(i);
            ctemp.setID(i--);
            if((i+1) == contatti.size()){
                ctemp.setNumeroSequenziale(i);
            }
        }
        
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
    * @param rubrica Rubrica da ordinare.
    * @return Rubrica.
    */
    @Override
    public void applicaOrdinamento(Ordinamento o){
        List<Map.Entry<Integer, Contatto>> lista = new ArrayList<>(this.getSet());
        Map<Integer, Contatto> orderedList = new LinkedHashMap<>();
        if (o==Ordinamento.OrdineAlfabetico){
            lista.sort(Comparator.comparing((Map.Entry<Integer,Contatto> entry) -> entry.getValue().getNome())
            .thenComparing((Map.Entry<Integer,Contatto> entry) -> entry.getValue().getCognome()));
            
            for (Map.Entry<Integer, Contatto> entry : lista){
                orderedList.put(entry.getKey(), entry.getValue());
            }
        } 
        else if(o==Ordinamento.OrdineStandard) {
            lista.sort(Comparator.comparing((Map.Entry<Integer,Contatto> entry) -> entry.getValue().getID()));
            for (Map.Entry<Integer, Contatto> entry : lista){
                orderedList.put(entry.getKey(), entry.getValue());
            }
        }
        
        this.contatti = orderedList;
    }

    private Set<Map.Entry<Integer, Contatto>> getSet(){
        return contatti.entrySet();
    }
}

