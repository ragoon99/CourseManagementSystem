package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.table.DefaultTableModel;

import backend.Details.TutorDetails;
import exception.ErrorGUI;

public class TutorTableModels extends DBConnector {

	public DefaultTableModel getCourseDefaultTableModel() {
		int columnLength;
    	
		DefaultTableModel courseTableModel = new DefaultTableModel() {
    		@Override
    		public boolean isCellEditable(int row, int column) {
    		       return false;
    		}
    	};

    	ArrayList<Object> tableHeader = new ArrayList<>(
    			Arrays.asList("Course Code", "Course Name", "Total Modules", "Total Semester", "Course Length (Months)", "Availibility"));
    	
    	courseTableModel.setColumnIdentifiers(tableHeader.toArray());
    	
		try {
			Connection con = getConnection();
			ResultSet st = con.createStatement().executeQuery("SELECT * FROM courses");
			
			ResultSetMetaData rsmd = st.getMetaData();
			
			columnLength = rsmd.getColumnCount();
			String columnNames[] = new String[columnLength];
			
			for (int i = 0; i < columnLength; i++) {
				columnNames[i] =  rsmd.getColumnName(i+1);
			}
			
			while(st.next()) {
				ArrayList<Object> data = new ArrayList<>(); 
				for (String string : columnNames) {
					data.add(st.getObject(string));
				}
				
				courseTableModel.addRow(data.toArray());
			}
			
			return courseTableModel;
		} catch (Exception e) {
			if (e instanceof NullPointerException) {
				new ErrorGUI("Cannot Connect To the Server...", e);
			}
			return new DefaultTableModel();
		}
	}

	public DefaultTableModel getStudentDefaultTableModel(TutorDetails t) {
		int columnLength;
    	
		DefaultTableModel studentTableModel = new DefaultTableModel() {
    		@Override
    		public boolean isCellEditable(int row, int column) {
    		       return false;
    		}
    	};

    	ArrayList<Object> tableHeader = new ArrayList<>(
    			Arrays.asList("First Name", "Last Name", "Gender", "Email"));
    	
    	studentTableModel.setColumnIdentifiers(tableHeader.toArray());
    	
		try {
			Connection con = getConnection();
			ResultSet st = con.createStatement().executeQuery("SELECT student.firstName, student.lastName, student.gender, studentcreds.email "
					+ "FROM student "
					+ "INNER JOIN studentcreds ON student.id=studentcreds.id "
					+ "INNER JOIN student_enrollment ON studentcreds.id=student_enrollment.student_id "
					+ "WHERE "
					+ "student_enrollment.module_code='"+t.getModule1code()+"'");
			
			ResultSetMetaData rsmd = st.getMetaData();
			
			columnLength = rsmd.getColumnCount();
			String columnNames[] = new String[columnLength];
			
			for (int i = 0; i < columnLength; i++) {
				columnNames[i] =  rsmd.getColumnName(i+1);
			}
			
			while(st.next()) {
				ArrayList<Object> data = new ArrayList<>(); 
				for (String string : columnNames) {
					data.add(st.getObject(string));
				}
				
				studentTableModel.addRow(data.toArray());
			}
			
			return studentTableModel;
		} catch (NullPointerException | SQLException e) {
			if (e instanceof NullPointerException) {
				new ErrorGUI("Cannot Connect To the Server...", e);
			}
			e.printStackTrace();
			return new DefaultTableModel();
		}
	}

	public DefaultTableModel getReportDefaultTableModel(TutorDetails t) {
		int columnLength;
    	
		DefaultTableModel studentTableModel = new DefaultTableModel() {
    		@Override
    		public boolean isCellEditable(int row, int column) {
    		       return false;
    		}
    	};

    	ArrayList<Object> tableHeader = new ArrayList<>(
    			Arrays.asList("Student ID", "First Name", "Last Name", "Module Code", "Final Grade"));
    	
    	studentTableModel.setColumnIdentifiers(tableHeader.toArray());
    	
		try {
			Connection con = getConnection();
			ResultSet st = con.createStatement().executeQuery("SELECT student.id, student.firstName, student.lastName, student_enrollment.module_code, student_enrollment.final_grade "
					+ "FROM student "
					+ "INNER JOIN studentcreds ON student.id=studentcreds.id "
					+ "INNER JOIN student_enrollment ON studentcreds.id=student_enrollment.student_id "
					+ "WHERE "
					+ "student_enrollment.module_code='"+t.getModule1code()+"'");
			
			ResultSetMetaData rsmd = st.getMetaData();
			
			columnLength = rsmd.getColumnCount();
			String columnNames[] = new String[columnLength];
			
			for (int i = 0; i < columnLength; i++) {
				columnNames[i] =  rsmd.getColumnName(i+1);
			}
			
			while(st.next()) {
				ArrayList<Object> data = new ArrayList<>(); 
				for (String string : columnNames) {
					data.add(st.getObject(string));
				}
				
				studentTableModel.addRow(data.toArray());
			}
			
			return studentTableModel;
		} catch (NullPointerException | SQLException e) {
			if (e instanceof NullPointerException) {
				new ErrorGUI("Cannot Connect To the Server...", e);
			}
			e.printStackTrace();
			return new DefaultTableModel();
		}
	}

	public DefaultTableModel getModuleDefaultTableModel() {
		int columnLength;
    	
		DefaultTableModel moduleTableModel = new DefaultTableModel() {
    		@Override
    		public boolean isCellEditable(int row, int column) {
    		       return false;
    		}
    	};

    	ArrayList<Object> tableHeader = new ArrayList<>(
    			Arrays.asList("Module Code", "Module Name", "Module Type", "Semester", "Course Code", "Course Name"));
    	
    	moduleTableModel.setColumnIdentifiers(tableHeader.toArray());
    	
		try {
			Connection con = getConnection();
			ResultSet st = con.createStatement().executeQuery("SELECT modules.*, courses.courseName "
					+ "FROM modules INNER JOIN courses ON modules.courseField=courses.courseCode");
			
			ResultSetMetaData rsmd = st.getMetaData();
			
			columnLength = rsmd.getColumnCount();
			String columnNames[] = new String[columnLength];
			
			for (int i = 0; i < columnLength; i++) {
				columnNames[i] =  rsmd.getColumnName(i+1);
			}
			
			while(st.next()) {
				ArrayList<Object> data = new ArrayList<>(); 
				for (String string : columnNames) {
					data.add(st.getObject(string));
				}
				
				moduleTableModel.addRow(data.toArray());
			}
			
			return moduleTableModel;
		} catch (Exception e) {
			if (e instanceof NullPointerException) {
				new ErrorGUI("Cannot Connect To the Server...", e);
			}
			return new DefaultTableModel();
		}
	}

	public DefaultTableModel getEnrolleDefaultTableModel() {
		int columnLength;
    	
		DefaultTableModel enrolledCourseTableModel = new DefaultTableModel() {
    		@Override
    		public boolean isCellEditable(int row, int column) {
    		       return false;
    		}
    	};

    	ArrayList<Object> tableHeader = new ArrayList<>(
    			Arrays.asList("Enrollment ID", "Module Code", "Course Code", "Course Name"));
    	
    	enrolledCourseTableModel.setColumnIdentifiers(tableHeader.toArray());
    	
		try {
			Connection con = getConnection();
			ResultSet st = con.createStatement().executeQuery("SELECT tutor_enrollment.enrollment_id, tutor_enrollment.module1_code, "
					+ "tutor_enrollment.module2_code, tutor_enrollment.module3_code, tutor_enrollment.module4_code, "
					+ "courses.courseCode, courses.courseName "
					+ "FROM tutor_enrollment "
					+ "INNER JOIN modules ON "
					+ "tutor_enrollment.module1_code=modules.moduleCode AND tutor_enrollment.module2_code=modules.moduleCode AND "
					+ "tutor_enrollment.module3_code=modules.moduleCode AND tutor_enrollment.module4_code=modules.moduleCode "
					+ "INNER JOIN courses ON modules.courseField=courses.courseCode");
			
			ResultSetMetaData rsmd = st.getMetaData();
			
			columnLength = rsmd.getColumnCount();
			String columnNames[] = new String[columnLength];
			
			for (int i = 0; i < columnLength; i++) {
				columnNames[i] =  rsmd.getColumnName(i+1);
			}
			
			while(st.next()) {
				ArrayList<Object> data = new ArrayList<>(); 
				for (String string : columnNames) {
					data.add(st.getObject(string));
				}
				
				enrolledCourseTableModel.addRow(data.toArray());
			}
			
			return enrolledCourseTableModel;
		} catch (Exception e) {
			if (e instanceof NullPointerException) {
				new ErrorGUI("Cannot Connect To the Server...", e);
			}
			e.printStackTrace();
			return new DefaultTableModel();
		}
	}
	
}
