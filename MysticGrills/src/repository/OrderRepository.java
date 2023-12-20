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
		String query = String.format("INSERT INTO orders VALUES (%d, \"%s\", \"%s\", %d, %d, \"%s\")"); 
        try {
            PreparedStatement ps = connect.prepareStatement(query);
            ps.setInt(1, order.getOrderID());
            ps.setString(2, order.getOrderName());
            ps.setString(3, order.getOrderStatus());
            ps.setDate(4, order.getOrderDate());
            ps.setInt(5, order.getOrderTotal());
            ps.setString(6, order.getOrderDesc());
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
				String orderName = resultSet.getString("OrderName");
				String orderStatus = resultSet.getString("OrderStatus");
				Date orderDate = resultSet.getDate("OrderDate");
				int orderTotal = resultSet.getInt("OrderTotal");
				String orderDesc = resultSet.getString("OrderDesc");
				
				orders.add(new Order(orderID, orderName, orderStatus, orderDate, orderTotal, orderDesc));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return orders;
	}

	public List<Order> getAllPendingOrders(){
		ArrayList<Order> orders = new ArrayList<>();
		
		String query = "SELECT * FROM orders WHERE OrderStatus = 'Pending'";
		
		ResultSet resultSet = connect.executeQuery(query);
		
		try {
			while(resultSet.next()) {
				int orderID = resultSet.getInt("OrderID");
				String orderName = resultSet.getString("OrderName");
				String orderDesc = resultSet.getString("OrderDesc");
				String orderStatus = resultSet.getString("OrderStatus");
				
				orders.add(new Order(orderID, orderName, orderDesc, orderStatus));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return orders;
	}

	public List<Order> getAllPreparedOrders(){
		ArrayList<Order> orders = new ArrayList<>();
		
		String query = "SELECT * FROM orders WHERE OrderStatus = 'Prepared'";
		
		ResultSet resultSet = connect.executeQuery(query);
		
		try {
			while(resultSet.next()) {
				int orderID = resultSet.getInt("OrderID");
				String orderName = resultSet.getString("OrderName");
				String orderDesc = resultSet.getString("OrderDesc");
				String orderStatus = resultSet.getString("OrderStatus");
				
				orders.add(new Order(orderID, orderName, orderDesc, orderStatus));
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
	            String orderName = resultSet.getString("OrderName");
	            String orderStatus = resultSet.getString("OrderStatus");
	            Date orderDate = resultSet.getDate("OrderDate");
	            int orderTotal = resultSet.getInt("OrderTotal");
	            String orderDesc = resultSet.getString("OrderDesc");

	            order = new Order(orderID, orderName, orderStatus, orderDate, orderTotal, orderDesc);
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
	
    public void deleteOrder(Order order) {
        if (order != null) {
            String query = "DELETE FROM orders WHERE OrderID = ?";

            try {
                PreparedStatement ps = connect.prepareStatement(query);
                ps.setInt(1, order.getOrderID());
                connect.executeUpdate(ps);

            } catch (SQLException e) {
                e.printStackTrace();
                // TODO: handle exception
            }
        }
    }
}
