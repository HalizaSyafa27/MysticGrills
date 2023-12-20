package controller;

import java.util.ArrayList;

import model.Receipt;
import view.ReceiptView;

public class ReceiptController {
	
	private ReceiptView receiptView;
	
	public void loadSearchedReceipt(String id) {
		ArrayList<Receipt> searchedReceipt = Receipt.getReceipt(id);
		receiptView.getTable().getItems().setAll(searchedReceipt);
	}
	
	public void loadReceiptData() {
		ArrayList<Receipt> receipts = Receipt.getAllReceipts();
		receiptView.getTable().getItems().setAll(receipts);
	}
	
	public ReceiptController(ReceiptView receiptView) {
		this.receiptView = receiptView;
		
		loadReceiptData();
	}
}
