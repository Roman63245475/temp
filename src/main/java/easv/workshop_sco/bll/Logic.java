package easv.workshop_sco.bll;

import easv.workshop_sco.be.Employee;
import easv.workshop_sco.dal.EmployeeAccessObject;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Logic {

    EmployeeAccessObject eao;
    public Logic(){
        eao = new EmployeeAccessObject();
    }
    public ArrayList<Employee> getEmployees() {
        return eao.getEmployees();
    }

    public ArrayList<Employee> searchRecords(String searchValue) {
        return eao.searchRecords(searchValue);
    }

    public void deleteEmployee(int id) {
        eao.deleteEmployee(id);
    }

    public void getNewEmployeeData(String name, double salary, boolean on_leave, String phoneNumber) {
        eao.getNewEmployeeData(name, Float.parseFloat(String.valueOf(salary)), on_leave, phoneNumber);
    }

    public void getEditEmployeeData(String name, double salary, boolean on_leave, String phoneNumber, Employee obj) {
        eao.getEditEmployeeData(name, Float.parseFloat(String.valueOf(salary)), on_leave, phoneNumber, obj);
    }
}