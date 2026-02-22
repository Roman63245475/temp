package easv.workshop_sco.dal;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.io.IOException;
import java.sql.*;

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



    public void getEmployees(){
        try(Connection con = cm.getConnection();){
            Statement stmt = con.createStatement();
            String sqlPrompt = "SELECT * FROM employee";
            PreparedStatement preparedStatement = con.prepareStatement(sqlPrompt);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                System.out.println(rs.getString("name"));
            }
        }
        catch (SQLException e){
            System.out.println("pey pivo");
        }
    }
}
