package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DBConnector {
    private Connection con;
    
    abstract void setConnection();
    
    public Connection getConnection() {
    	try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cms", "root", "");
        } catch (ClassNotFoundException | SQLException e) {
            if(e instanceof ClassNotFoundException) {
                System.out.println("Error While Loading Driver");
            }
        }
    	
    	return con;
    }
    
}