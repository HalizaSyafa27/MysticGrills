package view;

import java.util.List;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CustomerMenuBar extends MenuBar {
	private List<MenuItem> menuItems;
	
	Menu MenuItems, OrderMenu, LogoutMenu;
	Label MenuItemsLabel, OrderLabel, LogoutLabel;
	
	public void showCustomerBar() {
        VBox page = new VBox(10);
        page.getChildren().addAll(this); // Menambahkan AdminMenuBar ke VBox

        Scene scene = new Scene(page, 300, 300);
        Stage newStage = new Stage();
        newStage.setScene(scene);
        newStage.show();
	}
	
	public CustomerMenuBar(Stage stage) {
		MenuItemsLabel = new Label("Menu Items");
		OrderLabel = new Label("Order");
		LogoutLabel = new Label("Logout");
		
		MenuItems = new Menu("", MenuItemsLabel);
		OrderMenu = new Menu("", OrderLabel);
		LogoutMenu = new Menu("", LogoutLabel);
		
 
        // Menambahkan semua menu ke menu bar
        this.getMenus().addAll(MenuItems, OrderMenu, LogoutMenu);

        // Menangani aksi ketika menu dipilih
        MenuItemsLabel.setOnMouseClicked(e -> {
            // Tambahkan logika untuk menangani aksi "View Menu Items" di sini
        	openMenuItemsPage(stage);
        });

        OrderLabel.setOnMouseClicked(e -> {
            // Tambahkan logika untuk menangani aksi "View Ordered Menu Items" di sini
        	openOrderPage(stage);
        });

        LogoutLabel.setOnMouseClicked(e -> {
            // Tambahkan logika untuk menangani aksi "Logout" di sini
        	handleLogout(stage);
        });
    }
	
	private void openMenuItemsPage(Stage stage) {
		// logic : 
		// 1. liat semua menu items
		// 2. pilih menu -> masukin quantity -> klik order
		// setelah user klik order arahin ke page order
	}
	
	private void openOrderPage(Stage stage) {
		// logic order selanjutnya...
	}
	
	private void handleLogout(Stage stage) {
		// login selanjutnya
        UnauthorizedMenuBar unauthorizedMenuBar = new UnauthorizedMenuBar(stage);

        // Menampilkan UnauthorizedMenuBar yang baru
        Scene scene = new Scene(new VBox(unauthorizedMenuBar), 300, 300);
        stage.setScene(scene);
	}

}
