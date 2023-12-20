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
			String receiptPaymentType, int orderID) {
		super();
	    this.ReceiptID = receiptID;
	    this.ReceiptPaymentAmount = receiptPaymentAmount;
	    this.ReceiptPaymentDate = receiptPaymentDate;
	    this.ReceiptPaymentType = receiptPaymentType;
	    this.OrderID = orderID;
	}
	

	//Method mengambil receipt details
	public static ArrayList<OrderItem> getOrderItems(String id) {
		ArrayList<OrderItem> orderItems = new ArrayList<>();
		
		Connect con = Connect.getInstance();
		
		String query = String.format("SELECT * FROM `orderitems` WHERE `OrderID` = \'%s\'", id);
		
		con.rs = con.execQuery(query);
		
		try {
			System.out.println("Print");
			String itemName= con.rs.getString("ItemName");
			int price = con.rs.getInt("Price");
			int quantity = con.rs.getInt("Quantity");
			orderItems.add(new OrderItem(itemName, price, quantity));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return orderItems;
	}
	
	//Get All Receipts
	public static ArrayList<Receipt> getAllReceipts(){
		
		ArrayList<Receipt> receipts = new ArrayList<>();
		Connect con = Connect.getInstance();
		
		String query = "SELECT * FROM receipts";
		

		con.rs = con.execQuery(query);
		
		try {
			while(con.rs.next()) {
				System.out.println("getallreceipt");
				int receiptID = con.rs.getInt("ReceiptID");
				int receiptPaymentAmount = con.rs.getInt("ReceiptPaymentAmount");
				Date receiptPaymentDate = con.rs.getDate("ReceiptPaymentDate");
				String receiptPaymentType = con.rs.getString("ReceiptPaymentType");
				int orderID = con.rs.getInt("OrderID");
				receipts.add(new Receipt(receiptID, receiptPaymentAmount, receiptPaymentDate, receiptPaymentType, orderID));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return receipts;
		
	}
	
	//get receipt by receiptID
	public static ArrayList<Receipt> getReceipt(String id) {
		ArrayList<Receipt> singleReceipt = new ArrayList<>();
		
		Connect con = Connect.getInstance();
		
		String query = String.format("SELECT * FROM `receipts` WHERE `ReceiptID` = \'%s\'", id);
		

		con.rs = con.execQuery(query);
		
				try {
					System.out.println("Print");
					int receiptID = con.rs.getInt("ReceiptID");
					int receiptPaymentAmount = con.rs.getInt("ReceiptPaymentAmount");
					Date receiptPaymentDate = con.rs.getDate("ReceiptPaymentDate");
					String receiptPaymentType = con.rs.getString("ReceiptPaymentType");
					int orderID = con.rs.getInt("OrderID");
					singleReceipt.add(new Receipt(receiptID, receiptPaymentAmount, receiptPaymentDate, receiptPaymentType, orderID));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		return singleReceipt;
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
