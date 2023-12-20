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
		// Isi program : 
		// 1. liat semua menu items
		// 2. pilih menu -> masukin quantity -> klik order
		// setelah user klik order arahin ke page order
		ListView<String> menuListView = new ListView<>();
		menuListView.getItems().addAll("Menu Items 1", "Menu Items 2", "Menu Items 3");

		Label MenuItemsLabel = new Label("Choose Menu Items: ");
		Label OrderLabel = new Label("Order");
		TextField quantityField = new TextField();
		Label quantityField = new Label("Enter Quantity: ");
		Label LogoutLabel = new Label("Logout");

		orderButton.setOnAction(e -> {
			String selectedMenuItem = menuListView.getSelectionModel().getSelectionItem();
			String quantity = quantityField.getText();

		if (selectedMenuItem != null && !quantity.isEmpty()) {
                	showAlert("Order Confirmation", "Ordered " + quantity + " of " + selectedMenuItem);
        	} else {
                	showAlert("Error", "Please choose a menu item and enter quantity.");
        	}
        	});

       	 	// Menunjukkan layout untuk Customer Menu Bar
         	VBox layout = new VBox(10);
        	layout.getChildren().addAll(menuListView, chooseLabel, quantityLabel, quantityField, orderButton);

        	// Membuat dan membentuk setting untuk scene
        	Scene scene = new Scene(layout, 300, 300);
        	stage.setScene(scene);
        	stage.setTitle("Menu Items Page");
        	stage.show();
	        } 	
	}
	
	private void openOrderPage(Stage stage) {
		// Isi program :
		// Untuk logic order setelah Open Menu Items Page
        	String selectedMenuItem = "Item 1";
        	int quantity = 2;

        	// Menunjukkan komponen dari UI saat membuka pesanan
        	Label summaryLabel = new Label("Order Summary:");
        	Label itemLabel = new Label("Selected Items: " + selectedMenuItems);
        	Label quantityLabel = new Label("Quantity: " + quantity);
        	Button confirmOrderButton = new Button("Confirm Order");

        	// Melakukan setting untuk konfirmasi dalam bentuk tombol klik pemesanan 
        	confirmOrderButton.setOnAction(e -> {
            	showAlert("Order Confirmed", "Thank you! Your order has been confirmed!");
        	});

        	// Menunjukkan bentuk layout dalam pesanan
        	VBox layout = new VBox(10);
        	layout.getChildren().addAll(summaryLabel, itemLabel, quantityLabel, confirmOrderButton);

        	// Menunjukkan setting pada scene dalam pesanan
        	Scene scene = new Scene(layout, 300, 200);
        	stage.setScene(scene);
        	stage.setTitle("Order Page");
        	stage.show();
    		}
	}
	
	private void handleLogout(Stage stage) {
		// Isi program:
		// Untuk melakukan login selanjutnya
        	UnauthorizedMenuBar unauthorizedMenuBar = new UnauthorizedMenuBar(stage);
		
        	// Melakukan redirecting pada halaman untuk login page
        	showLoginScreen(stage);
    		}

    	private void showLoginScreen(Stage stage) {
        	// Menunjukan seluruh komponen UI untuk melakukan login
        	Label titleLabel = new Label("Login");
        	Button loginButton = new Button("Login");

        	// Menunjukan untuk sistem login dengan adanya button click
        	loginButton.setOnAction(e -> {
            	showAlert("Login Successful", "Redirecting to customer view.");
            	stage.close();
       		});

        	// Menunjukan layout untuk halaman login
        	VBox layout = new VBox(10);
        	layout.getChildren().addAll(titleLabel, loginButton);

        	// Menunjukan scene yang telah melalui proses setting
        	Scene scene = new Scene(layout, 200, 150);
        	stage.setScene(scene);
        	stage.setTitle("Login Page");
        	stage.show();
    		}

        	// Menampilkan UnauthorizedMenuBar yang baru
        	Scene scene = new Scene(new VBox(unauthorizedMenuBar), 300, 300);
        	stage.setScene(scene);
		}

	private void showAlert(String title, String content) {
        	Alert alert = new Alert(Alert.AlertType.INFORMATION);
        	alert.setTitle(title);
        	alert.setHeaderText(null);
        	alert.setContentText(content);
        	alert.showAndWait();
    		}

}
