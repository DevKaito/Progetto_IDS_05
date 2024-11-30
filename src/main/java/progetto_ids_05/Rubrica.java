package progetto_ids_05;

import progetto_ids_05.Interfaces.*;
import java.util.Map;
import java.util.TreeMap;

public class Rubrica implements CercaContatto, OrdinaContatto {
    private Map<Integer, Contatto> contatti;
    
    public Rubrica(){
        contatti = new TreeMap<>();
    }
    
    /**
    * @brief Crea e aggiunge un contatto alla rubrica.
    *
    * Prende come parametro un contatto "Contatto c" da aggiungere alla rubrica e lo aggiunge.
    *
    * @pre none
    * @post Il contatto viene aggiunto alla rubrica.
    * 
    * @param c Il contatto "Contatto c" da aggiungere alla rubrica.
    */
    public void aggiungiContattoRubrica(Contatto c){
        //aaaa
    }
        
     //-
    
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
    
    @Override
    public Contatto[] eseguiRicerca(String searchValue){
        return new Contatto[1];
    }
    
   @Override
    public void applicaOrdinamento(Ordinamento o){
        
    }
}
