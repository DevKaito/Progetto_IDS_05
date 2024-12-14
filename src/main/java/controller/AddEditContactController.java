/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import classes.Contatto;
import classes.Rubrica;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author franc
 */
public class AddEditContactController implements Initializable {

    @FXML
    private TextField nameFld;
    @FXML
    private TextField surnameFld;
    @FXML
    private TextField numberFld1;
    @FXML
    private TextField numberFld2;
    @FXML
    private TextField numberFld3;
    @FXML
    private TextField emailFld1;
    @FXML
    private TextField emailFld2;
    @FXML
    private TextField emailFld3;
    @FXML
    private Button submitBtn;
    
    private Rubrica rubrica;
    private Contatto contatto;
    private Boolean isEditMode = false;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (isEditMode && contatto != null) {
            // Popola i campi con i dati del contatto esistente
            nameFld.setText(contatto.getNome());
            surnameFld.setText(contatto.getCognome());

            String[] numeri = contatto.getNumeriTelefono();
            if (numeri.length > 0) numberFld1.setText(numeri[0]);
            if (numeri.length > 1) numberFld2.setText(numeri[1]);
            if (numeri.length > 2) numberFld3.setText(numeri[2]);

            String[] emails = contatto.getIndirizziEmail();
            if (emails.length > 0) emailFld1.setText(emails[0]);
            if (emails.length > 1) emailFld2.setText(emails[1]);
            if (emails.length > 2) emailFld3.setText(emails[2]);
        }
    }
    
    public void setEditMode(Boolean isEditMode){
        this.isEditMode = isEditMode;
    }
    
    public void setRubrica(Rubrica rubrica){
        this.rubrica = rubrica;
    }
    
    public void setContatto(Contatto contatto){
        this.contatto = contatto;
        
    }
    
    @FXML
    private void handleSubmit(){
        String nome = nameFld.getText();
        String cognome = surnameFld.getText();
        String[] numeriTelefono = {
            numberFld1.getText(),
            numberFld2.getText(),
            numberFld3.getText(),
        };
        String[] indirizziEmail = {
            emailFld1.getText(),
            emailFld2.getText(),
            emailFld3.getText(),
        };
        
        if (isEditMode && contatto != null){
            rubrica.modificaContatto(nome, cognome, numeriTelefono, indirizziEmail, contatto.getID());
        }else rubrica.aggiungiContattoRubrica(nome, cognome, numeriTelefono, indirizziEmail);
        
        Stage stage = (Stage) submitBtn.getScene().getWindow();
        stage.close();
    } 

}
