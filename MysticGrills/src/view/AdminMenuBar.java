package view;

import java.util.List;

import controller.UserController;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import repository.UserRepository;

public class AdminMenuBar extends MenuBar{
	private List<MenuItem> menuItems;
	
    Menu viewMenuItems, viewAllUsers, LogoutMenu;
    Label viewMenuItemsLabel, viewAllUsersLabel, logoutLabel;
    
    public void showAdminMenuBar() {
        VBox page = new VBox(10);
        page.getChildren().addAll(this); // Menambahkan AdminMenuBar ke VBox

        Scene scene = new Scene(page, 300, 300);
        Stage newStage = new Stage();
        newStage.setScene(scene);
        newStage.show();
    }
    
    public AdminMenuBar(Stage stage) {
    	viewMenuItemsLabel = new Label ("View Menu Items");
    	viewAllUsersLabel = new Label ("View All Users");
    	logoutLabel = new Label("Logout");
    	
        viewMenuItems = new Menu("", viewMenuItemsLabel);
        viewAllUsers = new Menu("", viewAllUsersLabel);
        LogoutMenu = new Menu("", logoutLabel);
    	
        // Menambahkan item-menu ke Menu
        viewMenuItems.getItems().add(new MenuItem("Item 1"));
        viewMenuItems.getItems().add(new MenuItem("Item 2"));

        // Menambahkan item-menu ke LogoutMenu
        LogoutMenu.getItems().add(new MenuItem("Logout Item"));

        // Menambahkan label ke setiap Menu
        viewMenuItems.setGraphic(viewMenuItemsLabel);
        viewAllUsers.setGraphic(viewAllUsersLabel);
        LogoutMenu.setGraphic(logoutLabel);

    	this.getMenus().addAll(viewMenuItems, viewAllUsers, LogoutMenu);
    	
    	viewMenuItemsLabel.setOnMouseClicked(e -> {
    		openViewMenuItemsPage(stage);
    	});
    	
    	viewAllUsersLabel.setOnMouseClicked(e -> {
    		openViewAllUsersPage(stage);
    	});
    	
    	logoutLabel.setOnMouseClicked(e -> {
    		handleLogout(stage);
    	});
    }

    private void openViewMenuItemsPage(Stage stage) {
        // logic selanjutnya...
    }

    private void openViewAllUsersPage(Stage stage) {
        // logic selanjutnya...
    }

    private void handleLogout(Stage stage) {
        // logic selanjutnya...
        // Setelah logout, Anda dapat membuat objek UnauthorizedMenuBar baru
        UnauthorizedMenuBar unauthorizedMenuBar = new UnauthorizedMenuBar(stage);

        // Menampilkan UnauthorizedMenuBar yang baru
        Scene scene = new Scene(new VBox(unauthorizedMenuBar), 300, 300);
        stage.setScene(scene);
    }
}
