package backend.Interfaces;

import java.util.ArrayList;

import backend.Details.StudentDetails;

public interface StudentInterface {
	
	public boolean createStudent(StudentDetails s);
	public boolean updateStudent(StudentDetails s);
	public boolean removeStudent(int stdID);
	public boolean enrollModule(StudentDetails stdDetails, String CourseCode);
	public ArrayList<String> enrolledModule(StudentDetails stdDetails);
	public StudentDetails getStudentDetails(int stdID);
	public int generateStudentID();
	
	
}
