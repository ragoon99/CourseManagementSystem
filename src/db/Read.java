package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Read extends dbDetails {
    public ResultSet readTable(String tableName) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(super.url + super.dbName, super.username, super.pswd);

            PreparedStatement ps = con.prepareStatement("SELECT * FROM " + tableName);
            ResultSet rs = ps.executeQuery();

            con.close();
            
            return rs;
        } catch (ClassNotFoundException | SQLException e) {

            if(e instanceof ClassNotFoundException) {
                System.out.println("Error While Loading Driver");
            } else {
                System.out.println("Error While Reading Table");
            }
            
            return null;
        }
    }
  
    void getTable (String tablename) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(super.url + super.dbName, super.username, super.pswd);
            
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tablename);
            
            ResultSetMetaData rsmd = rs.getMetaData();
            
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                
                System.out.println(rsmd.getColumnName(i));
                
            }
            
            con.close();
        } catch (ClassNotFoundException | SQLException e) {

            if(e instanceof ClassNotFoundException) {
                    System.out.println("Error While Loading Driver");
            } else {
                    System.out.println("Error While Reading Table");
            }
            
        }
        
    }
    
    public Object[] getCourses() {
    	try {
    		ArrayList<String> course= new ArrayList<>();
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(super.url + super.dbName, super.username, super.pswd);
            
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT courseCode FROM courses");
            
            while(rs.next()) {
            	course.add(rs.getString(1));
            }
            
            con.close();
            
            return course.toArray();
        } catch (ClassNotFoundException | SQLException e) {

            if(e instanceof ClassNotFoundException) {
                    System.out.println("Error While Loading Driver");
            } else {
            	e.printStackTrace();
                System.out.println("Error While Reading Table");
            }
            
        }
		return null;
    }
    
    public int getID() {
    	try {
    		int id = 0;
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(super.url + super.dbName, super.username, super.pswd);
            
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id FROM student ORDER BY id DESC LIMIT 1");
            
            rs.next();
            
            id = rs.getInt(1);
            
            con.close();
            
            return id;
        } catch (ClassNotFoundException | SQLException e) {

            if(e instanceof ClassNotFoundException) {
                    System.out.println("Error While Loading Driver");
            } else {
            	e.printStackTrace();
                System.out.println("Error While Reading Table");
            }
            
        }
		return 0;
    }
}
