package easv.workshop_sco.bll;

import easv.workshop_sco.dal.EmployeeAccessObject;

public class Logic {

    EmployeeAccessObject eao;
    public Logic(){
        eao = new EmployeeAccessObject();
    }
    public void getEmployees() {
        eao.getEmployees();
    }
}