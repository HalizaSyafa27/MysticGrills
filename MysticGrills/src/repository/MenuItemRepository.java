package repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.Connect;

public class MenuItemRepository {
	private Connect connect; 
	
	public boolean addMenuItem(String name, String description, double price) {
        try {
            String query = "INSERT INTO Menu (name, description, price) VALUES (?, ?, ?)";
            PreparedStatement ps = connect.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setDouble(3, price);

            connect.executeUpdate(ps);

            return true; // Penambahan item berhasil
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Penambahan item gagal
        }
    }
}
