package view;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminMenuBar extends MenuBar{
	
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

    //Buka page ViewMenuItems yaitu dari MenuItemManagementView
    private void openViewMenuItemsPage(Stage stage) {
        MenuItemManagementView menuItemView = new MenuItemManagementView(stage);
        menuItemView.showMenuItemView();
    }

    //Buka page ViewAllUser yaitu dari userManagementView
    private void openViewAllUsersPage(Stage stage) {
        UserManagementView userManagementView = new UserManagementView(stage);
        userManagementView.showUserManagementView();
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
