import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class ComputeDists {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/geolocation";
	
	static final String USER = "geolocation";
	static final String PASS = "geolocation";
	   
	public static void findNearCities(double[] location) {

		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			for (int i = 1; i < 100000; i++) {
				String q = "SELECT * FROM locid WHERE locid = ? ";
				stmt = conn.prepareStatement(q);
				stmt.setInt(1, i);
				ResultSet rs = stmt.executeQuery(q);
				
				String args[]= new String[4];
				args[0] = location[0] + "";
				args[1] = location[1] + "";
				args[2] = rs.getString("latitude");
				args[3] = rs.getString("longitude");
				
				double dists[] = new double[2];
				dists = GreatCircle.dists(args);
				
				System.out.println("Horizontal Distance: " + dists[0]);
				System.out.println("Vertical Distance: " + dists[1]);
			}
			
//			String sql = "SELECT * FROM locid WHERE (latitude > ? AND latitude < ?) AND "
//					+ " (longitude > ? AND longitude < ?)";
//			
//			stmt = conn.prepareStatement(sql);
			
			/*
			 * location1, location2
			 * for all cities
 					calculate distance b/w
 					d1, d2
			 */
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static double[] locationOfCity(String city) {
		Connection conn = null;
		PreparedStatement stmt = null;

		double[] location = new double[2];
		
		try{
			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			String sql = "SELECT * FROM locid WHERE city LIKE '?%'";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, city);
			ResultSet rs = stmt.executeQuery(sql);
			
			location[0] = rs.getDouble("latitude");
			location[1] = rs.getDouble("longitude");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return location;
	}
	
	public static void main(String[] args) {
		System.out.println("Enter name of City to search: ");
		Scanner scan = new Scanner(System.in);
		
		String city = scan.next();
		
		findNearCities(locationOfCity(city));
	}
}
