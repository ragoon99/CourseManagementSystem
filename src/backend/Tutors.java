package backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import backend.Details.TutorDetails;
import backend.Interfaces.TutorInterface;
import database.DBConnector;
import exception.DuplicateTutorModuleRegistrationExecption;
import exception.ErrorGUI;
import exception.TutorModuleCountOnLimitException;

/**
 * This class file implements the TutorInterface where methods are predefined and
 * inherits the DBConnector for Database Conectivity
 * 
 * This Class contains all the methods required for CRUD functionality of Tutor
 */
public class Tutors extends DBConnector implements TutorInterface {
	
	/**
	 * This function is implemented from TutorInterface Interface
	 * which is used to Create Tutor 
	 * 
	 * @param Details of the Tutor are Stored in the TutorDetailClass
	 * 
	 * @returns true if Tutor is successfully added to Database
	 * 			returns false if error occurs
	 */
	@Override
	public boolean createTutor(TutorDetails t) {
		Connection con = getConnection();
		
		try {
            PreparedStatement st = con.prepareStatement("SELECT * FROM tutorcreds WHERE email=?");
            
            st.setInt(1, t.getTutId());
            
            ResultSet rSet = st.executeQuery();
            rSet.next();
            
            if (rSet.isLast()) {
            	throw new SQLIntegrityConstraintViolationException();
            }
            
            st = con.prepareStatement("INSERT INTO tutor "
                    + "(id, firstName, lastName) "
                    + "Values (?, ?, ?)");

	        st.setInt(1, t.getTutId());
	        st.setString(2, t.getFirstName());
	        st.setString(3, t.getLastName());
	
	        st.executeUpdate();
	        
	        PreparedStatement anotherSt = con.prepareStatement("INSERT INTO tutorcreds (id, email, password) VALUES (?, ?, ?)");
	        
	        anotherSt.setInt(1, t.getTutId());
	        anotherSt.setString(2, t.getEmail());
	        anotherSt.setString(3, t.getPassword());
	        
	        anotherSt.execute();
            
            con.close();
            
            return true;
        } catch (SQLException e) {
            if (e instanceof SQLIntegrityConstraintViolationException) {
            	e.printStackTrace();
            	new ErrorGUI("Email is Already in Use");
            } else {
            	new ErrorGUI(null, e);
            }
            return false;
        }
	}

	/**
	 * This function is implemented from TutorInterface which is 
	 * used to Update Tutor Information
	 * 
	 * @param Details of the Tutor are Stored in the TutorDetailClass
	 * @param new id to be assigned as String
	 * 
	 * @returns true if Tutor Information is successfully updated in Database
	 * 			returns false if error occurs
	 */
	@Override
	public boolean updateTutor(TutorDetails t) {
		try {
            Connection con = getConnection();
            
            PreparedStatement st = con.prepareStatement("SELECT * FROM tutorcreds WHERE email=?");
            
            st.setString(1, t.getEmail());
            
            ResultSet rSet = st.executeQuery();
            rSet.next();
            
            if (rSet.isLast()) {
            	throw new SQLIntegrityConstraintViolationException();
            }

            st = con.prepareStatement("UPDATE tutor SET "
                            + "firstName=?, lastName=? "
                            + "WHERE id=?");
            
            st.setString(1, t.getFirstName());
            st.setString(2, t.getLastName());
            st.setInt(3, t.getTutId());

            st.executeUpdate();
            
            PreparedStatement anotherSt = con.prepareStatement("UPDATE tutorcreds SET email=? "
            		+ "WHERE id=?");
            
            anotherSt.setString(1, t.getEmail());
            anotherSt.setInt(2, t.getTutId());
            
            anotherSt.execute();
            
            st = con.prepareStatement("UPDATE `tutor_enrollment` SET "
            		+ "`module_code`=? WHERE `tutor_id`=?");
            
            st.setString(1, (String) t.getModule1code());
            st.setInt(2, t.getTutId());

            con.close();
            
            return true;
        } catch (SQLException e) {
            new ErrorGUI("Email is Already Registered...", e);
            
            return false;
        }
	}

	/**
	 * This function is implemented from TutorInterface which is 
	 * used to Remove Tutor
	 * 
	 * @param ID of the Tutor to be removed
	 * 
	 * @returns true if Tutor is successfully Deleted from Database
	 * 			returns false if error occurs
	 */
	@Override
	public boolean removeTutor(int tutID) {
		try {
            Connection con = getConnection();
            
            PreparedStatement ps = con.prepareStatement("DELETE FROM tutor WHERE id=?");
            ps.setInt(1, tutID);
            ps.execute();

            con.close();

            return true;
        } catch (Exception e) {
        	new ErrorGUI("Error Occured While Deleting Tutor Details", e);
        	return false;
        }
	}

	/**
	 * This function is implemented from TutorInterface which is 
	 * used to enroll Certain Tutor to a Module
	 * 
	 * 
	 * @returns true if Module is successfully enrolled
	 * 			false if error occurs
	 */
	@Override
	public boolean enrollModule(TutorDetails tutorDetails) {
		Connection con = getConnection();
		try {
			PreparedStatement st = con.prepareStatement("SELECT module_code FROM `tutor_enrollment` WHERE `tutor_id`=?");
			st.setInt(1, tutorDetails.getTutId());
			
			ResultSet rs = st.executeQuery();
			
			int registerCount = 0;
			
			while(rs.next()) {
				registerCount++;
			}
			
            if (registerCount >= 4) {
            	throw new TutorModuleCountOnLimitException();
            }
            
            st = con.prepareStatement("SELECT module_code FROM tutor_enrollment WHERE tutor_id=? AND module_code=?");
            st.setInt(1, tutorDetails.getTutId());
            st.setString(2, tutorDetails.getModule1code());
            
            rs = st.executeQuery();
            
            if(rs.next()) {
            	throw new DuplicateTutorModuleRegistrationExecption();
            }
			
            st = con.prepareStatement("INSERT INTO `tutor_enrollment`(`tutor_id`, `module_code`) "
	        		+ "VALUES (?, ?)");
	
	        st.setInt(1, tutorDetails.getTutId());
	        st.setString(2, tutorDetails.getModule1code());
	
	        st.execute();

            con.close();

            return true;
        } catch (SQLException | TutorModuleCountOnLimitException | DuplicateTutorModuleRegistrationExecption e) {
        	if (e instanceof SQLException) {
        		new ErrorGUI("Error Occured While Registring Tutor Details");        		
        	} else {
        		new ErrorGUI(e.getMessage());
        	}
        	return false;
        }
	}

	/**
	 * This function is implemented from TutorInterface which is 
	 * used to get Details of existing Student in database
	 * 
	 * 
	 * @returns Details of the Tutor stored in the TutorDetails class
	 */
	@Override
	public TutorDetails getTutorDetails(int tutID) {
		try {
			ArrayList<String> modules = new ArrayList<String>();
			
			TutorDetails tutorDetails= new TutorDetails();
			
            Connection con = getConnection();
            
            PreparedStatement stmt = con.prepareStatement("SELECT tutor.firstName, tutor.lastName, tutor.dob, tutor.address, tutor.gender, "
            		+ "tutorcreds.email, tutor_enrollment.module_code, tutorcreds.password FROM tutor "
            		+ "INNER JOIN tutorcreds ON tutor.id=tutorcreds.id "
            		+ "INNER JOIN tutor_enrollment ON tutorcreds.id=tutor_enrollment.tutor_id "
            		+ "WHERE tutor.id=?");
            
            stmt.setInt(1, tutID);
            
            ResultSet rs = stmt.executeQuery();
            
            rs.next();
            tutorDetails.setTutId(tutID);
            tutorDetails.setFirstName(rs.getString("firstName"));
            tutorDetails.setLastName(rs.getString("lastName"));
            tutorDetails.setEmail(rs.getString("email"));
            tutorDetails.setDob(rs.getDate("dob"));
            tutorDetails.setAddress(rs.getString("address"));
            tutorDetails.setGender(rs.getString("gender"));
            tutorDetails.setPassword(rs.getString("password"));
            modules.add(rs.getString("module_code"));
            
            
            while (rs.next()) {
            	modules.add(rs.getString("module_code"));
            }
            
            tutorDetails.setModules(modules);
            
            con.close();
            
            return tutorDetails;
        } catch (SQLException e) {
            new ErrorGUI("Error Occured While Appending Data to Database.", e);
        	
            return null;
        }
	}
	
	/**
	 * This function is implemented from TutorInterface which is 
	 * used to get generate ID for Tutor 
	 * 
	 * 
	 * @returns int ID for the Tutor to be assigned
	 */
	@Override
	public int generateTutorID() {
		Connection con = getConnection();
		
		try {
            
            PreparedStatement stmt = con.prepareStatement("SELECT MAX(id) AS id FROM tutor");
            
            ResultSet rs = stmt.executeQuery();
            
            rs.next();
            int tutorID = rs.getInt("id") + 1;
            
            con.close();
            
            return tutorID;
        } catch (SQLException e) {
            new ErrorGUI("Error Occured While Generating ID.", e);
        	
            return 0;
        }
	}
	
	
	/**
	 * This function is implemented from TutorInterface which is 
	 * used to get generate ID for Tutor 
	 * 
	 * 
	 * @returns int ID for the Tutor to be assigned
	 */
	@Override
	public boolean assignMarks(float mark, int stdId, String moduleCode) {
		Connection con = getConnection();
		
		try {
            
            PreparedStatement stmt = con.prepareStatement("UPDATE student_enrollment SET final_grade=? WHERE student_id=? AND module_code=?");
            stmt.setFloat(1, mark);
            stmt.setInt(2, stdId);
            stmt.setString(3, moduleCode);
            
            stmt.executeUpdate();
            
            con.close();
            
            return true;
        } catch (SQLException e) {
            new ErrorGUI("Error Occured While Assigning Mark", e);
        	
            return false;
        }
	}
}
