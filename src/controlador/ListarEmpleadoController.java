package controlador;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import modelo.Empleado;
import modelo.Empresa;

public class ListarEmpleadoController implements Initializable {

    
    @FXML
    private TableView<Empleado> tblEmpleados;

    
    private static Empresa empresa;
    
   public static ObservableList<Empleado> listaEmpleados = FXCollections.observableArrayList();
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnEditar;
    @FXML
    private TableColumn columNombre;
    @FXML
    private TableColumn columnTelefono;
    @FXML
    private TableColumn columnMail;
    
     public static ObservableList<Empleado> empleados = FXCollections.observableArrayList(); 
    @FXML
    private TableColumn columApellido;
    @FXML
    private VBox formContainer;
    @FXML
    private Label lblEmpresa;
    @FXML
    private TableColumn<Empleado, Void> columBoton;
    
    ObservableList<Empleado> empleadosFiltrados = FXCollections.observableArrayList();
 


    @Override
    public void initialize(URL url, ResourceBundle rb) {

            lblEmpresa.setText(empresa.getNombre());

           this.columNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
           this.columApellido.setCellValueFactory(new PropertyValueFactory("apellido"));
           this.columnTelefono.setCellValueFactory(new PropertyValueFactory("telefono"));
           this.columnMail.setCellValueFactory(new PropertyValueFactory("mail"));

       columBoton.setCellFactory(param -> new TableCell<Empleado, Void>() {


   private final Button btn = new Button("Liquidar");

        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            
            if (empty) {
                setGraphic(null);
            } else {
                final Empleado empresa = getTableView().getItems().get(getIndex());
                
                btn.setOnAction(event -> handleButton(empresa));
                
                setGraphic(btn);
            }
        }
    });
           
           for(Empleado empleado : empleados){
           if(empleado.getEmpresaId() == empresa.getId()){
           empleadosFiltrados.add(empleado);
           }
           }
           if(empleadosFiltrados.isEmpty() && empleados.isEmpty()){
           Empleado e = new Empleado(1,"Juanito","Castillos",3454,"Juanitocas@gmail.com",empresa.getId());
           empleados.add(e);
           tblEmpleados.setItems(empleados);
           }else{
           tblEmpleados.setItems(empleadosFiltrados);
           }
    }    
    
    public static void setEmpresa(Empresa emp) {   
      empresa = emp;
    }

    @FXML
    private void navegarEliminarEmpleado(ActionEvent event) {
       Empleado selectedEmpleado = tblEmpleados.getSelectionModel().getSelectedItem();
    
    if (selectedEmpleado != null) {
        Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmDialog.setTitle("Confirmar Eliminación");
        confirmDialog.setHeaderText(null);
        confirmDialog.setContentText("¿Estás seguro de que deseas eliminar al empleado seleccionado?");
        
        Optional<ButtonType> result = confirmDialog.showAndWait();
       
        if (result.isPresent() && result.get() == ButtonType.OK) {
            empleados.remove(selectedEmpleado);
            empleadosFiltrados.remove(selectedEmpleado);
        }
        
    }else{
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Advertencia");
        alert.setHeaderText(null);
        alert.setContentText("No se seleccionó ningún empleado");
        alert.showAndWait();
        }
    }

    @FXML
    private void navegarAgregarEmpleado(ActionEvent event) {
        EmpleadoDetailController.setEmpresa(empresa);
        MainLayoutController mainController = Main.getMainController();
        mainController.showAgregarEmpleado();
    }

    @FXML
    private void navegarEditarEmpleado(ActionEvent event) {
             
        Empleado selectedEmpleado = tblEmpleados.getSelectionModel().getSelectedItem();

        if (selectedEmpleado == null) {
              Alert alert = new Alert(Alert.AlertType.WARNING);
               alert.setTitle("Advertencia");
               alert.setHeaderText(null);
               alert.setContentText("No se seleccionó ningún empleado");
               alert.showAndWait();
        }else{
           EmpleadoDetailController.setEmpresa(empresa);
            EmpleadoDetailController.setEditMode(selectedEmpleado);
            MainLayoutController mainLayout = Main.getMainController();
            mainLayout.showAgregarEmpleado();
        }
 
    }
    
   private void handleButton(Empleado empleado){
        
        MainLayoutController mainController = Main.getMainController();
        mainController.showLiquidacion();
   }
}
