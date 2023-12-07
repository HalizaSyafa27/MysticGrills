package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class Connect {

 private final String USERNAME = "root";
 private final String PASSWORD = "";
 private final String DATABASE = "mysticgrills";
 private final String HOST = "localhost:3306";
 private final String CONECTION = String.format("jdbc:mysql://%s/%s", HOST, DATABASE);

 private static Connection con;
 private Statement st;
 private static Connect connect;
 private ResultSet rs;

 //private karena akan diterapkan konsep Singleton
 private Connect() {
  try {
   Class.forName("com.mysql.cj.jdbc.Driver"); //nyari Driver buat connect ke db
   con = DriverManager.getConnection(CONECTION, USERNAME, PASSWORD); //buat establish connection
   st = con.createStatement();
  } catch (Exception e) {
   e.printStackTrace();
   System.out.println("Failed to connect the database, the system is terminated!");
   System.exit(0);
  }
 }

 public static synchronized Connect getConnection() {
  return connect = (connect == null) ? new Connect() : connect;
 }

 //SELECT
 public ResultSet executeQuery(String query) {
   try {
    st = con.createStatement();
    rs = st.executeQuery(query);
   } catch (SQLException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
   }
  return rs;
 }


 public PreparedStatement prepareStatement(String query) {
  PreparedStatement ps = null;
  try {
   ps = con.prepareStatement(query);
  } catch (Exception e) {
   e.printStackTrace();
  }
  return ps;
 }
 
 public void executeUpdate(PreparedStatement ps) {
     try {
         ps.executeUpdate();
     } catch (SQLException e) {
         e.printStackTrace();
     }
 }
}