package controller;

import java.util.ArrayList;
import java.util.List;

import model.OrderItem;
import model.Receipt;
import repository.ReceiptRepository;
import view.ReceiptDetailsView;
import view.ReceiptView;

public class ReceiptController {
	
	private ReceiptRepository receiptRepo;
	private ReceiptDetailsView rdv;
	String id;
		
	//receipt detail
	public void loadReceiptDetail(String id) {
		ArrayList<OrderItem> receiptDetail = ReceiptRepository.getOrderItems(id);
		rdv.getTable().getItems().setAll(receiptDetail);

	}
	
	//get searched
	public Receipt loadSearchedReceipt() {
		
		return (Receipt) receiptRepo.getReceipt(id);
	}
	
	
	//get all
	public List<Receipt> getreceiptData() {
		return receiptRepo.getAllReceipts();
	}
	
	public ReceiptController(ReceiptView receiptView) {
		this.receiptRepo = new ReceiptRepository();
	}
}

