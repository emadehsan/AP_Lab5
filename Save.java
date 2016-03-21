import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class Save {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/geolocation";
	
	static final String USER = "geolocation";
	static final String PASS = "geolocation";
	   
	public static void mains(String[] args) {

		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			String sql = "INSERT into locid (locid, country, region, city, postalCode, latitude, longitude, metroCode, areaCode) "
								+ " VALUES  (?,     ?,       ?,      ?,    ?, 		    ?, 		 ?, 	    ?, 		    ?)";
											// 1	2			3	  4 	5			6			7		8			9
			
			stmt = conn.prepareStatement(sql);
			
			String fileName = "D:\\VI Semester\\Advanced Program\\Lab5\\GeoLiteCity-Location.csv";
			List<String> list = new ArrayList<>();

			// Open the file
			FileInputStream fstream = new FileInputStream(fileName);
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

			String strLine;

			//Read File Line By Line
			while ((strLine = br.readLine()) != null)   {
//			  System.out.println (strLine);
			  String[] arr = strLine.split(",");
//			  System.out.println("Len: " + arr.length);
			  
			  if (Integer.parseInt(arr[0]) > 14945) {
			  	if (arr.length > 0 &&arr[0].trim().length() != 0)
				  	stmt.setInt(1, Integer.parseInt(arr[0]));
			  	else
			  		stmt.setInt(1, 0);
			  	if (arr.length > 1 &&arr[1].trim().length() != 0)
			  		stmt.setString(2, arr[1]);
			  	else
			  		stmt.setString(2, "");
			  	if (arr.length > 2 &&arr[2].trim().length() != 0)
			  		stmt.setString(3, arr[2]);
			  	else
			  		stmt.setString(3, "");
			  	if (arr.length > 3 &&arr[3].trim().length() != 0)
				  	stmt.setString(4, arr[3]);
			  	else
			  		stmt.setString(4, "");
			  	if (arr.length > 4 &&arr[4].trim().length() != 0)
				  	stmt.setString(5, arr[4]);
			  	else
			  		stmt.setString(5, "");
			  	if (arr.length > 5 &&arr[5].trim().length() != 0)
				  	stmt.setDouble(6, Double.parseDouble(arr[5]));
			  	else
			  		stmt.setDouble(6, 0.0);
			  	if (arr.length > 6 &&arr[6].trim().length() != 0)
				  	stmt.setDouble(7, Double.parseDouble(arr[6]));
			  	else
			  		stmt.setDouble(7, 0.0);
			  	if (arr.length > 7 &&arr[7].trim().length() != 0)
				  	stmt.setString(8, arr[7]);
			  	else
			  		stmt.setString(8, "");
			  	if (arr.length > 8 &&arr[8].trim().length() != 0)
			  		stmt.setString(9, arr[8]);
			  	else
			  		stmt.setString(9, "");
			  	
			  	boolean b = stmt.execute();
			  	stmt.clearParameters();

			  }
			  
			}

			//Close the input stream
			br.close();
	

//			int rows = stmt.executeUpdate();
//			System.out.println("Rows impacted : " + rows );

	      
//			ResultSet rs = stmt.executeQuery(sql);

	   } catch (Exception e) {
		   	e.printStackTrace();
	   }
	   
		System.out.println("DOne");
	}	    
}
// locId	country	region	city	postalCode	latitude	longitude	metroCode	areaCode

//DEGREES(ACOS(COS(RADIANS(lat1)) * COS(RADIANS(lat2)) *
//        COS(RADIANS(long1) - RADIANS(long2)) +
//        SIN(RADIANS(lat1)) * SIN(RADIANS(lat2))))

