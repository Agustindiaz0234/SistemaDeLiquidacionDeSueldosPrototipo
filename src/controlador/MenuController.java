/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author diaza
 */
public class MenuController implements Initializable {

    @FXML
    private Button btnEmpresas;
    @FXML
    private Button btnAdministrar;
    @FXML
    private Button btnSalir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void navegarEmpresa(ActionEvent event) {
        MainLayoutController mainController = Main.getMainController();
        mainController.showEmpresa();
    }

    @FXML
    private void navegarAdministrar(ActionEvent event) {
        PopUpAlert popUp = new PopUpAlert();
        popUp.noAcceso();
    }

    @FXML
     public void closeVentana(){
     Stage stage = (Stage) btnSalir.getScene().getWindow();
     stage.close();
     }
    
}
