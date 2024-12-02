package interfaces;

import classes.Contatto;

/**
 * @brief Interfaccia da implementare per la realizzazione della funzionalità di ricerca di contatti nella rubrica
 */
public interface CercaContatto {
    /**
     * @brief Metodo da implementare per effettuare la ricerca usando il parametro fornito
     * @param searchValue, stringa da ricercare nei nomi dei contatti.
     * @return Contatto[]
     */
    public Contatto[] eseguiRicerca(String searchValue);
        
}
