package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbDetails {
    private String username = "root";
    private String dbName = "cms";
    private String pswd = "";
    private String url = "jdbc:mysql://localhost:3306/";
    private Connection con;
    
    public dbDetails() {
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url + dbName, username, pswd);

        } catch (ClassNotFoundException | SQLException e) {
            if(e instanceof ClassNotFoundException) {
                System.out.println("Error While Loading Driver");
            }
        }
	}
    
    public Connection getConnection() {
    	return con;
    }
    
    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getPswd() {
		return pswd;
	}

	public void setPswd(String pswd) {
		this.pswd = pswd;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}