
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import modelo.Empresa;

public class EmpresaDetailController implements Initializable {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtDireccion;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtId;
    @FXML
    private Button btnGuardar;
    
    private static Empresa empresa;
    
    private static boolean isEdit;
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(isEdit){
            
            this.txtNombre.setText(empresa.getNombre());
             this.txtDireccion.setText(empresa.getDireccion());
             this.txtTelefono.setText(String.valueOf(empresa.getTelefono()));
             this.txtId.setText(String.valueOf(empresa.getId()));

        }
    }    

    @FXML
    private void handleGuardar(ActionEvent event) {
       String nombre = txtNombre.getText();
    String direccion = txtDireccion.getText();
    String telefonoStr = txtTelefono.getText();
    String idStr = txtId.getText();

    // Verificar que los campos no estén vacíos o nulos
    if (nombre == null || nombre.trim().isEmpty()) {
        showAlert("El nombre no puede estar vacío.");
        return; // Salir del método si la validación falla
    }
    
    if (direccion == null || direccion.trim().isEmpty()) {
        showAlert("La dirección no puede estar vacía.");
        return; // Salir del método si la validación falla
    }

    if (telefonoStr == null || telefonoStr.trim().isEmpty()) {
        showAlert("El teléfono no puede estar vacío.");
        return; // Salir del método si la validación falla
    }

    if (idStr == null || idStr.trim().isEmpty()) {
        showAlert("El ID no puede estar vacío.");
        return; // Salir del método si la validación falla
    }

    try {
        int telefono = Integer.parseInt(telefonoStr);
        int id = Integer.parseInt(idStr);

        // Crear la nueva empresa
        Empresa empresaNueva = new Empresa(id, nombre, direccion, telefono);

        // Lógica de edición o inserción
        if (isEdit) {
            updateEmpresa(empresaNueva, empresa);
        } else {
            insertarEmpresa(empresaNueva);
        }
    } catch (NumberFormatException e) {
        showAlert("El teléfono y el ID deben ser números válidos.");
        return;
    } catch (Exception e) {
        showAlert("Ocurrió un error inesperado: " + e.getMessage());
        return;
    }
   
        MainLayoutController mainController = Main.getMainController();
        mainController.showEmpresa();
    }
    
    
    public void insertarEmpresa(Empresa empresa){
        
        EmpresaListController.empresas.add(empresa);
       
    }
    
        public void updateEmpresa(Empresa empresaNueva, Empresa empresaVieja){
        
         EmpresaListController.empresas.remove(empresaVieja);
         EmpresaListController.empresas.add(empresaNueva);
       
    }
        
        public static void setEditMode(Empresa emp){
        
            isEdit = true;
            
            empresa =  emp;
            
        }
        
        private void showAlert(String message) {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Advertencia");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}
    
}