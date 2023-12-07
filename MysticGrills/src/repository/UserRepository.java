package repository;

import database.Connect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {

    private Connect connect; 

    public UserRepository() {
        this.connect = Connect.getConnection();
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
    
    public ResultSet getUserData(String username) {
        String query = "SELECT * FROM users WHERE username = ?";
        try {
            PreparedStatement ps = connect.prepareStatement(query);
            ps.setString(1, username);
            return connect.executeQuery(ps.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
