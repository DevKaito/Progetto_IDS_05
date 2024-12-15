package controller;

import classes.Rubrica;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author franc
 */
public class AddEditContactController{

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
    
    public Rubrica rubrica;
    public Integer ID;
    public Boolean isEditMode = false;
    
    @FXML
    public void initialize() {
        if (isEditMode) {
            
            // Popola i campi con i dati del contatto esistente
            nameFld.setText(rubrica.getContatti().get(ID).getNome());
            surnameFld.setText(rubrica.getContatti().get(ID).getCognome());

            String[] numeri = rubrica.getContatti().get(ID).getNumeriTelefono();
            if (numeri.length > 0) numberFld1.setText(numeri[0]);
            if (numeri.length > 1) numberFld2.setText(numeri[1]);
            if (numeri.length > 2) numberFld3.setText(numeri[2]);

            String[] emails = rubrica.getContatti().get(ID).getIndirizziEmail();
            if (emails.length > 0) emailFld1.setText(emails[0]);
            if (emails.length > 1) emailFld2.setText(emails[1]);
            if (emails.length > 2) emailFld3.setText(emails[2]);
        }
    }
    
    @FXML
    private void handleSubmit(){
        String nome = nameFld.getText();
        String cognome = surnameFld.getText();
        ArrayList<String> numeri = new ArrayList<>();
        ArrayList<String> email = new ArrayList<>();
        
        if(!numberFld1.getText().isEmpty())
            numeri.add(numberFld1.getText());
        if(!numberFld2.getText().isEmpty())
            numeri.add(numberFld2.getText());
        if(!numberFld3.getText().isEmpty())
            numeri.add(numberFld3.getText());
        
        if(!emailFld1.getText().isEmpty())
            email.add(emailFld1.getText());
        if(!emailFld2.getText().isEmpty())
            email.add(emailFld2.getText());
        if(!emailFld3.getText().isEmpty())
            email.add(emailFld3.getText());
        
        String[] numeriTelefono = numeri.toArray(new String[numeri.size()]);
        String[] indirizziEmail =  email.toArray(new String[email.size()]);
        System.out.print(Arrays.toString(numeriTelefono));
        System.out.print(Arrays.toString(indirizziEmail));
        
        if (isEditMode){
            rubrica.modificaContatto(nome, cognome, numeriTelefono, indirizziEmail, ID);
        }
        else{
            rubrica.aggiungiContattoRubrica(nome, cognome, numeriTelefono, indirizziEmail);
        }       
        
        Stage stage = (Stage) submitBtn.getScene().getWindow();
        stage.close();
    } 
    
    public void setEditMode(Boolean isEditMode){
        this.isEditMode = isEditMode;
    }
    
    public void setRubrica(Rubrica rubrica){
        this.rubrica = rubrica;
    }
    
    public void setID(Integer ID){
        this.ID = ID; 
    }
}
