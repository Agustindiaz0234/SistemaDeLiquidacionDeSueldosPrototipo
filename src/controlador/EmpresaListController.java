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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modelo.Empresa;

public class EmpresaListController implements Initializable {

    @FXML
    private Button btnAgregar;
    @FXML
    private TableColumn columNombre;
    @FXML
    private TableColumn columDireccion;
    @FXML
    private TableColumn columnTelefono;
    @FXML
    private TableView<Empresa> tblEmpresas;
    
    public static ObservableList<Empresa> empresas = FXCollections.observableArrayList(); 
    @FXML
    private TableColumn<Empresa, Void> columBoton;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnEditar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        this.columNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.columDireccion.setCellValueFactory(new PropertyValueFactory("direccion"));
        this.columnTelefono.setCellValueFactory(new PropertyValueFactory("telefono"));
        
        
    columBoton.setCellFactory(param -> new TableCell<Empresa, Void>() {


   private final Button btn = new Button("Seleccionar");

        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            
            if (empty) {
                setGraphic(null);
            } else {
                final Empresa empresa = getTableView().getItems().get(getIndex());
                
                btn.setOnAction(event -> handleButton(empresa));
                
                setGraphic(btn);
            }
        }
    });
      if(empresas.isEmpty()){ 
        Empresa e = new Empresa(1, "AgusCorp", "Parana333", 3454);
         Empresa e1 = new Empresa(2, "AAACorp", "Japon324", 54432);
          Empresa e2 = new Empresa(3, "MessiCorp", "Miami", 3333333);
        empresas.add(e);
         empresas.add(e1);
          empresas.add(e2);
      }
        this.tblEmpresas.setItems(empresas);
    }    


    @FXML
    private void navegarAgregarEmpresa(ActionEvent event) {
          
        MainLayoutController mainController = Main.getMainController();
        mainController.showAgregarEmpresa();
    }
    
 
    
    private void handleButton(Empresa empresa){

    ListarEmpleadoController.setEmpresa(empresa);
    
    System.out.print("Se envio la empresa " + empresa);
     
      MainLayoutController mainController  = Main.getMainController();
      mainController.showEmpleadoList();

    }

    @FXML
    private void navegarEliminarEmpresa(ActionEvent event) {
        Empresa selectedEmpresa = tblEmpresas.getSelectionModel().getSelectedItem();
       
    
    if (selectedEmpresa != null) {
        Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmDialog.setTitle("Confirmar Eliminación");
        confirmDialog.setHeaderText(null);
        confirmDialog.setContentText("¿Estás seguro de que deseas eliminar la empresa seleccionada?");

        Optional<ButtonType> result = confirmDialog.showAndWait();
        
        if (result.isPresent() && result.get() == ButtonType.OK) {
            empresas.remove(selectedEmpresa);
        }
        
        }else{
               Alert alert = new Alert(Alert.AlertType.WARNING);
               alert.setTitle("Advertencia");
               alert.setHeaderText(null);
               alert.setContentText("No se seleccionó ningún empleado");
        alert.showAndWait();
        }
    }

    @FXML
    private void navegarEditarEmpresa(ActionEvent event) {
          Empresa selectedEmpresa = tblEmpresas.getSelectionModel().getSelectedItem();
       
          
        if (selectedEmpresa == null) {
              Alert alert = new Alert(Alert.AlertType.WARNING);
               alert.setTitle("Advertencia");
               alert.setHeaderText(null);
               alert.setContentText("No se seleccionó ningúna empresa");
               alert.showAndWait();
        }else{
            EmpresaDetailController.setEditMode(selectedEmpresa);
            MainLayoutController mainLayout = Main.getMainController();
            mainLayout.showAgregarEmpresa();
        }
       
        
    }

    
}
