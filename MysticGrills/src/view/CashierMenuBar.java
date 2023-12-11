package view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class CashierMenuBar extends MenuBar{
    public CashierMenuBar(Stage stage) {
        // Menu "View Receipts"
        Menu viewReceiptsMenu = new Menu("View Receipts");
        MenuItem viewReceiptsItem = new MenuItem("View Receipts");
        viewReceiptsMenu.getItems().add(viewReceiptsItem);

        // Menu "View Order Details"
        Menu viewOrderDetailsMenu = new Menu("View Order Details");
        MenuItem viewOrderDetailsItem = new MenuItem("View Order Details");
        viewOrderDetailsMenu.getItems().add(viewOrderDetailsItem);

        // Menu "Process Order Payment"
        Menu processOrderPaymentMenu = new Menu("Process Order Payment");
        MenuItem processOrderPaymentItem = new MenuItem("Process Order Payment");
        processOrderPaymentMenu.getItems().add(processOrderPaymentItem);

        // Menu "Logout"
        Menu logoutMenu = new Menu("Logout");
        MenuItem logoutItem = new MenuItem("Logout");
        logoutMenu.getItems().add(logoutItem);

        // Menambahkan semua menu ke menu bar
        this.getMenus().addAll(viewReceiptsMenu, viewOrderDetailsMenu, processOrderPaymentMenu, logoutMenu);

        // Menangani aksi ketika menu dipilih
        viewReceiptsItem.setOnAction(e -> {
            // Tambahkan logika untuk menangani aksi "View Receipts" di sini
            System.out.println("View Receipts");
        });

        viewOrderDetailsItem.setOnAction(e -> {
            // Tambahkan logika untuk menangani aksi "View Order Details" di sini
            System.out.println("View Order Details");
        });

        processOrderPaymentItem.setOnAction(e -> {
            // Tambahkan logika untuk menangani aksi "Process Order Payment" di sini
            System.out.println("Process Order Payment");
        });

        logoutItem.setOnAction(e -> {
            // Tambahkan logika untuk menangani aksi "Logout" di sini
            System.out.println("Logout");
        });
    }
}
