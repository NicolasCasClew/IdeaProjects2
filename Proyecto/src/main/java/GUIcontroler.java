import DBs.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GUIcontroler implements Initializable {

    @FXML public TableView tvid;
    @FXML public TableColumn nifid;
    @FXML public TableColumn nombreid;
    @FXML public TableColumn apellidoid;
    @FXML public TableColumn skillsedid;

    public List<Employee> empLista = FXCollections.observableArrayList();
    public Button botonTest;
    public Text textoEdit;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        nifid.setCellValueFactory(new PropertyValueFactory<>("nif"));
        nombreid.setCellValueFactory(new PropertyValueFactory<>("name"));
        apellidoid.setCellValueFactory(new PropertyValueFactory<>("surname"));
        skillsedid.setCellValueFactory(new PropertyValueFactory<>("skillset"));

       //. empLista.add(Main.getAllEmployees());
        List<Employee> tempemp = Main.getAllEmployees();

        for (Employee e : tempemp){
            empLista.add(e);
        }
        tvid.setItems((ObservableList) empLista);
    }


    public void onActionBT(ActionEvent actionEvent) {
        Employee person = (Employee) tvid.getSelectionModel().getSelectedItem();
        System.out.println(person.getName());
        textoEdit.setText(person.getName());
    }
}
