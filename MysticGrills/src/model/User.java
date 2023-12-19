package model;

import database.Connect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private String username;
    private String email;
    private String password;
    private String role;
    private int id;

    public User(String username, String email, String password, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(int id, String username, String role) {
		this.id = id;
		this.username = username;
		this.role = role;
	}

	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    public int getId() {
    	return id;
    }

    // Untuk menyimpan user ke database
    public boolean saveToDatabase(Connect connect) {
        String query = "INSERT INTO users (username, email, password, role) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = connect.prepareStatement(query);
            ps.setString(1, this.username);
            ps.setString(2, this.email);
            ps.setString(3, this.password);
            ps.setString(4, this.role);
            connect.executeUpdate(ps);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Untuk mengambil user dari database berdasarkan email
    public static User loadFromDatabase(String email, Connect connect) {
        String query = "SELECT * FROM users WHERE email = ?";
        try {
            PreparedStatement ps = connect.prepareStatement(query);
            ps.setString(1, email);
            ResultSet rs = connect.executeQuery(ps.toString());

            if (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("role");

                return new User(username, email, password, role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static String getRoleByEmail(String email, Connect connect) {
        String query = "SELECT role FROM users WHERE email = ?";
        try {
            PreparedStatement ps = connect.prepareStatement(query);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("role");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
