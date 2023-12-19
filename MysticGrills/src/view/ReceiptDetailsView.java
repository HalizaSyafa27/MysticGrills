package view;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Receipt;
import model.ReceiptItem;

public class ReceiptDetailsView {

    private TableView<ReceiptItem> table;
    private Stage stage;

    public ReceiptDetailsView(Stage stage) {
        this.stage = stage;
    }

    // Method untuk menampilkan detail receipt
    public void showReceiptDetailsView(Receipt receipt) {
        VBox root = new VBox();

        table = createReceiptDetailsTable();
        populateTable(receipt);

        GridPane form = createReceiptDetailsForm();
        VBox.setMargin(form, new Insets(20));
        root.getChildren().addAll(table, form);

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Receipt Details");
        stage.show();
    }

    // Membuat tabel untuk menampilkan detail item pada receipt
    private TableView<ReceiptItem> createReceiptDetailsTable() {
        TableView<ReceiptItem> table = new TableView<>();
        TableColumn<ReceiptItem, String> itemNameColumn = new TableColumn<>("Item Name");
        itemNameColumn.setCellValueFactory(cellData -> cellData.getValue().itemNameProperty());

        TableColumn<ReceiptItem, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getPrice()));

        TableColumn<ReceiptItem, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());

        table.getColumns().addAll(itemNameColumn, priceColumn, quantityColumn);
        return table;
    }

    // Mengisi tabel dengan data dari receipt
    private void populateTable(Receipt receipt) {
        // Bersihkan isi tabel sebelum menambahkan item baru
        table.getItems().clear();

        // Periksa apakah objek Receipt memiliki daftar item
        if (receipt.getItems() != null) {
            // Lakukan iterasi pada daftar item dan tambahkan ke tabel
            for (ReceiptItem item : receipt.getItems()) {
                table.getItems().add(item);
            }
        }
    }


    // Membuat form (jika diperlukan)
    private GridPane createReceiptDetailsForm() {
        GridPane form = new GridPane();
        // Tambahkan komponen form sesuai kebutuhan
        return form;
    }
}
