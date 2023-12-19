package model;

import java.sql.Date;

public class Order {
	private int OrderID;
//	private String OrderUser;
	private String OrderName; 
	private String OrderStatus;
	private Date OrderDate;
	private int OrderTotal;
	private String OrderDesc; 
	
	public Order(int orderID, String orderName, String orderStatus, Date orderDate, int orderTotal, String orderDesc) {
		OrderID = orderID;
//		OrderUser = orderUser;
		OrderName = orderName;
		OrderStatus = orderStatus;
		OrderDate = orderDate;
		OrderTotal = orderTotal;
		OrderDesc = orderDesc;
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
	
	
}
