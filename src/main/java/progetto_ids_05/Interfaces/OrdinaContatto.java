package progetto_ids_05.Interfaces;

import progetto_ids_05.Ordinamento;

/**
* @brief Interfaccia per l'implementazione del metodo che si occupa dell'ordinamento dei contatti.
*/
public interface OrdinaContatto {
    /**
    * @brief Applica l'ordinamento in base a "Ordinamento o" passato da parametro.
    * 
    * @param o Definisce il tipo di ordinamento.
    */
    public void applicaOrdinamento(Ordinamento o);
}
