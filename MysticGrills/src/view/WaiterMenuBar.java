package view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WaiterMenuBar extends MenuBar {
	
	Menu viewPrepareOrder, logoutMenu;
	Label viewPrepareOrderLabel, logoutLabel;
	
    public void showWaiterMenuBar() {
        VBox page = new VBox(10);
        page.getChildren().addAll(this); // Menambahkan AdminMenuBar ke VBox

        Scene scene = new Scene(page, 300, 300);
        Stage newStage = new Stage();
        newStage.setScene(scene);
        newStage.show();
    }
    
    public WaiterMenuBar(Stage stage) {
    	
    	viewPrepareOrderLabel = new Label("View Prepare Order");
    	logoutLabel = new Label("Logout");
//        // Menu "Prepare - Serve"
    	
    	viewPrepareOrder = new Menu("", viewPrepareOrderLabel);
    	logoutMenu = new Menu("", logoutLabel);
    	
    	viewPrepareOrder.setGraphic(viewPrepareOrderLabel);
    	logoutMenu.setGraphic(logoutLabel);
    	
    	this.getMenus().addAll(viewPrepareOrder, logoutMenu);
    	
    	viewPrepareOrderLabel.setOnMouseClicked(e ->{
    		openviewPrepareOrderPage(stage);
//    		OpenviewPrepareOrderPage openviewPrepareOrderPage = new OpenviewPrepareOrderPage(stage);
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

	private void openviewPrepareOrderPage(Stage stage) {
		// TODO Auto-generated method stub
		//ada button update, remove untuk existing pending order atau add order item
		OrderPrepareView orderPrepareView = new OrderPrepareView(stage);
		orderPrepareView.showOrderPrepareView();
	}
}