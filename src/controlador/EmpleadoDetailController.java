
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import modelo.Empleado;
import modelo.Empresa;

/**
 * FXML Controller class
 *
 * @author diaza
 */
public class EmpleadoDetailController implements Initializable {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtId;
    @FXML
    private Button btnGuardar;

    private static boolean isEdit;
    @FXML
    private TextField txtApellido;
    @FXML
    private TextField txtMail;
    
    private static Empleado empleado;
    
     private static Empresa empresa;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(isEdit){
        this.txtNombre.setText(empleado.getNombre());
        this.txtApellido.setText(empleado.getApellido());
        this.txtTelefono.setText(String.valueOf(empleado.getTelefono()));
        this.txtMail.setText(empleado.getMail());
        this.txtId.setText(String.valueOf(empleado.getId()));
      
        }else{
        
        this.txtNombre.clear();
        }

        
    }    

    @FXML
    private void handleGuardar(ActionEvent event) {
           String nombre = txtNombre.getText();
    String apellido = txtApellido.getText();
    String telefonoStr = txtTelefono.getText();
    String idStr = txtId.getText();
    String mail = txtMail.getText();

    
    if (nombre == null || nombre.trim().isEmpty()) {
        showAlert("El nombre no puede estar vacío.");
        return; 
    }

    if (apellido == null || apellido.trim().isEmpty()) {
        showAlert("El apellido no puede estar vacío.");
        return; 
    }

    if (telefonoStr == null || telefonoStr.trim().isEmpty()) {
        showAlert("El teléfono no puede estar vacío.");
        return;
    }

    if (idStr == null || idStr.trim().isEmpty()) {
        showAlert("El ID no puede estar vacío.");
        return; 
    }

    if (mail == null || mail.trim().isEmpty()) {
        showAlert("El correo electrónico no puede estar vacío.");
        return; 
    }

    try {
        int telefono = Integer.parseInt(telefonoStr);
        int id = Integer.parseInt(idStr);

       
        Empleado empleadoNuevo = new Empleado(id, nombre, apellido, telefono, mail, empresa.getId());

       
        if (isEdit) {
            updateEmpleado(empleadoNuevo, empleado);
        } else {
            insertarEmpleado(empleadoNuevo);
        }
    } catch (NumberFormatException e) {
        showAlert("El teléfono y el ID deben ser números válidos.");
    } catch (Exception e) {
        showAlert("Ocurrió un error inesperado: " + e.getMessage());
    }
         isEdit = false;
        MainLayoutController mainController = Main.getMainController();
        mainController.showEmpresa();
    }
    
    
    public void insertarEmpleado(Empleado empleado){
        
        ListarEmpleadoController.empleados.add(empleado);
       
    }
    
       public void updateEmpleado(Empleado empleadoNuevo, Empleado empleadoViejo){
        
        ListarEmpleadoController.empleados.remove(empleadoViejo);   
        ListarEmpleadoController.empleados.add(empleadoNuevo);
       
    }
    
      public static void setEmpresa(Empresa emp) {   
      empresa = emp;
    }
      
      public static void setEditMode(Empleado emp){
            isEdit = true;
            
            empleado = emp;
      }
      
      private void showAlert(String message) {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Advertencia");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}
    
}
