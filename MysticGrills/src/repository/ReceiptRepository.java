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
	private ReceiptView receiptView;
	private ReceiptDetailsView rdv;
		
	//receipt detail
	public void loadReceiptDetail(String id) {
		ArrayList<OrderItem> receiptDetail = ReceiptRepository.getOrderItems(id);
		rdv.getTable().getItems().setAll(receiptDetail);

	}
	
	//get searched
	public Receipt loadSearchedReceipt(String id) {
		return (Receipt) receiptRepo.getReceipt(id);
	}
	
	
	//get all
	public List<Receipt> getreceiptData() {
		return receiptRepo.getAllReceipts();
	}
	
	public ReceiptController(ReceiptView receiptView) {
		this.receiptView = receiptView;
	}
}

