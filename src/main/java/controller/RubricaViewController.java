package controller;

import classes.Contatto;
import classes.Rubrica;
import enumerators.Ordinamento;
import java.io.IOException;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    private TableColumn<Contatto, Void> gestioneColumn;
    @FXML
    private Button addBtn;
    @FXML
    private Button importBtn;
    @FXML
    private Button exportBtn;
    
    
    @FXML
    public void initialize(){
        
        //Searchbar inizialization
        searchBar.setPromptText("1233");
        
        //Combobox initialization
        cmb.getItems().addAll("Per aggiunta", "Alfabetico");
        cmb.getSelectionModel().selectFirst();
        cmb.setOnAction(event -> handleOrdinamento());
        
        //Utility buttons inizialization
        gestisciButtons();
        addBtn.setOnAction(event -> addContactWindow());
       
        //TableView initialization con contatti di prova
        rubrica = new Rubrica();
        rubrica.aggiungiContattoRubrica("si", "no", new String[]{"3341847499"}, new String[]{"mario@gmail.com"});
        rubrica.aggiungiContattoRubrica("no", "si", new String[]{"3341847498", "3341857499"}, new String[]{"nintendo@gmail.com"});
        rubrica.aggiungiContattoRubrica("Amedeo", "Pratola", new String[]{"3761847499"}, new String[]{"ophelia@dnd.com"});
        rubrica.aggiungiContattoRubrica("Amedeo", "Bianco", new String[]{"3761347499", "9898908983"}, new String[]{"violayio@dnd.com"});
        
        updateTableView();
    }
    private void updateTableView(){
            ObservableList<Contatto> data = FXCollections.observableArrayList(rubrica.getContatti().values());
        
            //Columns initialization
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
            tableView.refresh();
        }
    
    private void handleOrdinamento(){
    
            String opzioneSelezionata = (String) cmb.getValue();
            
                if ("Alfabetico".equals(opzioneSelezionata)){
                    rubrica.applicaOrdinamento(Ordinamento.OrdineAlfabetico);
                } else if ("Per aggiunta".equals(opzioneSelezionata)){
                    rubrica.applicaOrdinamento(Ordinamento.OrdineStandard);
                }
            ObservableList<Contatto> data = FXCollections.observableArrayList(rubrica.getContatti().values());
            tableView.setItems(data);
        }
    
    private void gestisciButtons() {
            gestioneColumn.setCellFactory(param -> new TableCell<Contatto, Void>() {
            private final HBox container = new HBox();
            private final Button modificaButton = new Button("Modifica");
            private final Button rimuoviButton = new Button("Rimuovi");
            
            {     
                modificaButton.setOnAction(event -> {
                    Integer ID = getTableView().getItems().get(getIndex()).getID();
                    if (ID != null) {
                        modifyContactWindow(ID);
                        ObservableList<Contatto> data = FXCollections.observableArrayList(rubrica.getContatti().values());
                        tableView.setItems(data);
                    } else {
                        System.err.println("Contatto non valido per modifica.");
                    }
                });

                {rimuoviButton.setOnAction(event -> {
                    Contatto contatto = getTableView().getItems().get(getIndex());
                    if (contatto != null) {
                        rubrica.rimuoviContatto(contatto.getID());
                        ObservableList<Contatto> data = FXCollections.observableArrayList(rubrica.getContatti().values());
                        tableView.setItems(data);
                    } 
                });
                }
                modificaButton.setStyle("-fx-background-color: lightblue; -fx-text-fill: black;");
                rimuoviButton.setStyle("-fx-background-color: lightcoral; -fx-text-fill: black;");
                container.setSpacing(10); // Spazio tra i pulsanti
                container.getChildren().addAll(modificaButton, rimuoviButton);
            }
    
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null); 
                } else {
                    setGraphic(container); 
                }
            }
            });
        }
        
    private void addContactWindow() {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/AddEditContact.fxml"));
                Parent root = loader.load();
                AddEditContactController controller = loader.getController();
                controller.setRubrica(rubrica);

                Stage stage = new Stage();
                stage.setTitle("Aggiungi Contatto");
                stage.setScene(new Scene(root));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();

                updateTableView();
            } catch (IOException e) {
                e.printStackTrace();
        }
}
        
    private void modifyContactWindow(Integer ID){
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/AddEditContact.fxml"));
                
                Parent root = loader.load();
                AddEditContactController controller = loader.getController();
                controller.setRubrica(rubrica);
                controller.setID(ID);
                controller.setEditMode(true);
                controller.initialize();
                
                Stage stage = new Stage();
                stage.setTitle("Modifica contatto");
                stage.setScene(new Scene(root));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
                
                updateTableView();
            } 
            catch (IOException e) {
                e.printStackTrace();
            }
            
}
        
}        

