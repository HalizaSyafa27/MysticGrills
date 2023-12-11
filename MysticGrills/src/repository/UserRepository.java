package repository;

import database.Connect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {

    private Connect connect; 

    public UserRepository() {
        this.connect = new Connect();
    }

    public boolean createUser(String username, String email, String password) {
    	String query = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = connect.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, password);
            connect.executeUpdate(ps);
            return true; // Registrasi berhasil
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Registrasi gagal
        }
    }
    
    public ResultSet getUserData(String email, String password) {
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
        try {
            PreparedStatement ps = connect.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);
            return ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    
    public boolean isUserExists(String email) {
        String query = "SELECT * FROM users WHERE email = ?";
        try {
            PreparedStatement ps = connect.prepareStatement(query);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean isUserValid(String email, String password) {
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
        try {
            PreparedStatement ps = connect.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}
