package model;

import java.sql.Date;

public class Order {
	private int OrderID;
	private String OrderUser;
	private String OrderStatus;
	private Date OrderDate;
	private int OrderTotal;
	
	
	public Order(int orderID, String orderUser, String orderStatus, Date orderDate, int orderTotal) {
		OrderID = orderID;
		OrderUser = orderUser;
		OrderStatus = orderStatus;
		OrderDate = orderDate;
		OrderTotal = orderTotal;
	}

	public int getOrderID() {
		return OrderID;
	}


	public void setOrderID(int orderID) {
		OrderID = orderID;
	}


	public String getOrderUser() {
		return OrderUser;
	}


	public void setOrderUser(String orderUser) {
		OrderUser = orderUser;
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
	
	
	
}
