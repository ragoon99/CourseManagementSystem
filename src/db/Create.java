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
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + super.dbName, super.username, super.pswd);

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
        } catch (ClassNotFoundException | SQLException e) {
            if(e instanceof ClassNotFoundException) {
                    System.out.println("Error While Loading Driver");
            } else if (e instanceof SQLIntegrityConstraintViolationException) {
                    System.out.println("Module is Already Regsitered in Database.");
            }
        }
    }

    public void addStudent(int id, String firstName, String lastName, Date dob, String address, String gender, String moduleEnrolled, String email, String password) {	
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + super.dbName, super.username, super.pswd);

            PreparedStatement st = con.prepareStatement("INSERT INTO student "
                            + "(id, firstName, lastName, dob, address, gender, courseEnrolled) "
                            + "Values (?, ?, ?, ?, ?, ?, ?)");

            st.setInt(1, id);
            st.setString(2, firstName);
            st.setString(3, lastName);
            st.setDate(4, dob);
            st.setString(5, address);
            st.setString(6, gender);
            st.setString(7, moduleEnrolled);

            st.executeUpdate();
            
            PreparedStatement anotherSt = con.prepareStatement("INSERT INTO studentcreds (studentId, email, password) VALUES (?, ?, ?)");
            
            anotherSt.setInt(1, id);
            anotherSt.setString(2, email);
            anotherSt.setString(3, password);
            
            anotherSt.execute();

            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            if(e instanceof ClassNotFoundException) {
            	System.out.println("Error While Loading Driver");
            } else if (e instanceof SQLIntegrityConstraintViolationException) {
            	new ErrorGUI("Student is Already Regsitered.");
            }

            System.out.println(e);
        }
    }

}
