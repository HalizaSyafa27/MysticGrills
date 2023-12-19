package repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.Connect;
import model.User;

public class UserManagementRepository {
	private Connect connect;
	
	public UserManagementRepository() {
		this.connect = Connect.getConnection();
	}
	
	//Untuk mengambil data user dari database, yang ditampilkan hanya id, username, dan role-nya
	public List<User> getAllUser() {
		List<User> users = new ArrayList<>();
		String query = "SELECT * FROM users";
		
		ResultSet resultSet = connect.executeQuery(query);
		
		try {
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("username");
				String role = resultSet.getString("role");
				
				User user = new User(id, name, role);
				users.add(user);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return users;
		}

	//Untuk menghapus user berdasarkan id dari database
	public void deleteUser(int id) {
		String query = "DELETE FROM users WHERE id = ?";
		try {
			PreparedStatement ps = connect.prepareStatement(query);
			ps.setInt(1, id);
			connect.executeUpdate(ps);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Untuk mengubah user role berdasarkan idnya dalam database
	public void updateUser(User user) {
		String query = "UPDATE users SET role = ? WHERE id = ?";
		try {
			PreparedStatement ps = connect.prepareStatement(query);
			ps.setString(1, user.getRole());
			ps.setInt(2, user.getId());
			connect.executeUpdate(ps);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
