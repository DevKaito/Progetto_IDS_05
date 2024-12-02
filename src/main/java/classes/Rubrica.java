package classes;

import interfaces.CercaContatto;
import interfaces.OrdinaContatto;
import enumerators.Ordinamento;
import java.util.Map;
import java.util.TreeMap;

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
    * Prende come parametro un contatto "Contatto c" da aggiungere alla rubrica.
    *
    * @pre none
    * @post Il contatto viene aggiunto alla rubrica.
    * 
    * @param c Il contatto "Contatto c" da aggiungere alla rubrica.
    */
    public void aggiungiContattoRubrica(Contatto c){
        contatti.put(c.getID(), c);
    }
    /**
    * @brief Aggiunge un contatto alla rubrica.
    * 
    * Prende come parametro un contatto "Contatto c" da un file esterno da aggiungere alla rubrica.
    * 
    * @pre Venga scelto almeno un contatto e il file abbia almeno un contatto.
    * @post I contatti scelti dal file vengono aggiunti alla rubrica.
    * 
    * @param c Il contatto "Contatto c" da aggiungere alla rubrica.
    */
    public void aggiungiContattoFile(Contatto c){
        TrasferimentoContatti.importaContatto(contatti, "contatti.ser");
    }
    
    /**
    * @brief Modifica un contatto dalla rubrica.
    *
    * Prende come parametro un contatto "Contatto c" e lo modifca.
    *
    * @pre Il contatto che si ha intenzione di modificare esista in rubrica.
    * @post Il contatto viene modificato con successo dalla rubrica.
    * 
    *
    * @param c Il contatto "Contatto c" da modificare dalla rubrica.
    */
    public void modificaContatto(Contatto c){
        
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
    * Prende da parametro "String searchValue" e ricerca in rubrica tutti i contatti contenenti la stringa.
    * 
    * @pre Presenza di contatti in rubrica.
    * @post La rubrica viene filtrata in base al testo scritto nella barra di ricerca.
    * 
    * @param searchValue Stringa inserita dall'utente nella barra di ricerca.
    * @return Contatto[].
    */
    @Override
    public Contatto[] eseguiRicerca(String searchValue){
        return new Contatto[1];
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
