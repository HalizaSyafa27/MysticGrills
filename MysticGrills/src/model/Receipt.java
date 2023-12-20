package model;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.Connect;

public class Receipt {
	private int ReceiptID;
	private int ReceiptPaymentAmount;
	private Date ReceiptPaymentDate;
	private String ReceiptPaymentType;
	private int OrderID;
	private static List<OrderItem> items;
	
	public Receipt(int receiptID, int receiptPaymentAmount, Date receiptPaymentDate,
			String receiptPaymentType, int orderID, List<OrderItem> items) {
		super();
	    this.ReceiptID = receiptID;
	    this.ReceiptPaymentAmount = receiptPaymentAmount;
	    this.ReceiptPaymentDate = receiptPaymentDate;
	    this.ReceiptPaymentType = receiptPaymentType;
	    this.OrderID = orderID;
	}


	public int getReceiptPaymentAmount() {
		return ReceiptPaymentAmount;
	}

	public void setReceiptPaymentAmount(int receiptPaymentAmount) {
		ReceiptPaymentAmount = receiptPaymentAmount;
	}

	public Date getReceiptPaymentDate() {
		return ReceiptPaymentDate;
	}

	public void setReceiptPaymentDate(Date receiptPaymentDate) {
		ReceiptPaymentDate = receiptPaymentDate;
	}

	public String getReceiptPaymentType() {
		return ReceiptPaymentType;
	}

	public void setReceiptPaymentType(String receiptPaymentType) {
		ReceiptPaymentType = receiptPaymentType;
	}

	public int getReceiptID() {
		return ReceiptID;
	}

	public void setReceiptID(int receiptID) {
		ReceiptID = receiptID;
	}
	
    public List<OrderItem> getItems() {
        return items;
    }

    // Metode untuk menambahkan item ke daftar
    public static List<OrderItem> addItem(OrderItem item) {
        items.add(item);
		return items;
    }
}
