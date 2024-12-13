package controller;

import classes.Contatto;
import classes.Rubrica;
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
    
    public RubricaViewController(){
        initController();
    }
    
    public void initController(){
        
        //Searchbar inizialization
        searchBar = new TextField("Ricerca");
        searchBar.setText("");
        
        //Combobox initialization
        cmb = new ComboBox();
        cmb.getItems().addAll("Per aggiunta", "Alfabetico");
        cmb.getSelectionModel().selectFirst();
       
        //TableView initialization
        tableView = new TableView();
        
        rubrica = new Rubrica();
        rubrica.aggiungiContattoRubrica("si", "no", new String[]{"333333"}, new String[]{"m"});
        rubrica.aggiungiContattoRubrica("no", "si", new String[]{"666666"}, new String[]{"n"});
        rubrica.aggiungiContattoRubrica("sto", "cazzo", new String[]{"999999"}, new String[]{"o"});
        ObservableList<Contatto> data = FXCollections.observableArrayList(rubrica.getContatti().values());
        
        System.out.print(data);
        
        nomeColumn.setCellValueFactory(new PropertyValueFactory<Contatto, String>("nome"));
        cognomeColumn.setCellValueFactory(new PropertyValueFactory<Contatto, String>("cognome"));
        numeriDiTelefonoColumn.setCellValueFactory(new PropertyValueFactory<Contatto, String>("numeriTelefono"));
        indirizziEmailColumn.setCellValueFactory(new PropertyValueFactory<Contatto, String>("indirizziEmail"));
        
        
        tableView.setItems(data);
    }
}
