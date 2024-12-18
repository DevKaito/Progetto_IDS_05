package controller;

import classes.Contatto;
import classes.Rubrica;
import enumerators.Ordinamento;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RubricaViewController{
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
        searchBar.setPromptText("e.g. Mario");
        
        //Combobox initialization
        cmb.getItems().addAll("Per aggiunta", "Alfabetico");
        cmb.getSelectionModel().selectFirst();
        cmb.setOnAction(event -> handleOrdinamento());
        
        //Utility buttons inizialization
        gestisciButtons();
        addBtn.setOnAction(event -> addContactWindow());
        
        //Import & Export
        importBtn.setOnAction(event -> ImportContactWindow());
        exportBtn.setOnAction(event -> ExportContactWindow());
       
        //TableView initialization con contatti di prova
        rubrica = new Rubrica();
        
        BindingRicerca();
        
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
            String indirizziUniti = String.join("\n", indirizzi);
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
                    handleOrdinamento();
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
    private void ImportContactWindow(){
        try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/Import_Export.fxml"));
                Parent root = loader.load();
                Import_ExportFileController importaController = loader.getController();
                importaController.setMainController(this);
                Stage stage = new Stage();
                stage.setTitle("Importazione");
                stage.setScene(new Scene(root));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
                
        } catch (IOException e){}
            
    }   
    public void importDataFromFile(File file) {
        rubrica.aggiungiContattoFile(file.getAbsolutePath());
        
        ObservableList<Contatto> contattiList = FXCollections.observableArrayList(rubrica.getContatti().values());
        tableView.setItems(contattiList);

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Importazione Contatti");
        alert.setHeaderText("Contatti importati");
        alert.showAndWait();
    }
    
    private void ExportContactWindow(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/Import_Export.fxml"));
            Parent root = loader.load();
            Import_ExportFileController esportaController = loader.getController();
            esportaController.setMode(true);
            esportaController.setMainController(this);
            Stage stage = new Stage();
            stage.setTitle("Esportazione");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
                
        } catch (IOException e){}
        
    }
    
    public void ExportDataToFile(File file) {
        rubrica.esportaRubricaFile(file.getAbsolutePath());
        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Esportazione Contatti");
        alert.setHeaderText("Contatti esportati");
        alert.showAndWait();
    }
    
    public void BindingRicerca(){
        searchBar.textProperty().addListener((observable, oldValue, newValue) ->{
                String[] parole = newValue.trim().split("\\s+");
                if (parole.length==0 || parole[0].isEmpty()){
                    tableView.setItems(FXCollections.observableArrayList(rubrica.getContatti().values()));
                    return;
                }
                
                List<Contatto> risultati = rubrica.getContatti().values().stream().filter(contatto -> {
                    for (String parola : parole){
                        if (!contatto.getNome().toLowerCase().contains(parola.toLowerCase()) && !contatto.getCognome().toLowerCase().contains(parola.toLowerCase()))
                            return false;
                    }
                    return true;
                })
                .collect(Collectors.toList());
                
                tableView.setItems(FXCollections.observableArrayList(risultati));
        });
        
        
    }
    
}
