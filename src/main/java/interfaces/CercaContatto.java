package interfaces;

import classes.Contatto;
import java.util.List;

/**
 * @brief Interfaccia da implementare per la realizzazione della funzionalità di ricerca di contatti nella rubrica
 */
public interface CercaContatto {
    /**
     * @brief Metodo da implementare per effettuare la ricerca usando il parametro fornito.
     * @param searchValue, stringa da ricercare nei nomi dei contatti.
     * @return List<Contatto>.
     */
    public List<Contatto> eseguiRicerca(String searchValue);
        
}
