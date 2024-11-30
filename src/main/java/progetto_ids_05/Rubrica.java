package progetto_ids_05;

import progetto_ids_05.Interfaces.*;
import java.util.Map;
import java.util.TreeMap;

public class Rubrica implements CercaContatto, OrdinaContatto {
    private Map<Integer, Contatto> contatti;
    
    /**
    * @brief Inizializza la struttura dati "contatti".
    * 
    * Inizializza una struttura dati di tipo TreeMap<>().
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
        
    }

    /**
    * @brief Aggiunge un contatto in rubrica.
    * 
    * Prende come parametro un contatto "Contatto c" da aggiungere alla rubrica da un file esterno.
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
    
    /**
    * @brief Ricerca un contatto.
    * 
    * Prende come parametro una String searchValue e ritorna un array "Contatto[]", filtrato in base alla stringa presa da parametro.
    * 
    * @pre Presenza di contatti in rubrica.
    * @post La rubrica viene filtrata in base al testo scritto nella barra di ricerca.
    * 
    * @param searchValue La stringa di testo inserita nella barra di ricerca.
    * @return Contatto[].
    */
    @Override
    public Contatto[] eseguiRicerca(String searchValue){
        return new Contatto[1];
    }
    
    /**
    * @brief Applica un ordinamento.
    * 
    * Prende in input "Ordinamento o" e ordina la rubrica in base all'ordinamento che viene passato da parametro.
    * 
    * @pre Presenza di contatti in rubrica.
    * @post I contatti in rubrica vengono visualizzati nell'ordine corrispondente al tipo di ordinamento passato da parametro.
    * 
    * @param o Tipo di ordinamento selezionato. 
    */
    @Override
    public void applicaOrdinamento(Ordinamento o){
        
    }
}
