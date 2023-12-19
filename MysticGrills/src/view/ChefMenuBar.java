package view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class ChefMenuBar extends MenuBar{
    public ChefMenuBar(Stage stage) {
        // Menu "Prepare - Serve"
        Menu prepareServeMenu = new Menu("Prepare - Serve");
        MenuItem prepareServeItem = new MenuItem("Prepare - Serve");
        prepareServeMenu.getItems().add(prepareServeItem);

        // Menu "Logout"
        Menu logoutMenu = new Menu("Logout");
        MenuItem logoutItem = new MenuItem("Logout");
        logoutMenu.getItems().add(logoutItem);

        // Menambahkan semua menu ke menu bar
        this.getMenus().addAll(prepareServeMenu, logoutMenu);

        // Menangani aksi ketika menu dipilih
        prepareServeItem.setOnAction(e -> {
            // Tambahkan logika untuk menangani aksi "Prepare - Serve" di sini
            System.out.println("Prepare - Serve");
        });

        logoutItem.setOnAction(e -> {
            // Tambahkan logika untuk menangani aksi "Logout" di sini
            System.out.println("Logout");
        });
    }
}
