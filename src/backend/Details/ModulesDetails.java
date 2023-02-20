package backend.Details;

/*
 * This class is used to store the Information of Modules
 * 
 * This methods contains the getter and setter for the
 * Modules Information
 * 
 */

public class ModulesDetails {
	private String moduleCode;
	private String moduleName;
	private String moduleType;
	private int semester;
	private String courseField;
	private String courseFieldName;
	
	public String getModuleCode() {
		return moduleCode;
	}
	
	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}
	
	public String getModuleName() {
		return moduleName;
	}
	
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	
	public String getModuleType() {
		return moduleType;
	}
	
	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}
	
	public int getSemester() {
		return semester;
	}
	
	public void setSemester(int semester) {
		this.semester = semester;
	}
	
	public String getCourseField() {
		return courseField;
	}
	
	public void setCourseField(String courseField) {
		this.courseField = courseField;
	}

	public String getCourseFieldName() {
		return courseFieldName;
	}

	public void setCourseFieldName(String courseFieldName) {
		this.courseFieldName = courseFieldName;
	}
	
	
}
