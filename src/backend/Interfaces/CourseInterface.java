package backend.Interfaces;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import backend.Details.CourseDetails;

public interface CourseInterface {
	
	public boolean createCourse(CourseDetails course);
	public boolean updateCourse(CourseDetails course, String newID);
	public boolean setCourseAvailibiliy(String courseCode);
	public boolean removeCourse(String courseCode);
	public LinkedHashMap<String, String> courseList();
	public HashMap<String, String> availableCourses();
	public List<Integer> getTotalSemesters(String moduleCode);
	public CourseDetails getCourseDetail(String courseCode);
	
}
