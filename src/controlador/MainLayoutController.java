
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;



/**
 * FXML Controller class
 *
 * @author diaza
 */
public class MainLayoutController implements Initializable {

    @FXML
    private VBox formContainer; // Contenedor para los formularios

    
    public void showAgregarEmpresa(){
        loadForm("EmpresaDetail.fxml");
    }
   
    public void showEmpleadoList() {
        
        loadForm("ListarEmpleado.fxml");
    }
    public void showEmpresa() {
    loadForm("EmpresaList.fxml");  
    }

    public void showAgregarEmpleado() {
        loadForm("EmpleadoDetail.fxml");
    }
    
     public void showLiquidacion() {
        loadForm("Liquidacion.fxml");
    }
     
     public void showMenu(){
     loadForm("Menu.fxml");
     }
     
     public void accesoDenegado(){
     PopUpAlert popUp = new PopUpAlert();
     popUp.noAcceso();
     }
     
    public void noFun(){
     PopUpAlert popUp = new PopUpAlert();
     popUp.noFunciona();
    }

 
    private void loadForm(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/"+fxmlFile));
            VBox form = loader.load(); 
            formContainer.getChildren().clear(); 
            formContainer.getChildren().add(form); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showMenu();
      }

 
}
