package easv.workshop_sco.gui;

import easv.workshop_sco.be.Employee;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EmployeeController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField salaryField;

    @FXML
    private TextField onLeaveField;

    @FXML
    private TextField phoneNumberField;

    private MainController mc;

    private Employee employee;

    public void getMainController(MainController mc){
        this.mc=mc;
    }

    @FXML
    private void saveClick(){
        String name=nameField.getText();
        String strSalary=salaryField.getText();
        String strOnLeave=onLeaveField.getText();
        String phoneNumber=phoneNumberField.getText();
        boolean onLeave;
        double salary;
        if (name.isEmpty()){
            showAlert("name field can't be empty");
            return;
        }
        if (strSalary.isEmpty()){
            showAlert("salary field can't be empty");
            return;
        }
        try {
            salary=Double.parseDouble(strSalary);
        }
        catch (NumberFormatException e){
            showAlert("salary must either be an integer or float point number");
            return;
        }
        if (strOnLeave.isEmpty()){
            showAlert("onLeave field can't be empty");
            return;
        }
        try {
            onLeave=Boolean.parseBoolean(strOnLeave);
        }
        catch (NumberFormatException e){
            showAlert("on leave field should hold boolean value");
            return;
        }
        if (phoneNumber.isEmpty() || phoneNumber.length() > 50){
            showAlert("phoneNumber field can't be empty and can't be over 50 numbers");
            return;
        }
        if (this.employee != null){
            mc.getEditEmployeeData(name, salary, onLeave, phoneNumber, this.employee);
        }
        else {
            mc.getNewEmployeeData(name, salary, onLeave, phoneNumber);
        }

        Stage st = (Stage) nameField.getScene().getWindow();
        st.close();
    }

    public void getObject(Employee employee){
        this.employee = employee;
        if (this.employee != null){
            fillFields();
        }
    }

    @FXML
    private void cancelClick(){
        Stage st = (Stage) nameField.getScene().getWindow();
        st.close();
    }

    private void fillFields(){
        nameField.setText(employee.getName());
        salaryField.setText(String.valueOf(employee.getSalary()));
        onLeaveField.setText(String.valueOf(employee.getOnLeave()));
        phoneNumberField.setText(String.valueOf(employee.getPhoneNumber()));
    }


    private void showAlert(String message){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setContentText(message);
    }
}
