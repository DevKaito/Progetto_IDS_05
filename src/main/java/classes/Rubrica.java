package classes;

import interfaces.CercaContatto;
import interfaces.OrdinaContatto;
import enumerators.Ordinamento;
import interfaces.ErrorHandler;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import javafx.scene.control.Alert;

/**
 * 
 * @brief Classe che si occupa della rappresentazione della rubrica contenente funzioni utili per i contatti che contiene.
 */
public class Rubrica implements CercaContatto, OrdinaContatto, ErrorHandler{
    private Map<Integer, Contatto> contatti;
    private boolean showAlerts = true;
    /**
    * @brief Inizializza la rubrica.
    */
    public Rubrica(){
        contatti = new LinkedHashMap<>();
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
    * @param indirizziEmail[].
    */
    public void aggiungiContattoRubrica(String nome, String cognome, String[] numeriTelefono, String[] indirizziEmail){
        
        if(!nome.isEmpty()){
            if(numeriTelefono.length > 3){
                showError("Superato il limite di numeri di telefono possedibili. Sono stati selezionati i primi 3");
                numeriTelefono = Arrays.copyOf(numeriTelefono, 3);
            }
            
            if(indirizziEmail.length > 3){
                showError("Superato il limite di indirizzi email possedibili. Sono state selezionate le prime 3");
                indirizziEmail = Arrays.copyOf(indirizziEmail, 3);
            }
            
            for(String s : indirizziEmail){
                if(!s.contains("@") || !s.contains(".")){
                    showError("L'Email inserita non risulta valida.");
                    throw new RuntimeException();
                }
            }
            
            for(String s : numeriTelefono){
                if(s.length() != 10){
                    showError("Il numero di telefono inserito non risulta valido");
                    throw new RuntimeException();
                }
            }
            
            Contatto c = new Contatto(nome, cognome, numeriTelefono, indirizziEmail);
            contatti.put(c.getID(), c);
        }
        else{
            throw new RuntimeException("No");
        }
        
    }
    /**
    * @brief Aggiunge dei contatti presi da un file esterno alla rubrica.
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
    * @param numeriTelefono[].
    * @param indirizziEmail[].
    * @param ID.
    */
    public void modificaContatto(String nome, String cognome, String[] numeriTelefono, String[] indirizziEmail, int ID){
        Contatto nuovoContatto = contatti.get(ID);

        if(!nome.equals("")){
            nuovoContatto.setNome(nome);
            nuovoContatto.setCognome(cognome);
            if(numeriTelefono.length > 3){
                showError("Errore! Un contatto può avere massimo 3 numeri di telefono!");
                nuovoContatto.setNumeriTelefono(Arrays.copyOf(numeriTelefono, 3));
            } else {
                nuovoContatto.setNumeriTelefono(numeriTelefono);
            }

            if(indirizziEmail.length > 3){
                showError("Errore! Un contatto può avere massimo 3 indirizzi email!");
                nuovoContatto.setIndirizziEmail(Arrays.copyOf(indirizziEmail, 3));
            } else {
                nuovoContatto.setIndirizziEmail(indirizziEmail);
            }
            
            for(String s : indirizziEmail){
                if(!s.contains("@") || !s.contains(".")){
                    showError("L'Email inserita non risulta valida.");
                    throw new RuntimeException();
                }
                    
                   
            }
            
            for(String s : numeriTelefono){
                if(s.length() != 10 || !s.matches("\\d+")){
                    showError("Il numero di telefono inserito non risulta valido");
                    throw new RuntimeException();
                }
            }
            
            contatti.put(ID, nuovoContatto);
            System.out.println("Contatto modificato con successo");
            }
        else{
            throw new RuntimeException("No");
        }
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
    * @param ID L'ID del contatto da rimuovere dalla rubrica.
    */
    public void rimuoviContatto(Integer ID){
        if(contatti.containsKey(ID)){
            int currID = ID;
            int size = contatti.size();
            contatti.remove(currID);   
            for(int i = currID+1; i<=size; i++){
                Contatto ctemp = contatti.get(i);
                Integer newID = i-1;
                ctemp.setID(newID);
                contatti.remove(i);
                contatti.put(newID, ctemp);
                if(i == contatti.size()){
                    Contatto.setNumeroSequenziale(newID);
                }
            }
        }
        else{
            throw new RuntimeException("Non esiste tale contatto");
        }  
        
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
        String searchValueLower = searchValue.toLowerCase();

        for (Contatto c : contatti.values()){
            if(c.getNome().toLowerCase().contains(searchValueLower) | c.getCognome().toLowerCase().contains(searchValueLower)){
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
    * @post I contatti in rubrica vengono visualizzati in ordine alfabetico oppure in ordine di inserimento.
    * 
    * @param o Tipo di ordinamento.
    */
    @Override
    public void applicaOrdinamento(Ordinamento o){
        List<Map.Entry<Integer, Contatto>> lista = new ArrayList<>(this.getSet());
        Map<Integer, Contatto> orderedList = new LinkedHashMap<>();
        if (o==Ordinamento.OrdineAlfabetico){
            lista.sort(Comparator.comparing((Map.Entry<Integer,Contatto> entry) -> entry.getValue().getCognome().toLowerCase())
            .thenComparing((Map.Entry<Integer,Contatto> entry) -> entry.getValue().getNome().toLowerCase()));
            
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

    /**
     * @brief Esporta la rubrica su un file esterno.
     * 
     * @pre Il file scelto sia valido
     * @post La rubrica viene esportata sul file scelto.
     * 
     * @param filename
     */
    public void esportaRubricaFile(String filename){
        TrasferimentoContatti.esportaContatto(contatti, filename);
    }
    
    public Map<Integer, Contatto> getContatti(){
        return this.contatti;
    }

    private Set<Map.Entry<Integer, Contatto>> getSet(){
        return contatti.entrySet();
    }
    
    public void setShowAlerts(boolean showAlerts) {
        this.showAlerts = showAlerts;
    }
    
    /**
    * @brief Mostra un warning a schermo in caso di dato errato inserito nella Rubrica.
    * 
    * @pre L'utente inserisce un dato errato.
    * @post Compare un messaggio di warning a schermo.
    * 
    * @param message Definisce il messaggio da mostrare a schermo.
    */
    @Override
    public void showError(String message){
        if (showAlerts){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errore di Inserimento");
            alert.setHeaderText("Dati non validi");
            alert.setContentText(message);
            alert.showAndWait();
        }
    }
  
}

