package backend.Details;

import java.sql.Date;
import java.util.ArrayList;

/*
 * This class is used to store the Information of Tutor
 * 
 * This methods contains the getter and setter for the
 * Tutor Information
 * 
 */

public class TutorDetails {
	private int tutId;
	private String firstName;
	private String lastName;
	private Date dob;
	private String address;
	private String gender;
	private String email;
	private String password;
	private String module1code;
	private String module2code;
	private String module3code;
	private String module4code;
	private ArrayList<String> modules;
	
	public int getTutId() {
		return tutId;
	}
	
	public void setTutId(int tutId) {
		this.tutId = tutId;
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
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getModule1code() {
		return module1code;
	}

	public void setModule1code(String module1code) {
		this.module1code = module1code;
	}

	public String getModule2code() {
		return module2code;
	}

	public void setModule2code(String module2code) {
		this.module2code = module2code;
	}

	public String getModule3code() {
		return module3code;
	}

	public void setModule3code(String module3code) {
		this.module3code = module3code;
	}

	public String getModule4code() {
		return module4code;
	}

	public void setModule4code(String module4code) {
		this.module4code = module4code;
	}

	public ArrayList<String> getModules() {
		return modules;
	}

	public void setModules(ArrayList<String> modules) {
		this.modules = modules;
	}
	
	
	
	
}
