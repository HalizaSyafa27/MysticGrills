package view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChefMenuBar extends MenuBar {
	Menu viewPendingOrder, logoutMenu;
	Label viewPendingOrderLabel, logoutLabel;
    public void showChefMenuBar() {
        VBox page = new VBox(10);
        page.getChildren().addAll(this); // Menambahkan AdminMenuBar ke VBox

        Scene scene = new Scene(page, 300, 300);
        Stage newStage = new Stage();
        newStage.setScene(scene);
        newStage.show();
    }
    
    public ChefMenuBar(Stage stage) {
    	
    	viewPendingOrderLabel = new Label("View Pending Order");
    	logoutLabel = new Label("Logout");
//        // Menu "Prepare - Serve"
    	
    	viewPendingOrder = new Menu("", viewPendingOrderLabel);
    	logoutMenu = new Menu("", logoutLabel);
    	
    	viewPendingOrder.setGraphic(viewPendingOrderLabel);
    	logoutMenu.setGraphic(logoutLabel);
    	
    	this.getMenus().addAll(viewPendingOrder, logoutMenu);
    	
    	viewPendingOrderLabel.setOnMouseClicked(e ->{
    		openViewPendingOrderPage(stage);
//    		OpenViewPendingOrderPage openViewPendingOrderPage = new OpenViewPendingOrderPage(stage);
    	});
    	
    	logoutLabel.setOnMouseClicked(e -> {
    		handleLogout(stage);
    	});
    
    }

	private void handleLogout(Stage stage) {
		// TODO Auto-generated method stub
        Stage currentStage = (Stage) this.getScene().getWindow(); 
        currentStage.close();
    	
    	// Setelah logout, buat objek UnauthorizedMenuBar yang baru
        UnauthorizedMenuBar unauthorizedMenuBar = new UnauthorizedMenuBar(stage);

        // Menampilkan UnauthorizedMenuBar yang baru
        Scene scene = new Scene(new VBox(unauthorizedMenuBar), 300, 300);
        stage.setScene(scene);
        stage.show();
	}

	private void openViewPendingOrderPage(Stage stage) {
		// TODO Auto-generated method stub
		//ada button update, remove untuk existing pending order atau add order item
		OrderPendingView orderPendingView = new OrderPendingView(stage);
		orderPendingView.showOrderPendingView();
	}
}
