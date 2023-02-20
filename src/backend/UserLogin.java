package backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;

import backend.Details.LoginDetails;
import database.DBConnector;
import exception.ErrorGUI;

/**
 * This class contains methods for Loggin Users in to the System
 *
 *
 * @author Ragoon
 */
public class UserLogin extends DBConnector {
	
	/**
	 * This method is used to login in user
	 * 
	 * @param LoginDetails which contains the credentials to try loggin from
	 * 
	 * @return true if credentials match the database
	 * 			false if credentials does not match
	 * 
	 */
	public boolean userLogin(LoginDetails details) {
		try {
            Connection con = getConnection();
            
            PreparedStatement st = con.prepareStatement("SELECT id FROM " + details.getTableName() + " WHERE id=? AND password=?");
            st.setInt(1, details.getUid());
            st.setString(2, details.getPassword());
            
            ResultSet rs = st.executeQuery();
            
            boolean state = rs.next();
            
            if (state) {
            	if (details.getTableName().equals(details.getStudentTable())) st = con.prepareStatement("INSERT INTO login_activity (userID, type) VALUES (?, 'Student')");
            	if (details.getTableName().equals(details.getTutorTable())) st = con.prepareStatement("INSERT INTO login_activity (userID, type) VALUES (?, 'Tutor')");
            	if (details.getTableName().equals(details.getAdminTable())) st = con.prepareStatement("INSERT INTO login_activity (userID, type) VALUES (?, 'Admin')");
            	
            	st.setInt(1, details.getUid());
            	
            	st.execute();
            	
                con.close();
                
                return true;
            } 
            
            con.close();
            
            return false;
		} catch (Exception e) {
            if (e instanceof CommunicationsException) {
            	new ErrorGUI("Error While Connecting to the Server").setVisible(true);
            }
            
            return false;
		}
	}
}
