package view;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CashierMenuBar extends MenuBar{
	
    public void showCashierMenuBar() {
        VBox page = new VBox(10);
        page.getChildren().addAll(this); // Menambahkan CashierMenuBar ke VBox

        Scene scene = new Scene(page, 300, 300);
        Stage newStage = new Stage();
        newStage.setScene(scene);
        newStage.show();
    }
    
    public CashierMenuBar(Stage stage) {
        // Menu "View Receipts"
        Menu viewReceiptsMenu = new Menu("View Receipts");
        MenuItem viewReceiptsItem = new MenuItem("View Receipts");
        viewReceiptsMenu.getItems().add(viewReceiptsItem);

        // Menu "View Order Details"
        Menu viewOrderMenu = new Menu("View Order");
        MenuItem viewOrderItem = new MenuItem("View Order");
        viewOrderMenu.getItems().add(viewOrderItem);

        // Menu "Process Order Payment"
        Menu processOrderPaymentMenu = new Menu("Process Order Payment");
        MenuItem processOrderPaymentItem = new MenuItem("Process Order Payment");
        processOrderPaymentMenu.getItems().add(processOrderPaymentItem);

        // Menu "Logout"
        Menu logoutMenu = new Menu("Logout");
        MenuItem logoutItem = new MenuItem("Logout");
        logoutMenu.getItems().add(logoutItem);

        // Menambahkan semua menu ke menu bar
        this.getMenus().addAll(viewReceiptsMenu, viewOrderMenu, processOrderPaymentMenu, logoutMenu);

        // Menangani aksi ketika menu dipilih
        viewReceiptsItem.setOnAction(e -> {
            // Tambahkan logika untuk menangani aksi "View Receipts" di sini
            openViewReceiptsPage(stage);
        });

        viewOrderItem.setOnAction(e -> {
            // Tambahkan logika untuk menangani aksi "View Order Details" di sini
            openViewOrderPage(stage);
        });

        processOrderPaymentItem.setOnAction(e -> {
            // Tambahkan logika untuk menangani aksi "Process Order Payment" di sini
        	openProcessPayment(stage);
        });

        logoutItem.setOnAction(e -> {
            // Tambahkan logika untuk menangani aksi "Logout" di sini
        	handleLogout(stage);
            System.out.println("Logout");
        });
        
    }

	private void openViewReceiptsPage(Stage stage) {
		// TODO Auto-generated method stub
		ReceiptView receiptView = new ReceiptView(stage);
		receiptView.showReceiptView();
	}
	
	private void openViewOrderPage(Stage stage) {
		OrderView orderView = new OrderView(stage);
		orderView.showReceiptOrderView();
	}
	
    //Open ProcessPaymentView
    private void openProcessPayment(Stage stage) {
    	ProcessPaymentView processPaymentView = new ProcessPaymentView(stage);
    	processPaymentView.showProcessPaymentView();
    }
    
    private void handleLogout(Stage stage) {
    	//Tutup AdminMenuBar
    	Stage currentStage = (Stage) this.getScene().getWindow(); 
        currentStage.close();
    	
    	// Setelah logout, buat objek UnauthorizedMenuBar yang baru
        UnauthorizedMenuBar unauthorizedMenuBar = new UnauthorizedMenuBar(stage);

        // Menampilkan UnauthorizedMenuBar yang baru
        Scene scene = new Scene(new VBox(unauthorizedMenuBar), 300, 300);
        stage.setScene(scene);
        stage.show();
    }
}
