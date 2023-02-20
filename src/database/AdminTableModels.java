package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.table.DefaultTableModel;

import exception.ErrorGUI;

/*
 * This class contains the methods of getting the TableModel for the JTable
 * 
 */
public class AdminTableModels extends DBConnector {
	
	/**
	 * This is used to get the Table Model of Course for Admin View
	 * 
	 * @returns DefaultTableModel which contains the TableModel
	 * 
	 */
	public DefaultTableModel getCourseDefaultTableModel() {
		int columnLength;
    	
		DefaultTableModel courseTableModel = new DefaultTableModel() {
    		@Override
    		public boolean isCellEditable(int row, int column) {
    		       return false;
    		}
    	};
    	
    	// Setting the Table Header
    	ArrayList<Object> tableHeader = new ArrayList<>(
    			Arrays.asList("Course Code", "Course Name", "Total Modules", "Total Semester", "Course Length (Months)", "Availibility"));
    	
    	courseTableModel.setColumnIdentifiers(tableHeader.toArray());
    	
		try {
			Connection con = getConnection();
			ResultSet st = con.createStatement().executeQuery("SELECT * FROM courses");
			
			ResultSetMetaData rsmd = st.getMetaData();
			
			// counting the column 
			columnLength = rsmd.getColumnCount();
			String columnNames[] = new String[columnLength];
			
			for (int i = 0; i < columnLength; i++) {
				columnNames[i] =  rsmd.getColumnName(i+1);
			}
			
			// While ResultSet contains value append the data to the Table Model
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
			} else {
				new ErrorGUI("Error While Fetching Data from the Database");
			}
			// return null DefaultTableModel if error Occurs
			return new DefaultTableModel();
		}
	}
	
	/**
	 * This is used to get the Table Model of Student for Admin View
	 * 
	 * @returns DefaultTableModel which contains the TableModel
	 * 	
	 */
	public DefaultTableModel getStudentDefaultTableModel() {
		int columnLength;
    	
		DefaultTableModel studentTableModel = new DefaultTableModel() {
    		@Override
    		public boolean isCellEditable(int row, int column) {
    		       return false;
    		}
    	};
    	
    	// Setting the Table Header
    	ArrayList<Object> tableHeader = new ArrayList<>(
    			Arrays.asList("ID", "First Name", "Last Name", "DOB", "Address", "Gender", "Course Enrolled", "Date Enrolled", "Email"));
    	
    	studentTableModel.setColumnIdentifiers(tableHeader.toArray());
    	
		try {
			Connection con = getConnection();
			ResultSet st = con.createStatement().executeQuery("SELECT student.*, studentcreds.email "
					+ "FROM student "
					+ "INNER JOIN studentcreds ON student.id=studentcreds.id");
			
			ResultSetMetaData rsmd = st.getMetaData();
			
			columnLength = rsmd.getColumnCount();
			String columnNames[] = new String[columnLength];
			
			for (int i = 0; i < columnLength; i++) {
				columnNames[i] =  rsmd.getColumnName(i+1);
			}
			
			// While ResultSet contains value append the data to the Table Model
			while(st.next()) {
				ArrayList<Object> data = new ArrayList<>(); 
				for (String string : columnNames) {
					data.add(st.getObject(string));
				}
				
				studentTableModel.addRow(data.toArray());
			}
			
			return studentTableModel;
		} catch (Exception e) {
			if (e instanceof NullPointerException) {
				new ErrorGUI("Cannot Connect To the Server...", e);
			} else {
				new ErrorGUI("Error While Fetching Data from the Database");
			}
			return new DefaultTableModel();
		}
	}

	/**
	 * This is used to get the Table Model of Tutor for Admin View
	 * 
	 * @returns DefaultTableModel which contains the TableModel
	 * 	
	 */
	public DefaultTableModel getTutorDefaultTableModel() {
		int columnLength;
    	
		DefaultTableModel tutorTableModel = new DefaultTableModel() {
    		@Override
    		public boolean isCellEditable(int row, int column) {
    		       return false;
    		}
    	};
    	
    	// Setting the Table Header
    	ArrayList<Object> tableHeader = new ArrayList<>(
    			Arrays.asList("ID", "First Name", "Last Name", "DOB", "Address", "Gender", "Date Joined", "Email"));
    	
    	tutorTableModel.setColumnIdentifiers(tableHeader.toArray());
    	
		try {
			Connection con = getConnection();
			ResultSet st = con.createStatement().executeQuery("SELECT tutor.*, tutorcreds.email "
					+ "FROM tutor "
					+ "INNER JOIN tutorcreds ON tutor.id=tutorcreds.id ");
			
			ResultSetMetaData rsmd = st.getMetaData();
			
			columnLength = rsmd.getColumnCount();
			String columnNames[] = new String[columnLength];
			
			for (int i = 0; i < columnLength; i++) {
				columnNames[i] =  rsmd.getColumnName(i+1);
			}
			// While ResultSet contains value append the data to the Table Model
			while(st.next()) {
				ArrayList<Object> data = new ArrayList<>(); 
				for (String string : columnNames) {
					data.add(st.getObject(string));
				}
				
				tutorTableModel.addRow(data.toArray());
			}
			
			return tutorTableModel;
		} catch (Exception e) {
			if (e instanceof NullPointerException) {
				new ErrorGUI("Cannot Connect To the Server...", e);
			} else {
				new ErrorGUI("Error While Fetching Data from the Database");
			}
			// return null DefaultTableModel if error Occurs
			return new DefaultTableModel();
		}
	}
	
	/**
	 * This is used to get the Table Model of Report for Admin View
	 * 
	 * @returns DefaultTableModel which contains the TableModel
	 * 	
	 */
	public DefaultTableModel getReportDefaultTableModel() {
		int columnLength;
    	
		DefaultTableModel reportTableModel = new DefaultTableModel() {
    		@Override
    		public boolean isCellEditable(int row, int column) {
    		       return false;
    		}
    	};
    	// Setting the Table Header
    	ArrayList<Object> tableHeader = new ArrayList<>(
    			Arrays.asList("ID", "First Name", "Last Name", "Gender", "Module", "Final Grade"));
    	
    	reportTableModel.setColumnIdentifiers(tableHeader.toArray());
    	
		try {
			Connection con = getConnection();
			ResultSet st = con.createStatement().executeQuery("SELECT student.id, student.firstName, student.lastName, student.gender, "
					+ "student_enrollment.module_code, student_enrollment.final_grade "
					+ "FROM student "
					+ "INNER JOIN student_enrollment ON student.id=student_enrollment.student_id");
			
			ResultSetMetaData rsmd = st.getMetaData();
			
			columnLength = rsmd.getColumnCount();
			String columnNames[] = new String[columnLength];
			
			for (int i = 0; i < columnLength; i++) {
				columnNames[i] =  rsmd.getColumnName(i+1);
			}
			// While ResultSet contains value append the data to the Table Model
			while(st.next()) {
				ArrayList<Object> data = new ArrayList<>(); 
				for (String string : columnNames) {
					data.add(st.getObject(string));
				}
				
				reportTableModel.addRow(data.toArray());
			}
			
			return reportTableModel;
		} catch (Exception e) {
			if (e instanceof NullPointerException) {
				new ErrorGUI("Cannot Connect To the Server...", e);
			} else {
				new ErrorGUI("Error While Fetching Data from the Database");
			}
			// return null DefaultTableModel if error Occurs
			return new DefaultTableModel();
		}
	}

	/**
	 * This is used to get the Table Model of Module for Admin View
	 * 
	 * @returns DefaultTableModel which contains the TableModel
	 * 	
	 */
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
			} else {
				new ErrorGUI("Error While Fetching Data from the Database");
			}
			// return null DefaultTableModel if error Occurs
			return new DefaultTableModel();
		}
	}
	
	/**
	 * This is used to get the Table Model of User Activity for Admin View
	 * 
	 * @returns DefaultTableModel which contains the TableModel
	 * 	
	 */
	public DefaultTableModel getUserActivityLog() {
		int columnLength;
		
		Connection con = getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM login_activity");
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			
			DefaultTableModel activityDefaultTableModel = new DefaultTableModel() {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			
			ArrayList<Object> tableHeader = new ArrayList<>(
	    			Arrays.asList("Activity ID", "User ID", "User Type", "Login Timestamp"));
	    	
			activityDefaultTableModel.setColumnIdentifiers(tableHeader.toArray());
			
			columnLength = rsmd.getColumnCount();
			String columnNames[] = new String[columnLength];
			
			for (int i = 0; i < columnLength; i++) {
				columnNames[i] =  rsmd.getColumnName(i+1);
			}
			
			while(rs.next()) {
				ArrayList<Object> data = new ArrayList<>(); 
				for (String string : columnNames) {
					data.add(rs.getObject(string));
				}
				
				activityDefaultTableModel.addRow(data.toArray());
			}
			
			return activityDefaultTableModel;
			
			
		} catch (Exception e) {
			if (e instanceof NullPointerException) {
				new ErrorGUI("Cannot Connect To the Server...", e);
			} else {
				new ErrorGUI("Error While Fetching Data from the Database");
			}
			// return null DefaultTableModel if error Occurs
			return new DefaultTableModel();
		}
	}

	/**
	 * This is used to get the Table Model of Registered Tutor for Admin View
	 * 
	 * @returns DefaultTableModel which contains the TableModel
	 * 	
	 */
	public DefaultTableModel getRegisteredTutor() {
		int columnLength;
    	
		DefaultTableModel registeredTutorTableModel = new DefaultTableModel() {
    		@Override
    		public boolean isCellEditable(int row, int column) {
    		       return false;
    		}
    	};

    	ArrayList<Object> tableHeader = new ArrayList<>(
    			Arrays.asList("ID", "First Name", "Last Name", "Module Code", "Enrollment Date"));
    	
    	registeredTutorTableModel.setColumnIdentifiers(tableHeader.toArray());
    	
		try {
			Connection con = getConnection();
			ResultSet st = con.createStatement().executeQuery("SELECT tutor.id, tutor.firstName, tutor.lastName, tutor_enrollment.module_code "
					+ "FROM tutor "
					+ "INNER JOIN tutor_enrollment ON tutor.id=tutor_enrollment.tutor_id ");
			
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
				
				registeredTutorTableModel.addRow(data.toArray());
			}
			
			return registeredTutorTableModel;
		} catch (Exception e) {
			if (e instanceof NullPointerException) {
				new ErrorGUI("Cannot Connect To the Server...", e);
			} else {
				new ErrorGUI("Error While Fetching Data from the Database");
			}
			// return null DefaultTableModel if error Occurs
			return new DefaultTableModel();
		}
	}
}
