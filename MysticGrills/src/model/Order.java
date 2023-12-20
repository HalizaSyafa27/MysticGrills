package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Order {
	private int OrderID;
	private String OrderName; 
	private String OrderStatus;
	private Date OrderDate;
	private int OrderTotal;
	private String OrderDesc; 
	private List<OrderItem> orderItems;
	
	public Order(int orderID, String orderName, String orderStatus, Date orderDate, int orderTotal, String orderDesc) {
		OrderID = orderID;
		OrderName = orderName;
		OrderStatus = orderStatus;
		OrderDate = orderDate;
		OrderTotal = orderTotal;
		OrderDesc = orderDesc;
		this.orderItems = new ArrayList<>();
	}

	public int getOrderID() {
		return OrderID;
	}


	public void setOrderID(int orderID) {
		OrderID = orderID;
	}

	public String getOrderStatus() {
		return OrderStatus;
	}


	public void setOrderStatus(String orderStatus) {
		OrderStatus = orderStatus;
	}


	public Date getOrderDate() {
		return OrderDate;
	}


	public void setOrderDate(Date orderDate) {
		OrderDate = orderDate;
	}


	public int getOrderTotal() {
		return OrderTotal;
	}


	public void setOrderTotal(int orderTotal) {
		OrderTotal = orderTotal;
	}

	public String getOrderName() {
		return OrderName;
	}

	public void setOrderName(String orderName) {
		OrderName = orderName;
	}

	public String getOrderDesc() {
		return OrderDesc;
	}

	public void setOrderDesc(String orderDesc) {
		OrderDesc = orderDesc;
	}
	
    // Metode untuk menambahkan OrderItem ke dalam pesanan
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
    }

    // Metode untuk mengecek apakah pesanan sudah berisi menu tertentu
    public boolean containsMenu(Menu menu) {
        for (OrderItem orderItem : orderItems) {
            if (orderItem.getItemName().equals(menu.getName())) {
                return true;
            }
        }
        return false;
    }

    // Metode untuk mendapatkan semua OrderItem dalam pesanan
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
	
}
