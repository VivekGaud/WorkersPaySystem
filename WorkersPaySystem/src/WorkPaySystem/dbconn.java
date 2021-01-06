package WorkPaySystem;
import java.sql.*;
public class dbconn {
	Connection con;
	private Statement stmt;
	
	public Statement getStmt() {
		return stmt;
	}

	public dbconn() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3307/workersdb","root","");
			this.stmt = con.createStatement();
	}catch(Exception e){
		System.out.println(e);
	}
	}

}
