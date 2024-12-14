package controller;

import classes.Contatto;
import classes.Rubrica;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class RubricaViewController {
    private Rubrica rubrica;    
    @FXML
    private TextField searchBar;
    @FXML
    private ComboBox cmb;
    @FXML
    private TableView<Contatto> tableView;
    @FXML
    private TableColumn<Contatto, String> nomeColumn;
    @FXML
    private TableColumn<Contatto, String> cognomeColumn;
    @FXML
    private TableColumn<Contatto, String> numeriDiTelefonoColumn;
    @FXML
    private TableColumn<Contatto, String> indirizziEmailColumn;
    
    @FXML
    public void initialize(){
        
        //Searchbar inizialization
        searchBar.setPromptText("1233");
        
        //Combobox initialization
        cmb.getItems().addAll("Per aggiunta", "Alfabetico");
        cmb.getSelectionModel().selectFirst();
       
        //TableView initialization
        rubrica = new Rubrica();
        rubrica.aggiungiContattoRubrica("si", "no", new String[]{"3341847499"}, new String[]{"mario@gmail.com"});
        rubrica.aggiungiContattoRubrica("no", "si", new String[]{"3341847498", "3341857499"}, new String[]{"nintendo@gmail.com"});
        rubrica.aggiungiContattoRubrica("sto", "cazzo", new String[]{"3761847499"}, new String[]{"ophelia@dnd.com"});
        ObservableList<Contatto> data = FXCollections.observableArrayList(rubrica.getContatti().values());
        
        nomeColumn.setCellValueFactory(new PropertyValueFactory<Contatto, String>("nome"));
        cognomeColumn.setCellValueFactory(new PropertyValueFactory<Contatto, String>("cognome"));
        numeriDiTelefonoColumn.setCellValueFactory(cellData -> {
            String[] numeri = cellData.getValue().getNumeriTelefono();
            String numeriUniti = String.join("\n", numeri);
            return new SimpleStringProperty(numeriUniti);
        });
        indirizziEmailColumn.setCellValueFactory(cellData -> {
            String[] indirizzi = cellData.getValue().getIndirizziEmail();
            String indirizziUniti = String.join("", indirizzi);
            return new SimpleStringProperty(indirizziUniti);
        });
        
        tableView.setItems(data);
    }
    
    
    
}
