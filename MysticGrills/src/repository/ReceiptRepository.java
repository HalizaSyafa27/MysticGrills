package repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.Connect;
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
            ps.setString(1, receipt.getReceiptOrder());
            ps.setInt(2, receipt.getReceiptPaymentAmount());
            ps.setString(3, receipt.getReceiptPaymentDate());
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
            ps.setString(3, receipt.getReceiptPaymentDate());
            ps.setString(4, receipt.getReceiptPaymentType());
            connect.executeUpdate(ps);
        } catch (SQLException e) {
            e.printStackTrace();
         // TODO: handle exception
        }
	}
	
	public List<Receipt> getReceipt() {
		//ArrayList<Receipt> singleReceipt = new ArrayList<>();
		List<Receipt> singleReceipt = new ArrayList<>();
//		Connect con = Connect.getInstance();
		
		String query = "SELECT * FROM receipts WHERE ReceiptID = ?";
		ResultSet resultSet = connect.executeQuery(query);
//		con.rs = con.execQuery(query);
		
	    try {
	        while (resultSet.next()) {
	        	System.out.println("Print");
				int receiptID = resultSet.getInt("ReceiptID");
				String receiptOrder = resultSet.getString("ReceiptOrder");
				int receiptPaymentAmount = resultSet.getInt("ReceiptPaymentAmount");
				String receiptPaymentDate = resultSet.getString("ReceiptPaymentDate");
				String receiptPaymentType = resultSet.getString("ReceiptPaymentType");
	            
//	            Receipt receipt = new Receipt(receiptID, receiptOrder, receiptPaymentAmount, receiptPaymentDate, receiptPaymentType);
//	            singleReceipt.add(receipt);
				Receipt currentReceipt = new Receipt(receiptID, receiptOrder, receiptPaymentAmount, receiptPaymentDate, receiptPaymentType, null);
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
				String receiptOrder = resultSet.getString("ReceiptOrder");
				int receiptPaymentAmount = resultSet.getInt("ReceiptPaymentAmount");
				String receiptPaymentDate = resultSet.getString("ReceiptPaymentDate");
				String receiptPaymentType = resultSet.getString("ReceiptPaymentType");
				receipts.add(new Receipt(receiptID, receiptOrder, receiptPaymentAmount, receiptPaymentDate, receiptPaymentType, null));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return receipts;
		
	}
}
