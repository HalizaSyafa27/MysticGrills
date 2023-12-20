package repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import database.Connect;
import model.Menu;

public class MenuItemManagementRepository {
	private Connect connect; 
	
	public MenuItemManagementRepository() {
	        this.connect = Connect.getConnection();
	}
	
	//Untuk mengambil data menu dari database, yang ditampilkan id, nama, deskripsi, dan harga menu
	public List<Menu> getAllMenus() {
	    List<Menu> menus = new ArrayList<>();
	    String query = "SELECT * FROM menu";
	    
	    ResultSet resultSet = connect.executeQuery(query);
	    
	    try {
	        while (resultSet.next()) {
	            int id = resultSet.getInt("ItemID");
	            String name = resultSet.getString("MenuName");
	            String desc = resultSet.getString("MenuDescription");
	            int price = resultSet.getInt("MenuPrice");
	            
	            Menu menu = new Menu(id, name, desc, price);
	            menus.add(menu);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // TODO: handle exception
	    }
	    
	    return menus;
    }
	
	//Untuk menambahkan menu baru ke database
	public void addMenuItem(Menu menu) {
		String query = "INSERT INTO Menu (MenuName, MenuDescription, MenuPrice) VALUES (?, ?, ?)";
		try {
            PreparedStatement ps = connect.prepareStatement(query);
            ps.setString(1, menu.getName());
            ps.setString(2, menu.getDesc());
            ps.setDouble(3, menu.getPrice());
            connect.executeUpdate(ps);
        } catch (SQLException e) {
            e.printStackTrace();
            // TODO: handle exception
        }
    }
	
	//Untuk memperbarui data menu ke dalam database
	public void updateMenu(Menu menu) {
	    String query = "UPDATE menu SET MenuName = ?, MenuDescription = ?, MenuPrice = ? WHERE ItemID = ?";
	    try {
	        PreparedStatement ps = connect.prepareStatement(query);
	        ps.setString(1, menu.getName());
	        ps.setString(2, menu.getDesc());
	        ps.setInt(3, menu.getPrice());
	        ps.setInt(4, menu.getId());
	        connect.executeUpdate(ps);
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // TODO: handle exception
	    }
	}
	
	//Untuk menghapus menu dari database
	public void deleteMenu(int id) {
        String query = "DELETE FROM menu WHERE ItemID = ?";
        try {
            PreparedStatement ps = connect.prepareStatement(query);
            ps.setInt(1, id);
            connect.executeUpdate(ps);
        } catch (SQLException e) {
            e.printStackTrace();
         // TODO: handle exception
        }
    }
}
