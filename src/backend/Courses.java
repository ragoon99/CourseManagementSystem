package backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import backend.Details.CourseDetails;
import backend.Interfaces.CourseInterface;
import database.DBConnector;
import exception.ErrorGUI;

/**
 * This class file implements the CourseInterface where methods are predefined and
 * inherits the DBConnector for Database Conectivity
 * 
 * This Class contains all the methods required for CRUD functionality of Course
 */
public class Courses extends DBConnector implements CourseInterface {
	
	/**
	 * This function is implemented from CourseInterface Interface
	 * which is used to Create Course
	 * 
	 * @param Details of the Course Stored in the Course Detail Class
	 * 
	 * @returns true if course is successfully added to Database
	 * 			returns false if error occurs
	 */
	@Override
	public boolean createCourse(CourseDetails course) {
		Connection con = getConnection();
		
		try {PreparedStatement st = con.prepareStatement("INSERT INTO courses "
                            + "(`courseCode`, `courseName`, `totalModules`, `totalSemester`, `courseLength(months)`) "
                            + "Values (?, ?, ?, ?, ?)");
            st.setString(1, course.getCourseCode());
            st.setString(2, course.getCourseName());
            st.setInt(3, course.getTotalModule());
            st.setInt(4, course.getTotalSemester());
            st.setInt(5, course.getCourseLength());

            st.executeUpdate();

            con.close();
            
            return true;
        } catch (SQLException e) {
        	if (e instanceof SQLIntegrityConstraintViolationException) {
        		new ErrorGUI("Course is Already Regsitered in Database.", e);
            } else {
            	new ErrorGUI("Error While Adding Data To Database", e);
            }
        	
        	return false;
        }
	}
	
	/**
	 * This function is implemented from CourseInterface which is 
	 * used to Update Course
	 * 
	 * @param Details of the Course Stored in the Course Detail Class
	 * @param new id to be assigned as String
	 * 
	 * @returns true if course is successfully updated in Database
	 * 			returns false if error occurs
	 */
	@Override
	public boolean updateCourse(CourseDetails course, String newID) {
		Connection con = getConnection();
		
		try {
			PreparedStatement st = con.prepareStatement("UPDATE courses SET "
                            + "`courseCode`=?, `courseName`=?, `totalModules`=?, `totalSemester`=?, `courseLength(months)`=? "
                            + "WHERE courseCode=?");

            st.setString(1, newID);
            st.setString(2, course.getCourseName());
            st.setInt(3, course.getTotalModule());
            st.setInt(4, course.getTotalSemester());
            st.setInt(5, course.getCourseLength());
            st.setString(6, course.getCourseCode());

            st.executeUpdate();

            con.close();
            
            return true;
        } catch (SQLException e) {
        	if (e instanceof SQLIntegrityConstraintViolationException) {
        		new ErrorGUI("Module is Already Regsitered in Database.");
            } else {
            	new ErrorGUI(null, e);
            }
        	
        	return false;
        }
	}
	
	/**
	 * This function is implemented from CourseInterface which is 
	 * used to Remove Course
	 * 
	 * @param Code of the course to be removed
	 * 
	 * @returns true if course is successfully Deleted from Database
	 * 			returns false if error occurs
	 */
	@Override
	public boolean removeCourse(String courseCode) {
		Connection con = getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement("DELETE FROM courses WHERE courseCode=?");
            ps.setString(1, courseCode);

            ps.execute();

            con.close();
            
            return true;
        } catch (Exception e) {
            new ErrorGUI("Error Occured While Deleting Course", e);
        }
		return false;
	}
	
	/**
	 * This function is implemented from CourseInterface which is 
	 * used to Cancel or Re-Enable Course from database
	 * 
	 * @param ID of the Course to be cancel or re-enabled
	 * 
	 * @returns true if course availability is successfully set
	 * 			returns false if error occurs
	 */
	@Override
	public boolean setCourseAvailibiliy(String courseCode)  {
		Connection con = getConnection();

		try {
            PreparedStatement st = con.prepareStatement("SELECT availability FROM courses WHERE courseCode=?");
            st.setString(1, courseCode);

            ResultSet rSet = st.executeQuery();
            
            rSet.next();
            
            boolean isAvailable = (rSet.getString(1).equals("Available") ? true : false);
            
            if (isAvailable) {
            	st = con.prepareStatement("UPDATE courses SET `availability`= 'Not Available' WHERE courseCode=?");
            } else {
            	st = con.prepareStatement("UPDATE courses SET `availability`= 'Available' WHERE courseCode=?");            	
            }
            
            st.setString(1, courseCode);
            st.execute();

            con.close();
            
            return true;
        } catch (SQLException e) {
        	new ErrorGUI("Error Occured While Setting Availibility", e);
        	
        	return false;
        }
	}
	
	/**
	 * This function is implemented from CourseInterface which is 
	 * used to get all existing Course from database
	 * 
	 * 
	 * @returns HashMap where course name is set as the key and
	 * 			course code is set as the value 
	 */
	@Override
	public LinkedHashMap<String, String> courseList() {
		Connection con = getConnection();

		try {
			LinkedHashMap<String, String> course= new LinkedHashMap<>();
            
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT courseCode, courseName FROM courses");
            
            while(rs.next()) {
            	course.put(
            			rs.getString("courseName"), 
            			rs.getString("courseCode")
            		);
            }
            
            con.close();
            
            return course;
        } catch (SQLException e) {
        	e.printStackTrace();
        	new ErrorGUI("Error While Reading Data From the Database");
            
            return null;
        }
	}
	
	/**
	 * This function is implemented from CourseInterface which is 
	 * used to get Course where availability is set to Available from database
	 * 
	 * 
	 * @returns HashMap where course name is set as the key and
	 * 			course code is set as the value 
	 */
	@Override
	public HashMap<String, String> availableCourses() {
		Connection con = getConnection();

		try {
    		HashMap<String, String> course= new HashMap<>();
            
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT courseCode, courseName FROM courses WHERE availability='Available'");
            
            while(rs.next()) {
            	course.put(
            			rs.getString("courseCode"), 
            			rs.getString("courseName")
            		);
            }
            
            con.close();
            
            return course;
        } catch (SQLException e) {
        	new ErrorGUI("Error While Reading Data From the Database");
            
            return null;
        }
	}
	
	/**
	 * This function is implemented from CourseInterface which is 
	 * used to get Details of existing Course of database
	 * 
	 * 
	 * @returns Details of the course stored in the CourseDetails class
	 */
	public CourseDetails getCourseDetail(String courseCode) throws NullPointerException {
		CourseDetails courseDetails = new CourseDetails();
		Connection con = getConnection();

		try {
    		HashMap<String, String> course= new HashMap<>();
            
            PreparedStatement st = con.prepareStatement("SELECT * FROM courses WHERE courseCode =?");
            st.setString(1, courseCode);
            
            ResultSet rs = st.executeQuery();
            
            rs.next();
            
            courseDetails.setCourseCode(rs.getString("courseCode"));
            courseDetails.setCourseName(rs.getString("courseName"));
            courseDetails.setTotalModule(rs.getInt("totalModules"));
            courseDetails.setTotalSemester(rs.getInt("totalSemester"));
            courseDetails.setCourseLength(rs.getInt("courseLength(Months)"));
            courseDetails.setAvailibility(rs.getString("availability").equals("Available"));
            
            con.close();
            
            return courseDetails;
        } catch (SQLException e) {
        	new ErrorGUI("Error While Reading Data From the Database", e);
            
            return null;
        }
		
	}

	/**
	 * This function is implemented from CourseInterface which is 
	 * used to get total semester of a existing Course of database
	 * 
	 * 
	 * @returns List Integer as generic type where series of number is
	 * 			stored
	 */
	@Override
	public List<Integer> getTotalSemesters(String courseCode) {
		Connection con = getConnection();
		
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement("SELECT totalSemester FROM courses WHERE courseName=?");
			stmt.setString(1, courseCode);
	        
	        ResultSet rs = stmt.executeQuery();
	        
	        rs.next();
	        	      
	        List<Integer> semesters = IntStream.range(1, rs.getInt("totalSemester")+1).boxed().collect(Collectors.toList());;
			
			return semesters;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
