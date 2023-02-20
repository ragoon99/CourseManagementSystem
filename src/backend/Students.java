package backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import backend.Details.StudentDetails;
import backend.Interfaces.StudentInterface;
import database.DBConnector;
import exception.DuplicateStudentModuleRegistrationException;
import exception.ErrorGUI;
import exception.StudentModuleCountLimitException;

/**
 * This class file implements the StudentInterface where methods are predefined and
 * inherits the DBConnector for Database Conectivity
 * 
 * This Class contains all the methods required for CRUD functionality of Students
 */
public class Students extends DBConnector implements StudentInterface {
	
	/**
	 * This function is implemented from StudentInterface Interface
	 * which is used to Create Student 
	 * 
	 * @param Details of the Students are Stored in the Student Detail Class
	 * 
	 * @returns true if module is successfully added to Database
	 * 			returns false if error occurs
	 */
	@Override
	public boolean createStudent(StudentDetails stdDetails) {
		try {
            Connection con = getConnection();

            PreparedStatement st = con.prepareStatement("INSERT INTO student "
                            + "(id, firstName, lastName, dob, address, gender, courseEnrolled) "
                            + "Values (?, ?, ?, ?, ?, ?, ?);");
            st.setInt(1, stdDetails.getStdId());
            st.setString(2, stdDetails.getFirstName());
            st.setString(3, stdDetails.getLastName());
            st.setDate(4, stdDetails.getDob());
            st.setString(5, stdDetails.getAddress());
            st.setString(6, stdDetails.getGender());
            st.setString(7, stdDetails.getCourseEnrolled());
            
            st.execute();
            
            st = con.prepareStatement("INSERT INTO studentcreds (id, email, password) VALUES (?, ?, ?)");
            st.setInt(1, stdDetails.getStdId());
            st.setString(2, stdDetails.getEmail());
            st.setString(3, stdDetails.getPassword());
            
            st.execute();
            
            st = con.prepareStatement("INSERT INTO student_year (studentid, semester) VALUES (?, 1)");
            st.setInt(1, stdDetails.getStdId());
            
            st.execute();

            con.close();
            
            return true;
            
        } catch (SQLException e) {
            if (e instanceof SQLIntegrityConstraintViolationException) {
            	e.printStackTrace();
            	new ErrorGUI("Student is Already Regsitered.");
            } else {
            	new ErrorGUI("Error While Adding Student Detail", e);
            }
            return false;
        }
	}

	/**
	 * This function is implemented from StudentInterface which is 
	 * used to Remove Student
	 * 
	 * @param ID of the Student to be removed
	 * 
	 * @returns true if Student is successfully Deleted from Database
	 * 			returns false if error occurs
	 */
	@Override
	public boolean removeStudent(int stdID) {
		try {
            Connection con = getConnection();

            PreparedStatement ps = con.prepareStatement("DELETE FROM studentcreds WHERE id=?");
            ps.setInt(1, stdID);
            ps.execute();

            con.close();
            
            return true;
        } catch (Exception e) {
            new ErrorGUI("Error Occured While Deleting Student Details", e);
            return false;
        }
	}
	
	/**
	 * This function is implemented from StudentInterface which is 
	 * used to Update Student Information
	 * 
	 * @param Details of the Students are Stored in the Student Detail Class
	 * @param new id to be assigned as String
	 * 
	 * @returns true if Student is successfully updated in Database
	 * 			returns false if error occurs
	 */
	@Override
	public boolean updateStudent(StudentDetails stdDetails) {
		Connection con = getConnection();

		try {

            PreparedStatement st = con.prepareStatement("UPDATE student SET "
                            + "firstName=?, lastName=?, courseEnrolled=? "
                            + "WHERE id=?");

            st.setString(1, stdDetails.getFirstName());
            st.setString(2, stdDetails.getLastName());
            st.setString(3, stdDetails.getCourseEnrolled());
            st.setInt(4, stdDetails.getStdId());
            
            st.executeUpdate();

            con.close();
            
            return true;
        } catch (SQLException e) {
            new ErrorGUI("Error in Database", e);
            
            return false;
        }
	}
	
	/**
	 * This function is implemented from StudentInterface which is 
	 * used to enroll Certain Student to a Course
	 * 
	 * 
	 * @returns true if student is successfully enrolled
	 * 			false if error occurs
	 */
	@Override
	public boolean enrollModule(StudentDetails stdDetails, String courseCode) {
		Connection con = getConnection();
		
		try {
			PreparedStatement st = con.prepareStatement("SELECT module_code FROM student_enrollment WHERE student_id=?");
			st.setInt(1, stdDetails.getStdId());
			
			ResultSet rs = st.executeQuery();
			
			int registerCount = 0;
			
			while(rs.next()) {
				registerCount++;
			}
			
            if (registerCount >= 4) {
            	throw new StudentModuleCountLimitException();
            }
            
            st = con.prepareStatement("SELECT module_code FROM student_enrollment WHERE student_id=? AND module_code=?");
            st.setInt(1, stdDetails.getStdId());
            st.setString(2, courseCode);
            
            rs = st.executeQuery();
            
            if(rs.next()) {
            	throw new DuplicateStudentModuleRegistrationException();
            }

            st = con.prepareStatement("INSERT INTO student_enrollment"
            		+ "(student_id, module_code) "
            		+ "VALUES (?, ?)");

            st.setInt(1, stdDetails.getStdId());
            st.setString(2, courseCode);

            st.executeUpdate();

            con.close();
            
            return true;
        } catch (DuplicateStudentModuleRegistrationException | StudentModuleCountLimitException | SQLException e) {
        	if (e instanceof SQLException) {
        		new ErrorGUI("Error While Adding Data to Database");
        	} else {	
        		new ErrorGUI(e.getMessage());
        	}
        	
        	return false;
        }
	}


	/**
	 * This function is implemented from StudentInterface which is 
	 * used to get Details of existing Student in database
	 * 
	 * 
	 * @returns Details of the Student stored in the CourseDetails class
	 */
	@Override
	public StudentDetails getStudentDetails(int stdID) {
		StudentDetails studentDetails = new StudentDetails();
		
		Connection con = getConnection();

		try {
    		HashMap<String, String> course= new HashMap<>();
            
            PreparedStatement st = con.prepareStatement("SELECT student.*, studentcreds.email, studentcreds.password, student_year.semester "
            		+ "FROM student "
            		+ "INNER JOIN studentcreds ON student.id=studentcreds.id "
            		+ "INNER JOIN student_year ON studentcreds.id=student_year.studentId "
            		+ "WHERE student.id=?");
            st.setInt(1, stdID);
            
            ResultSet rs = st.executeQuery();
            
            rs.next();
            
            studentDetails.setStdId(stdID);
            studentDetails.setFirstName(rs.getString("firstName"));
            studentDetails.setLastName(rs.getString("lastName"));
            studentDetails.setDob(rs.getDate("dob"));
            studentDetails.setAddress(rs.getString("address"));
            studentDetails.setGender(rs.getString("gender"));
            studentDetails.setCourseEnrolled(rs.getString("courseEnrolled"));
            studentDetails.setEmail(rs.getString("email"));
            studentDetails.setPassword(rs.getString("password"));
            studentDetails.setCurrentSemester(rs.getInt("semester"));
            
            con.close();
            
            return studentDetails;
        } catch (SQLException e) {
        	e.printStackTrace();
        	new ErrorGUI("Error While Reading Data From the Database");
            
            return null;
        }
	}

	/**
	 * This function is implemented from StudentInterface which is 
	 * used to get generate ID for Student 
	 * 
	 * 
	 * @returns int ID for the Student to be assigned
	 */
	@Override
	public int generateStudentID() {
		try {
    		int id = 0;
            Connection con = getConnection();
            
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS id FROM student");
            
            rs.next();
            
            id = rs.getInt("id") + 1;
            
            con.close();
            
            return id;
        } catch (SQLException e) {
            return 0;
        }
	}


	/**
	 * This function is implemented from StudentInterface which is 
	 * used to get enrolled module of a student
	 * 
	 * 
	 * @returns ArrayList which contains the array of enrolled module
	 */
	@Override
	public ArrayList<String> enrolledModule(StudentDetails stdDetails) {
		StudentDetails studentDetails = new StudentDetails();
		
		Connection con = getConnection();

		try {
    		ArrayList<String> course= new ArrayList<String>();
            
            PreparedStatement st = con.prepareStatement("SELECT module_code FROM student_enrollment WHERE student_id=?");
            st.setInt(1, stdDetails.getStdId());
            
            ResultSet rs = st.executeQuery();
            
            while (rs.next()) {
            	course.add(rs.getString(1));
            }
            
            con.close();
            
            return course;
        } catch (SQLException e) {
        	e.printStackTrace();
        	new ErrorGUI("Error While Reading Data From the Database");
            
            return null;
        }
	}

}
