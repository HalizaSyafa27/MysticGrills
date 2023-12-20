package view;

import controller.ReceiptController;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Receipt;
import model.OrderItem;

public class ReceiptDetailsView {

    private TableView<OrderItem> table;
    private Stage stage;
    private ReceiptController rc;
    private ReceiptView receiptView;

    public ReceiptDetailsView(Stage stage) {
        this.stage = stage;
    }

    // Method untuk menampilkan detail receipt
    public void showReceiptDetailsView(Receipt receipt) {
    	
        VBox root = new VBox();

        table = createReceiptDetailsTable();
        
        table.getItems().clear();
        
        String id = receiptView.getIdInput().getText();
        rc.loadReceiptDetail(id);

        GridPane form = createReceiptDetailsForm();
        VBox.setMargin(form, new Insets(20));
        root.getChildren().addAll(table, form);

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Receipt Details");
        stage.show();
    }

    // Membuat tabel untuk menampilkan detail item pada receipt
    private TableView<OrderItem> createReceiptDetailsTable() {
        TableView<OrderItem> table = new TableView<>();
        TableColumn<OrderItem, String> itemNameColumn = new TableColumn<>("Item Name");
        itemNameColumn.setCellValueFactory(cellData -> cellData.getValue().itemNameProperty());

        TableColumn<OrderItem, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getPrice()));

        TableColumn<OrderItem, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());

        table.getColumns().addAll(itemNameColumn, priceColumn, quantityColumn);
        return table;
    }

//    // Mengisi tabel dengan data dari receipt
//    private void populateTable(Receipt receipt) {
//        // Bersihkan isi tabel sebelum menambahkan item baru
//        table.getItems().clear();
//
////        // Periksa apakah objek Receipt memiliki daftar item
////        if (receipt.getItems() != null) {
////            // Lakukan iterasi pada daftar item dan tambahkan ke tabel
////            for (OrderItem item : receipt.getItems()) {
////                table.getItems().add(item);
////            }
////        }
//    }


    // Membuat form (jika diperlukan)
    private GridPane createReceiptDetailsForm() {
        GridPane form = new GridPane();
        // Tambahkan komponen form sesuai kebutuhan
        return form;
    }

	public TableView<OrderItem> getTable() {
		return table;
	}

	public void setTable(TableView<OrderItem> table) {
		this.table = table;
	}
    
    

}
