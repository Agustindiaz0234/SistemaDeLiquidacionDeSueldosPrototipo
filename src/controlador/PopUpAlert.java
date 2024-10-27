/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import javafx.scene.control.Alert;


public class PopUpAlert {
    
    public void noAcceso(){
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Acceso denegado");
    alert.setHeaderText(null);
    alert.setContentText("Acceso denegado");
    alert.showAndWait();
    
    }
    public void noFunciona(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Sin funcionamiento");
    alert.setHeaderText(null);
    alert.setContentText("Esta funcionalidad aun no a sido implementada");
    alert.showAndWait();
    }
}
