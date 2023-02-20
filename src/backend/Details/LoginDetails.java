package backend.Details;

import database.DBConnector;

/*
 * This class is used to store the Credentials for logging in
 * 
 * This methods contains the getter and setter for the
 * Credentials Information
 * 
 */
public class LoginDetails {
	private final String studentTable = "studentcreds";
	private final String tutorTable = "tutorcreds";
	private final String adminTable = "admin";
	
	private String tableName;
	
	private boolean state;
	
	private int uid;
	private String password;
	
	public boolean isState() {
		return state;
	}
	
	public void setState(boolean state) {
		this.state = state;
	}
	
	public int getUid() {
		return uid;
	}
	
	public void setUid(int uid) {
		this.uid = uid;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getStudentTable() {
		return studentTable;
	}
	
	public String getTutorTable() {
		return tutorTable;
	}
	
	public String getAdminTable() {
		return adminTable;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
}
