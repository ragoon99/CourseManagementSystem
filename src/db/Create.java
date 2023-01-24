package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import exception.ErrorGUI;

public class Create extends dbDetails{
	void createCourse(String courseCode, String cName, int totalSemester, int semLength) {
        try {
            Connection con = getConnection();

            PreparedStatement st = con.prepareStatement("INSERT INTO courses "
                            + "(courseCode, courseName, totalSemester, semesterDuration) "
                            + "Values (?, ?, ?, ?)");

            st.setString(1, courseCode);
            st.setString(2, cName);
            st.setInt(3, totalSemester);
            st.setInt(4, semLength);

            st.executeUpdate();

            System.out.println("Course Added.");

            con.close();
        } catch (SQLException e) {
        	if (e instanceof SQLIntegrityConstraintViolationException) {
                    System.out.println("Module is Already Regsitered in Database.");
            }
        }
    }

    public void addStudent(String id, String firstName, String lastName, Date dob, String address, String gender, String moduleEnrolled, String email, String password) {	
        try {
            Connection con = getConnection();

            PreparedStatement st = con.prepareStatement("INSERT INTO student "
                            + "(studentId, firstName, lastName, dob, address, gender, courseEnrolled) "
                            + "Values (?, ?, ?, ?, ?, ?, ?)");

            st.setString(1, id);
            st.setString(2, firstName);
            st.setString(3, lastName);
            st.setDate(4, dob);
            st.setString(5, address);
            st.setString(6, gender);
            st.setString(7, moduleEnrolled);

            st.executeUpdate();
            
            PreparedStatement anotherSt = con.prepareStatement("INSERT INTO studentcreds (studentId, email, password) VALUES (?, ?, ?)");
            
            anotherSt.setString(1, id);
            anotherSt.setString(2, email);
            anotherSt.setString(3, password);
            
            anotherSt.execute();

            con.close();
        } catch (SQLException e) {
            if (e instanceof SQLIntegrityConstraintViolationException) {
            	new ErrorGUI("Student is Already Regsitered.");
            }

            System.out.println(e);
        }
    }
    
    public void addTutor(String id, String firstName, String lastName, Date dob, String address, String gender, String email, String password) {	
        try {
            Connection con = getConnection();

            PreparedStatement st = con.prepareStatement("INSERT INTO tutor "
                            + "(id, fName, lName, dob, address, gender) "
                            + "Values (?, ?, ?, ?, ?, ?)");

            st.setString(1, id);
            st.setString(2, firstName);
            st.setString(3, lastName);
            st.setDate(4, dob);
            st.setString(5, address);
            st.setString(6, gender);

            st.executeUpdate();
            
            PreparedStatement anotherSt = con.prepareStatement("INSERT INTO tutorcreds (tutorId, email, password) VALUES (?, ?, ?)");
            
            anotherSt.setString(1, id);
            anotherSt.setString(2, email);
            anotherSt.setString(3, password);
            
            anotherSt.execute();

            con.close();
        } catch (SQLException e) {
            if (e instanceof SQLIntegrityConstraintViolationException) {
            	new ErrorGUI("Tutor is Already Regsitered.");
            }
            e.printStackTrace();
        }
    }

}
