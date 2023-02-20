package backend.Details;

/*
 * This class is used to store the Information of the 
 * Course
 * 
 * This methods contains the getter and setter for the
 * Couse Information
 * 
 */
public class CourseDetails {
	private String courseCode;
	private String courseName;
	private int totalModule;
	private int totalSemester;
	private int courseLength;
	private String availibility;
	
	public String getCourseCode() {
		return courseCode;
	}
	
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	
	public String getCourseName() {
		return courseName;
	}
	
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	public int getTotalModule() {
		return totalModule;
	}
	
	public void setTotalModule(int totalModule) {
		this.totalModule = totalModule;
	}
	
	public int getTotalSemester() {
		return totalSemester;
	}
	
	public void setTotalSemester(int totalSemester) {
		this.totalSemester = totalSemester;
	}
	
	public int getCourseLength() {
		return courseLength;
	}
	
	public void setCourseLength(int courseLength) {
		this.courseLength = courseLength;
	}
	
	public String getAvailibility() {
		return availibility;
	}
	
	public void setAvailibility(boolean isAvailable) {
		this.availibility = (isAvailable ? "Available" : "Not Available");
	}
	
}
