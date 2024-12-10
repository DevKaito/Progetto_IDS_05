package controller;

import classes.Contatto;
import classes.Rubrica;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class RubricaController {
    private Rubrica rubrica;
    private ObservableList<Contatto> contatti;
    
    @FXML
    private TableView<Contatto> tableView;
    @FXML
    private TableColumn<Contatto,String> nomeColumn;
    @FXML
    private TableColumn<Contatto, String> cognomeColumn;
    @FXML
    private TableColumn<Contatto, String> numeriDiTelefonoColumn;
    @FXML
    private TableColumn<Contatto, String> indirizziEmailColumn;
    
    public void initialize(){
        nomeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNome()));
        cognomeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCognome()));
        numeriDiTelefonoColumn.setCellValueFactory(cellData -> {
            String[] numeri = cellData.getValue().getNumeriTelefono();
            return new SimpleStringProperty(String.join(", ", numeri));
        });
        indirizziEmailColumn.setCellValueFactory(cellData -> {
            String[] email = cellData.getValue().getIndirizziEmail();
            return new SimpleStringProperty(String.join(", ", email));
        });
        
        Contatto c = new Contatto("si", "no", new String[]{"33333333"}, new String[]{"m"});
        rubrica = new Rubrica();
        rubrica.aggiungiContattoRubrica(c.getNome(), c.getCognome(), c.getNumeriTelefono(), c.getIndirizziEmail());
        contatti = FXCollections.observableArrayList(rubrica.getContatti().values());
        
        tableView.setItems(contatti);
    }
}
