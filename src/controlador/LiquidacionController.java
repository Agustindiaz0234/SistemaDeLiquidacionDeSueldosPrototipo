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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author diaza
 */
public class LiquidacionController implements Initializable {

    @FXML
    private TextField txtHorasTrabajadas;
    @FXML
    private TextField txtCobroHora;
    @FXML
    private Button btnCalcular;
    @FXML
    private Label lblSueldoBruto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void calcularSueldo(ActionEvent event) {
        int horasTrabajadas = Integer.parseInt(txtHorasTrabajadas.getText());
        int cobroHora = Integer.parseInt(txtCobroHora.getText());
        
        lblSueldoBruto.setText("El sueldo bruto del empleado es: " + String.valueOf(cobroHora * horasTrabajadas));
    }
    
}
