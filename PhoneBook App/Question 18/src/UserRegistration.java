import Helpers.DBUtils;

import java.sql.Connection;
import java.sql.*;

public class UserRegistration {
    private Connection con;

    public UserRegistration() {
        try {
            con = DBUtils.getDbConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    void insert(String firstName, String lastName, int phone, String status) {
        try {
            String insert = "INSERT INTO users (first_name, last_name, phone, status) Values (?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(insert);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setInt(3, phone);
            statement.setString(4, status);
            statement.executeUpdate();
            statement.close();//it used to close the connection after run the statement.


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    ResultSet get() {
        try {
            String select = "SELECT * FROM users";
            PreparedStatement statement = con.prepareStatement(select);
            return statement.executeQuery();//return statement.executeQuery(); is used for select.


        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        return null;//if try catch occur error then we have to return null.
    }

    void update( String firstName, String lastName, int phone, String status) {
        try {//Here "?" are placeholders.
            String update = "UPDATE users SET first_name=?, last_name =?, status =? WHERE phone =?  ";
            PreparedStatement statement = con.prepareStatement(update);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, status);
            statement.setInt(4, phone);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }

    void delete(int phone) {
        String delete = "DELETE FROM users WHERE  phone = ?";
        try {

            PreparedStatement statement = con.prepareStatement(delete);
            statement.setInt(1, phone);
            statement.execute();
            statement.close();

        }
        catch (SQLException ex) {
            ex.printStackTrace();

        }
    }
}

