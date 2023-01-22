package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Delete extends dbDetails {
	void deleteTable (String tName) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(super.url + super.dbName, super.username, super.pswd);

            PreparedStatement ps = con.prepareStatement("DROP TABLE " + tName);
            ps.execute();

            con.close();

            System.out.println("Table Deleted Successfully.");

        } catch (ClassNotFoundException | SQLException e) {

            if(e instanceof ClassNotFoundException) {
                    System.out.println("Error While Loading Driver");
            } else {
                    System.out.println("Error While Deleting Table");
            }
        }
    }

    void deleteCourse(String id) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(super.url + super.dbName, super.username, super.pswd);

            PreparedStatement ps = con.prepareStatement("DELETE FROM courses WHERE courseCode=?");
            ps.setString(1, id);

            ps.execute();

            con.close();

            System.out.println("Entry Deleted.");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);

            if(e instanceof ClassNotFoundException) {
                    System.out.println("Error While Loading Driver");
            } else {
                    System.out.println("Error While Deleting Entry");
            }
        }
    }
    
}
