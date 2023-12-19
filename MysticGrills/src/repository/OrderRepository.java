package repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.Connect;
import model.Order;

public class OrderRepository {
	private Connect connect;
	
	public OrderRepository(){
		this.connect = new Connect();
	}
	
	public void addOrder(Order order) {
		String query = String.format("INSERT INTO orders VALUES (%d, \"%s\", \"%s\", \"%s\", %d)"); 
        try {
            PreparedStatement ps = connect.prepareStatement(query);
            ps.setInt(1, order.getOrderID());
            ps.setString(2, order.getOrderUser());
            ps.setDate(3, order.getOrderDate());
            ps.setInt(4, order.getOrderTotal());
            connect.executeUpdate(ps);
        } catch (SQLException e) {
            e.printStackTrace();
         // TODO: handle exception
        }
	}
	
	public List<Order> getAllOrders(){
		ArrayList<Order> orders = new ArrayList<>();
		
		String query = "SELECT * FROM orders";
		
		ResultSet resultSet = connect.executeQuery(query);
		
		try {
			while(resultSet.next()) {
				System.out.println("Masuk");
				int orderID = resultSet.getInt("OrderID");
				String orderUser = resultSet.getString("OrderUser");
				String orderStatus = resultSet.getString("OrderStatus");
				Date orderDate = resultSet.getDate("OrderDate");
				int orderTotal = resultSet.getInt("OrderTotal");
				
				orders.add(new Order(orderID, orderUser, orderStatus, orderDate, orderTotal));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return orders;
	}
	
	public Order getOrderById(int orderId) {
	    Order order = null;
	    String query = "SELECT * FROM orders WHERE OrderID = ?";

	    try {
	        PreparedStatement ps = connect.prepareStatement(query);
	        ps.setInt(1, orderId);
	        ResultSet resultSet = ps.executeQuery();

	        if (resultSet.next()) {
	            int orderID = resultSet.getInt("OrderID");
	            String orderUser = resultSet.getString("OrderUser");
	            String orderStatus = resultSet.getString("OrderStatus");
	            Date orderDate = resultSet.getDate("OrderDate");
	            int orderTotal = resultSet.getInt("OrderTotal");

	            order = new Order(orderID, orderUser, orderStatus, orderDate, orderTotal);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        // TODO: handle exception
	    }

	    return order;
	}
	
	public void updateOrder(Order updatedOrder) {
	    if (updatedOrder != null) {
	        String query = "UPDATE orders SET OrderStatus = 'Paid' WHERE OrderID = ?";

	        try {
	            PreparedStatement ps = connect.prepareStatement(query);
	            ps.setInt(1, updatedOrder.getOrderID());
	            connect.executeUpdate(ps);

	        } catch (SQLException e) {
	            e.printStackTrace();
	            // TODO: handle exception
	        }
	    }
	}
}
