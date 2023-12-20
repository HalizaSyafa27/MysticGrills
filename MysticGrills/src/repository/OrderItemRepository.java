package repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.Connect;
import model.Order;
import model.OrderItem;

public class OrderItemRepository {
	private Connect connect;
	
	public OrderItemRepository(){
		this.connect = new Connect();
	}
    // Contoh daftar order items, gantilah dengan implementasi sesuai kebutuhan Anda
    private List<OrderItem> orderItems = new ArrayList<>();

    // Metode untuk mendapatkan semua order items
    public List<OrderItem> getAllOrderItems() {
        ArrayList<OrderItem> orderItems = new ArrayList<>();

        String query = "SELECT * FROM order_items"; // Sesuaikan dengan nama tabel yang benar

        ResultSet resultSet = connect.executeQuery(query);

        try {
            while (resultSet.next()) {
                String itemName = resultSet.getString("ItemName");
                double price = resultSet.getDouble("Price");
                int quantity = resultSet.getInt("Quantity");

                orderItems.add(new OrderItem(itemName, price, quantity));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderItems;
    }
    

    // Metode untuk menambahkan order item baru
    public void addOrderItem(OrderItem orderItem) {
        String query = "INSERT INTO order_items (ItemName, Price, Quantity) VALUES (?, ?, ?)";

        try {
            PreparedStatement ps = connect.prepareStatement(query);
            ps.setString(1, orderItem.getItemName());
            ps.setDouble(2, orderItem.getPrice());
            ps.setInt(3, orderItem.getQuantity());

            connect.executeUpdate(ps);
        } catch (SQLException e) {
            e.printStackTrace();
            // TODO: Handle exception
        }
    }


    // Metode lainnya sesuai kebutuhan aplikasi Anda
}

