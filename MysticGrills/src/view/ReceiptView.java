package view;

import java.sql.Date;

import controller.ReceiptController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Receipt;

public class ReceiptView {
    private TextField idInput = new TextField();
    private TableView<Receipt> table;
    private Button showDetailsButton, searchButton;
    private Stage stage;
    private ReceiptController receiptController;

    private ReceiptDetailsView detailsView; // Menambahkan instance ReceiptDetailsView

    private TableView<Receipt> createReceiptTable() {
        TableView<Receipt> table = new TableView<>();
        TableColumn<Receipt, Number> idColumn = new TableColumn<>("Receipt ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ReceiptID"));
        idColumn.setMinWidth(100);

        TableColumn<Receipt, Number> amountColumn = new TableColumn<>("Payment Amount");
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("ReceiptPaymentAmount"));
        amountColumn.setMinWidth(150);

        TableColumn<Receipt, Date> dateColumn = new TableColumn<>("Payment Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("ReceiptPaymentDate"));
        dateColumn.setMinWidth(120);

        TableColumn<Receipt, String> typeColumn = new TableColumn<>("Payment Type");
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("ReceiptPaymentType"));
        typeColumn.setMinWidth(150); 
        
        TableColumn<Receipt, Number> orderColumn = new TableColumn<>("Order ID");
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("OrderID"));
        typeColumn.setMinWidth(150); 

        table.getColumns().addAll(idColumn, amountColumn, dateColumn, typeColumn, orderColumn);

        // Menambahkan listener untuk menanggapi klik pada baris
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                showDetailsButton.setDisable(false);
            } else {
                showDetailsButton.setDisable(true);
            }
        });

        return table;
    }

    private GridPane createReceiptForm() {
        GridPane form = new GridPane();
        form.setVgap(20);
        form.setHgap(10);
        
        receiptController.loadReceiptData();
        
        showDetailsButton = new Button("Show Details");
        showDetailsButton.setDisable(true);
        searchButton = new Button("Search Receipt");

        form.add(new Label("Receipt ID"), 0, 0);
        form.add(idInput, 1, 0);
        form.add(showDetailsButton, 0, 1);

        // Button untuk menampilkan detail receipt
        showDetailsButton.setOnAction(e -> {
            Receipt selectedReceipt = table.getSelectionModel().getSelectedItem();
            if (selectedReceipt != null) {
                detailsView.showReceiptDetailsView(selectedReceipt);
            }
            
        });
        searchButton.setOnAction(e -> {
        	String id = idInput.getText();
        	receiptController.loadSearchedReceipt(id);
        });

        return form;
    }

    public TextField getIdInput() {
        return idInput;
    }

    public ReceiptView(Stage stage) {
        this.stage = stage;
        detailsView = new ReceiptDetailsView(new Stage()); // Membuat instance ReceiptDetailsView
    }

    public void showReceiptView() {
        VBox root = new VBox();

        table = createReceiptTable();
        GridPane form = createReceiptForm();
        

        VBox.setMargin(form, new Insets(20));
        root.getChildren().addAll(table, form);

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle("View Receipt");
        stage.show();
    }

	public TableView<Receipt> getTable() {
		return table;
	}

	public void setTable(TableView<Receipt> table) {
		this.table = table;
	}
    
    
}
