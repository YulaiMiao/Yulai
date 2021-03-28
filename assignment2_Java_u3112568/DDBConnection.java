//Author: 				Yulai Miao
import java.sql.DriverManager;

import java.sql.SQLException;



public class DDBConnection {
	
	public static java.sql.Connection connection(String filename) throws SQLException
	{	
		try
		{
		String driver = "net.ucanaccess.jdbc.UcanaccessDriver";
		Class.forName(driver);
		//Statement st = con.createStatement();
		//ResultSet rs = st.executeQuery("SELECT * FROM tblDates");
		// while (rs.next()) 
		 //	{
	     //    System.out.println(rs.getObject(1));
	         
	     //   }
		}
		catch(ClassNotFoundException e) {
            System.err.println("JDBC-ODBC Bridge Driver for databases not found!");
		}
		return DriverManager.getConnection("jdbc:ucanaccess://../Days_Alive_Calculator/" + filename );
	}
	

}
