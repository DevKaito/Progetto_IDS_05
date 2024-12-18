
package interfaces;

/**
 * @brief Interfaccia per visualizzare a schermo dei messaggi di warning.
 */
public interface ErrorHandler {
    /**
     * @brief Mostra il messaggio di warning a schermo.
     * 
     * @param message Definisce il messaggio da mostrare a schermo
     */
    
    void showError(String message);
}
