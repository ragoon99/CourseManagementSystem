package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

public class Read extends dbDetails {
    public Object[] getCourses() {
    	try {
    		ArrayList<String> course= new ArrayList<>();
            Connection con = getConnection();
            
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT courseCode FROM courses");
            
            while(rs.next()) {
            	course.add(rs.getString(1));
            }
            
            con.close();
            
            return course.toArray();
        } catch (SQLException e) {
        	e.printStackTrace();
            System.out.println("Error While Reading Table");
            
            return null;
        }
    }
    
    public int getID(String tableName) {
    	try {
    		int id = 0;
            Connection con = getConnection();
            
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id FROM " + tableName + " ORDER BY id DESC LIMIT 1");
            
            rs.next();
            
            id = rs.getInt(1);
            
            System.out.println(id);
            
            con.close();
            
            return id;
        } catch (SQLException e) {
            System.out.println("Error While Reading Table");
            
            return 0;
        }
    }
    
    public DefaultTableModel getTableModel(String table) {
    	Connection con = getConnection();
    	
    	DefaultTableModel tableModel = new DefaultTableModel() {
    		@Override
    		public boolean isCellEditable(int row, int column) {
    		       //all cells false
    		       return false;
    		}
    	};
    	
    	int columnLength;
    	
		try {
			ResultSet st = con.createStatement().executeQuery("SELECT * FROM " + table);
			ResultSetMetaData rsmd = st.getMetaData();
			
			columnLength = rsmd.getColumnCount();
			String columnNames[] = new String[columnLength];
			
			for (int i = 0; i < columnLength; i++) {
				columnNames[i] =  rsmd.getColumnName(i+1);
			}
			
	    	tableModel.setColumnIdentifiers(columnNames);
			
			while(st.next()) {
				ArrayList<Object> data = new ArrayList<>(); 
				for (String string : columnNames) {
					data.add(st.getObject(string));
				}
				
				tableModel.addRow(data.toArray());
			}
			
			return tableModel;
		} catch (SQLException e) {
			System.out.println("Error While Reading Table");
			return null;
		}
    }
}
