package model;

import java.util.ArrayList;
import java.util.List;

public class Receipt {
	private int ReceiptID;
	private String ReceiptOrder;
	private int ReceiptPaymentAmount;
	private String ReceiptPaymentDate;
	private String ReceiptPaymentType;
	private List<ReceiptItem> items;
	
	public Receipt(int receiptID, String receiptOrder, int receiptPaymentAmount, String receiptPaymentDate,
			String receiptPaymentType, List<ReceiptItem> items) {
		super();
	    this.ReceiptID = receiptID;
	    this.ReceiptOrder = receiptOrder;
	    this.ReceiptPaymentAmount = receiptPaymentAmount;
	    this.ReceiptPaymentDate = receiptPaymentDate;
	    this.ReceiptPaymentType = receiptPaymentType;
	    this.items = new ArrayList<>(items); // Inisialisasi dengan nilai parameter
	}

	public String getReceiptOrder() {
		return ReceiptOrder;
	}

	public void setReceiptOrder(String receiptOrder) {
		ReceiptOrder = receiptOrder;
	}

	public int getReceiptPaymentAmount() {
		return ReceiptPaymentAmount;
	}

	public void setReceiptPaymentAmount(int receiptPaymentAmount) {
		ReceiptPaymentAmount = receiptPaymentAmount;
	}

	public String getReceiptPaymentDate() {
		return ReceiptPaymentDate;
	}

	public void setReceiptPaymentDate(String receiptPaymentDate) {
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
	
    public List<ReceiptItem> getItems() {
        return items;
    }

    // Metode untuk menambahkan item ke daftar
    public void addItem(ReceiptItem item) {
        items.add(item);
    }
}
