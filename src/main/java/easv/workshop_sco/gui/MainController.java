package easv.workshop_sco.gui;

import easv.workshop_sco.be.Employee;
import easv.workshop_sco.bll.Logic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;


public class MainController implements Initializable {
    @FXML
    private Label welcomeText;

    private Logic logic;

    @FXML
    private TableView<Employee> employeeTable;

    @FXML
    private TableColumn<Employee,String> nameColumn;

    @FXML
    private TableColumn<Employee,String> salaryColumn;

    @FXML
    private TableColumn<Employee,String> onLeaveColumn;

    ObservableList<Employee> lst;

    @FXML
    private TableColumn<Employee,String> phoneNumberColumn;

    @FXML
    private TextField searchField;


    @FXML
    protected void onHelloButtonClick() {
        logic.getEmployees();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lst = FXCollections.observableArrayList();
        logic = new Logic();
        searchField.textProperty().addListener((observable, oldValue, newValue) -> searchRecords(newValue));
        setUpEmpolyeeTable();
        setTableViewButtons();
    }

    private void setTableViewButtons(){
        employeeTable.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.DELETE || event.getCode() == KeyCode.BACK_SPACE) {
                Employee employee = employeeTable.getSelectionModel().getSelectedItem();
                if (employee != null) {
                    logic.deleteEmployee(employee.getId());
                    lst.setAll(logic.getEmployees());
                }
            }
        });
    }

    private void setUpEmpolyeeTable(){
        lst.setAll(logic.getEmployees());
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
        onLeaveColumn.setCellValueFactory(new PropertyValueFactory<>("onLeave"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        employeeTable.setItems(lst);
    }


    private void searchRecords(String newValue){
        newValue = newValue.strip();
        lst.setAll(logic.searchRecords(newValue));
        System.out.println("table_view changed");
    }

    @FXML
    private void addNewEmployee(){
        openWindow("New Employee", null);
    }

    private void openWindow(String title, Employee obj){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("new_employee.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(fxmlLoader.load()));
            EmployeeController ec = fxmlLoader.getController();
            ec.getMainController(this);
            ec.getObject(obj);
            stage.setTitle(title);
            stage.showAndWait();
        }
        catch (IOException e) {
            System.out.println("something went wrong idk");
        }
    }

    @FXML
    private void editEmployee(){
        Employee employee = employeeTable.getSelectionModel().getSelectedItem();
        if (employee != null) {
            openWindow("Edit Employee", employee);
        }
    }

    public void getNewEmployeeData(String name, double salary, boolean onLeave, String phoneNumber){
        logic.getNewEmployeeData(name, salary, onLeave, phoneNumber);
        lst.setAll(logic.getEmployees());
    }


    public void getEditEmployeeData(String name, double salary, boolean onLeave, String phoneNumber, Employee obj) {
        logic.getEditEmployeeData(name, salary, onLeave, phoneNumber, obj);
        lst.setAll(logic.getEmployees());
    }
}
