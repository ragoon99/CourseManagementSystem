package backend.Details;

import java.sql.Date;
import java.util.ArrayList;


/*
 * This class is used to store the Information of Students
 * 
 * This methods contains the getter and setter for the
 * Modules Information
 * 
 */

public class StudentDetails {
	private int stdId;
	private String firstName;
	private String lastName;
	private Date dob;
	private String address;
	private String gender;
	private String courseEnrolled;
	private int academicYear;
	private int currentSemester;
	private float finalGrade;
	private String email;
	private String password;
	private ArrayList<Object> modulesEnrolled = new ArrayList<>();
	
	public int getStdId() {
		return stdId;
	}
	
	public void setStdId(int stdId) {
		this.stdId = stdId;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public Date getDob() {
		return dob;
	}
	
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getCourseEnrolled() {
		return courseEnrolled;
	}
	
	public void setCourseEnrolled(String courseEnrolled) {
		this.courseEnrolled = courseEnrolled;
	}
	
	public int getAcademicYear() {
		return academicYear;
	}
	
	public void setAcademicYear(int academicYear) {
		this.academicYear = academicYear;
	}
	
	public int getCurrentSemester() {
		return currentSemester;
	}
	
	public void setCurrentSemester(int currentSemester) {
		this.currentSemester = currentSemester;
	}
	
	public float getFinalGrade() {
		return finalGrade;
	}
	
	public void setFinalGrade(float finalGrade) {
		this.finalGrade = finalGrade;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public ArrayList<Object> getModulesEnrolled() {
		return modulesEnrolled;
	}
	
	public void setModulesEnrolled(ArrayList<Object> modulesEnrolled) {
		this.modulesEnrolled = modulesEnrolled;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String passwordString) {
		this.password = passwordString;
	}
}
