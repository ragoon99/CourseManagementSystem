package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Delete extends dbDetails {
	void deleteTable (String tName) {
        try {
            Connection con = getConnection();

            PreparedStatement ps = con.prepareStatement("DROP TABLE " + tName);
            ps.execute();

            con.close();

            System.out.println("Table Deleted Successfully.");

        } catch (SQLException e) {
            System.out.println("Error While Deleting Table");
        }
    }

    void deleteCourse(String id) {
        try {
            Connection con = getConnection();

            PreparedStatement ps = con.prepareStatement("DELETE FROM courses WHERE courseCode=?");
            ps.setString(1, id);

            ps.execute();

            con.close();

            System.out.println("Entry Deleted.");
        } catch (SQLException e) {
            System.out.println(e);
            System.out.println("Error While Deleting Entry");
        }
    }
    
}
