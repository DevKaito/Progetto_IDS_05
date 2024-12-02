package classes;

/**
 * @brief Classe che si occupa della creazione e gestione di una nuova istanza di un contatto.
 * 
 */
public class Contatto {
    private String nome;
    private String cognome;
    private String[] numeriTelefono;
    private String[] indirizziEmail;
    private Integer ID;
    private static int contatore = 0;
    
    /**
     * @brief Costruttore che si occupa della creazione di un contatto con i parametri ricevuti.
     * 
     * @param nome, nome del contatto 
     * @param cognome, cognome del contatto
     * @param numeriTelefono array di massimo 3 numeri di telefono
     * @param indirizziEmail array di massimo 3 indirizzi Email
     */
    public Contatto(String nome, String cognome, String[] numeriTelefono, String[] indirizziEmail){
        this.nome = nome;
        this.cognome = cognome;
        this.numeriTelefono = numeriTelefono;
        this.indirizziEmail = indirizziEmail;
        this.ID = ++contatore;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public String getCognome(){
        return this.cognome;
    }
    
    public String[] getNumeriTelefono(){
        return this.numeriTelefono;
    }
    
    public String[] getIndirizziEmail(){
       return this.indirizziEmail;
    }
    
    public Integer getID(){
        return this.ID;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public void setCognome(String cognome){
        this.cognome = cognome;
    }
    
    public void setNumeriTelefono(String[] numeriTelefono){
        this.numeriTelefono = numeriTelefono;
    }
    
    public void setIndirizziEmail(String[] indirizziEmail){
        this.indirizziEmail = indirizziEmail;
    }
    
    public void setID(Integer ID){
        this.ID = ID;
    }
}
