package easv.workshop_sco.gui;

import easv.workshop_sco.bll.Logic;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;


public class MainController implements Initializable {
    @FXML
    private Label welcomeText;

    private Logic logic;

    private TextField searchField;



    @FXML
    protected void onHelloButtonClick() {
        logic.getEmployees();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logic = new Logic();
        searchField.textProperty().addListener((observable, oldValue, newValue) -> search_records(newValue));
    }

    private void search_records(String newValue){
        newValue = newValue.strip();
    }


}
