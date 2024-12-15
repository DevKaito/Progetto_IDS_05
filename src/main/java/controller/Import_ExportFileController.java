package controller;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import javafx.scene.control.Button;

public class Import_ExportFileController{
    private RubricaViewController mainController;
    private boolean isExport = false;
    @FXML
    private Button bimportID;

    public void initialize(){
        bimportID.setOnAction(event -> handleFileSelection());
    }
    
    public void setMainController(RubricaViewController mainController) {
        this.mainController = mainController;
    }
    
    public void handleFileSelection(){
        Stage stage = (Stage) bimportID.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null && !isExport) {
            mainController.importDataFromFile(selectedFile);
            stage.close(); 
        }
        else if (selectedFile != null && isExport){
            mainController.ExportDataToFile(selectedFile);
            stage.close();
        }
        
    }
    
    public void setMode(Boolean bo){
        this.isExport = bo;
    }
    
}
