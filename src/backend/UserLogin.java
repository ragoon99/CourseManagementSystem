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
	private final String t1 = "admin";
	private final String t2 = "tutorcreds";
	private final String t3 = "studentcreds";
	private boolean state = false;
	
	public boolean adminLogin(String id, String pass) {
		return this.userLogin(id, pass, t1);
	}
	
	public boolean tutorLogin(String id, String pass) {
		return this.userLogin(id, pass, t2);
	}
	
	public boolean studentLogin(String id, String pass) {
		return this.userLogin(id, pass, t3);
	}
    
    private boolean userLogin (String id, String pass, String tableName) {
        try {
            Connection con = getConnection();
            
            PreparedStatement st = con.prepareStatement("SELECT id FROM "+ tableName + " WHERE id=?");
            st.setString(1, id);
            
            ResultSet rs = st.executeQuery();
            
            state = rs.next();
            
            if (state) {
                st = con.prepareStatement("SELECT password FROM "+ tableName + " WHERE id=? AND password=?");
                st.setString(1, id);
                st.setString(2, pass);
                
                rs = st.executeQuery();
                
                if (rs.next()) {
                	con.close();
                	return true;
                }
                con.close();
                return false;
            } 
            
            con.close();
            
            return false;
		} catch (Exception e) {
            if (e instanceof SQLIntegrityConstraintViolationException) {
            	System.out.println("Module is Already Regsitered in Database.");
            } else if (e instanceof CommunicationsException) {
            	new ErrorGUI("Error While Connecting to the Server").setVisible(true);
            } else {
            	e.printStackTrace();
            }
            
            return false;
		}
    }
    
}
