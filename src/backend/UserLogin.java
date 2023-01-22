package backend;

import db.dbDetails;
import exception.ErrorGUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;

/**
 *
 * @author Ragoon
 */
public class UserLogin extends dbDetails {
	private boolean state = false;

    public boolean adminLogin (int id, String pass) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + super.dbName, super.username, super.pswd);
            
            PreparedStatement st = con.prepareStatement("SELECT id FROM admin WHERE id=?");
            st.setInt(1, id);
            
            ResultSet rs = st.executeQuery();
            
            state = rs.next();
            
            if (state) {
                st = con.prepareStatement("SELECT password FROM admin WHERE id=? AND password=?");
                st.setInt(1, id);
                st.setString(2, pass);
                
                if (st.execute()) {
                	con.close();
                	return true;
                }
                
                con.close();
                return false;
            } 
            
            con.close();
            
            return false;
		} catch (Exception e) {
            if(e instanceof ClassNotFoundException) {
            	System.out.println("Error While Loading Driver");
            } else if (e instanceof SQLIntegrityConstraintViolationException) {
            	System.out.println("Module is Already Regsitered in Database.");
            } else if (e instanceof CommunicationsException) {
            	new ErrorGUI("Error While Connecting to the Server").setVisible(true);
            } else {
            	e.printStackTrace();
            }
            
            return false;
		}
    }
    
    public boolean tutorLogin (int id, String pass) {
    	return tutorLogin(id, null, pass);
    }
    
    public boolean tutorLogin (String email, String pass) {
    	return tutorLogin(0, email, pass);
    }
    
    public boolean tutorLogin (int id, String email, String pass) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + super.dbName, super.username, super.pswd);
            
            PreparedStatement st = con.prepareStatement("SELECT teacherId FROM tutorcreds WHERE teacherId=? OR email=?");
            st.setInt(1, id);
            st.setString(2, email);
            
            ResultSet rs = st.executeQuery();
            
            rs.first();
            
            state = rs.next();
            
            System.out.println(state);
            
            if (state) {
                st = con.prepareStatement("SELECT password FROM tutorcreds WHERE (teacherID=? OR email=?) AND password=?");
                st.setInt(1, id);
                st.setString(2, email);
                st.setString(3, pass);
                
                if (st.execute()) {
                	con.close();
                	return true;
                }
                
                con.close();
                return false;
            } 
            
            con.close();
            
            return false;
        } catch (Exception e) {
            if(e instanceof ClassNotFoundException) {
            	System.out.println("Error While Loading Driver");
            } else if (e instanceof SQLIntegrityConstraintViolationException) {
            	System.out.println("Module is Already Regsitered in Database.");
            } else if (e instanceof CommunicationsException) {
            	new ErrorGUI("Error While Connecting to the Server").setVisible(true);
            } else {
            	e.printStackTrace();
            }
            
            return false;
		}
    }
    
    public boolean studentLogin (int id, String pass) {
    	return studentLogin(id, null, pass);
    }
    
    public boolean studentLogin (String email, String pass) {
    	return studentLogin(0, email, pass);
    }
    
    public boolean studentLogin (int id, String email, String pass) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + super.dbName, super.username, super.pswd);
            
            PreparedStatement st = con.prepareStatement("SELECT studentId FROM studentcreds WHERE studentId=? OR email=?");
            st.setInt(1, id);
            st.setString(2, email);
            
            ResultSet rs = st.executeQuery();
            
            state = rs.next();
            
            if (state) {
                st = con.prepareStatement("SELECT password FROM studentcreds WHERE (studentId=? OR email=?) AND password=?");
                st.setInt(1, id);
                st.setString(2, email);
                st.setString(3, pass);
                
                if (st.execute()) return true;
                
                return false;
            } 
            
            con.close();
            
            return false;
        } catch (Exception e) {
            if(e instanceof ClassNotFoundException) {
            	System.out.println("Error While Loading Driver");
            } else if (e instanceof SQLIntegrityConstraintViolationException) {
            	System.out.println("Module is Already Regsitered in Database.");
            } else if (e instanceof CommunicationsException) {
            	new ErrorGUI("Error While Connecting to the Server");
            } else {
            	e.printStackTrace();
            }
            
            return false;
		}
    }
}
