package interfaces;

import classes.Rubrica;
import enumerators.Ordinamento;

/**
* @brief Interfaccia per l'implementazione del metodo che si occupa dell'ordinamento dei contatti.
*/
public interface OrdinaContatto {
    /**
    * @brief Applica l'ordinamento in base a "Ordinamento o" passato da parametro.
    * 
    * @param o Definisce il tipo di ordinamento.
    * @param rubrica Definisce la rubrica passata.
    * @return Rubrica.
    */
    public Rubrica applicaOrdinamento(Rubrica rubrica, Ordinamento o);
}
