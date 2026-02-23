package easv.workshop_sco.dal;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import easv.workshop_sco.be.Employee;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeAccessObject {

    ConnectionManager cm;

    public EmployeeAccessObject()  {
        try {
            cm = new ConnectionManager();
        }
        catch (IOException i){
            System.out.println("error appeared");
        }
    }



    public ArrayList<Employee> getEmployees(){
        ArrayList<Employee> lst = new ArrayList<>();
        Connection con = null;
        try{
            con = cm.getConnection();
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            Statement stmt = con.createStatement();
            String sqlPrompt = "SELECT * FROM employee LEFT JOIN salary_group on employee.salary_group = salary_group.id";
            PreparedStatement preparedStatement = con.prepareStatement(sqlPrompt);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                float personalBonus = rs.getFloat("personal_bonus");
                boolean onLeave = rs.getBoolean("on_leave");
                String phoneNumber = rs.getString("phone_number");
                String salaryGroup = rs.getString("group_name");
                float salary = rs.getFloat("salary");
                lst.add(new Employee(id, name, personalBonus, onLeave, phoneNumber, salary, salaryGroup));
            }
            con.commit();
        }
        catch (SQLException e){
            try {
                con.rollback();
            }
            catch (SQLException ex) {
                Logger.getLogger(EmployeeAccessObject.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeAccessObject.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return lst;
    }



    public ArrayList<Employee> searchRecords(String searchValue) {
        String pattern = searchValue + "%";
        ArrayList<Employee> lst = new ArrayList<>();
        Connection con = null;

        try {
            con = cm.getConnection();
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            String sqlPrompt = "SELECT * FROM employee LEFT JOIN salary_group on employee.salary_group = salary_group.id where Cast(employee.id as varchar) like ? OR name like ? OR Cast(personal_bonus as varchar) like ? or phone_number like ?";
            PreparedStatement preparedStatement = con.prepareStatement(sqlPrompt);
            preparedStatement.setString(1, pattern);
            preparedStatement.setString(2, pattern);
            preparedStatement.setString(3, pattern);
            preparedStatement.setString(4, pattern);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                float personalBonus = rs.getFloat("personal_bonus");
                boolean onLeave = rs.getBoolean("on_leave");
                String phoneNumber = rs.getString("phone_number");
                String salaryGroup = rs.getString("group_name");
                float salary = rs.getFloat("salary");
                lst.add(new Employee(id, name, personalBonus, onLeave, phoneNumber, salary, salaryGroup));
            }
            con.commit();
        }
        catch (SQLException e){
            System.out.println("pey pivo from search_records");
            if (con != null) {
                try {
                    con.rollback();
                }
                catch (SQLException ex) {
                    Logger.getLogger(EmployeeAccessObject.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        finally {
               if (con != null) {
                   try {
                       con.close();
                   } catch (SQLException e) {}
               }
        }
        return lst;
    }

    public void deleteEmployee(int id) {
        Connection con = null;
        try {
            con = cm.getConnection();
            con.setAutoCommit(false);
            String sqlPrompt = "DELETE FROM employee WHERE id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sqlPrompt);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            con.commit();
        }
        catch (SQLException e){
            if (con != null) {
                try {
                    con.rollback();
                }
                catch (SQLException ex) {}
            }
        }
        finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {}
            }
        }
        System.out.println("employee successfully deleted");
    }

    public void getNewEmployeeData(String name, float salary, boolean onLeave, String phoneNumber) {
        Connection con = null;
        try {
            con = cm.getConnection();
            con.setAutoCommit(false);
            String sqlPrompt = "Insert Into employee (name, personal_bonus, on_leave, phone_number) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(sqlPrompt);
            preparedStatement.setString(1, name);
            preparedStatement.setFloat(2, salary);
            preparedStatement.setBoolean(3, onLeave);
            preparedStatement.setString(4, phoneNumber);
            preparedStatement.execute();
        }
        catch (SQLException e){
            if (con != null) {
                try {
                    con.rollback();
                }
                catch (SQLException ex) {}
            }
        }
        finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {}
            }
        }
        System.out.println("new employee successfully added");
    }

    public void getEditEmployeeData(String name, float salary, boolean onLeave, String phoneNumber, Employee obj) {

        Connection con = null;
        try {
            con = cm.getConnection();
            con.setAutoCommit(false);
            String sqlPrompt = "Update employee set name = ?, personal_bonus = ?, on_leave = ?, phone_number = ? where id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sqlPrompt);
            preparedStatement.setString(1, name);
            preparedStatement.setFloat(2, salary);
            preparedStatement.setBoolean(3, onLeave);
            preparedStatement.setString(4, phoneNumber);
            preparedStatement.setInt(5, obj.getId());
            preparedStatement.execute();
        }
        catch (SQLException e){
            if (con != null) {
                try {
                    con.rollback();
                }
                catch (SQLException ex) {}
            }
        }
        finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {}
            }
        }
        System.out.println("record successfully updated");
    }
}
