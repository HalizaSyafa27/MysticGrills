package repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.Connect;
import model.OrderItem;
import model.Receipt;

public class ReceiptRepository {
	private Connect connect;
	
	public ReceiptRepository() {
		this.connect = Connect.getConnection();
	}
	
	//create new receipt after process payment
	public void addReceipt(Receipt receipt) {
//		String query = String.format("INSERT INTO receipts VALUES (\"%s\", %d, \"%s\", \"%s\")", this.ReceiptOrder, this.ReceiptPaymentAmount, this.ReceiptPaymentDate, this.ReceiptPaymentType); 
//		Connect con = Connect.getInstance();
//		con.execUpdate(query);
		String query = "INSERT INTO receipts (ReceiptOrder, ReceiptPaymentAmount, ReceiptPaymentDate, ReceiptPaymentType) VALUES (?, ?, ?, ?)";
		try {
            PreparedStatement ps = connect.prepareStatement(query);
            ps.setInt(2, receipt.getReceiptPaymentAmount());
            ps.setDate(3, receipt.getReceiptPaymentDate());
            ps.setString(5, receipt.getReceiptPaymentType());
            connect.executeUpdate(ps);
        } catch (SQLException e) {
            e.printStackTrace();
            // TODO: handle exception
        }
	}
	
	public void updateReceipt(Receipt receipt) {
//		String query = String.format("UPDATE receipts SET `ReceiptID`=\'%s\', `ReceiptPaymentAmount`= %d, `ReceiptPaymentDate`=\'%s\', `ReceiptPaymentType`=\'%s\' WHERE `ReceiptID` = \'%s\' ", id, paymentAmount, paymentDate, paymentType, id);
//        Connect con = Connect.getInstance();
//        con.execUpdate(query);
        String query = "UPDATE receipts SET ReceiptID = ?, ReceiptPaymentAmount = ?, ReceiptPaymentDate = ?, ReceiptPaymentType = ? WHERE ReceiptID = ? ";
        try {
            PreparedStatement ps = connect.prepareStatement(query);
            ps.setInt(1, receipt.getReceiptID());
            ps.setInt(2, receipt.getReceiptPaymentAmount());
            ps.setDate(3, receipt.getReceiptPaymentDate());
            ps.setString(4, receipt.getReceiptPaymentType());
            connect.executeUpdate(ps);
        } catch (SQLException e) {
            e.printStackTrace();
         // TODO: handle exception
        }
	}
	
	
	//get receipt by id
	public List<Receipt> getReceipt(String id) {
//		ArrayList<Receipt> singleReceipt = new ArrayList<>();
		List<Receipt> singleReceipt = new ArrayList<>();
//		Connect con = Connect.getInstance();
		
		String query = "SELECT * FROM receipts WHERE ReceiptID = ?";
		ResultSet resultSet = connect.executeQuery(query);
//		con.rs = con.execQuery(query);
		
	    try {
	        while (resultSet.next()) {
	        	System.out.println("Print");
				int receiptID = resultSet.getInt("ReceiptID");
				int receiptPaymentAmount = resultSet.getInt("ReceiptPaymentAmount");
				Date receiptPaymentDate = resultSet.getDate("ReceiptPaymentDate");
				String receiptPaymentType = resultSet.getString("ReceiptPaymentType");
				int orderID = resultSet.getInt("OrderID");
	            
//	            Receipt receipt = new Receipt(receiptID, receiptOrder, receiptPaymentAmount, receiptPaymentDate, receiptPaymentType);
//	            singleReceipt.add(receipt);
				Receipt currentReceipt = new Receipt(receiptID, receiptPaymentAmount, receiptPaymentDate, receiptPaymentType, orderID, null);
		        singleReceipt.add(currentReceipt);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // TODO: handle exception
	    }		
		return singleReceipt;
	}
	
	//receipt delete by orderID
	public void deleteReceipt(int id) {
		String query = String.format("DELETE FROM `receipts` WHERE `OrderID` = \'%s\'", id);
//        Connect con = Connect.getInstance();
//        con.execUpdate(query);
        try {
            PreparedStatement ps = connect.prepareStatement(query);
            ps.setInt(1, id);
            connect.executeUpdate(ps);
        } catch (SQLException e) {
            e.printStackTrace();
         // TODO: handle exception
        }
	}
	
	//Get All Receipts
	public List<Receipt> getAllReceipts(){
		
		ArrayList<Receipt> receipts = new ArrayList<>();
//		Connect con = Connect.getInstance();
		
		String query = "SELECT * FROM receipts";
		
		ResultSet resultSet = connect.executeQuery(query);
		
		try {
			while(resultSet.next()) {
				System.out.println("Masuk");
				int receiptID = resultSet.getInt("ReceiptID");
				int receiptPaymentAmount = resultSet.getInt("ReceiptPaymentAmount");
				Date receiptPaymentDate = resultSet.getDate("ReceiptPaymentDate");
				String receiptPaymentType = resultSet.getString("ReceiptPaymentType");
				int orderID = resultSet.getInt("OrderID");
				receipts.add(new Receipt(receiptID, receiptPaymentAmount, receiptPaymentDate, receiptPaymentType, orderID, null));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return receipts;
		
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
}
