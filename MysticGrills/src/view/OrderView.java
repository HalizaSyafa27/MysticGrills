package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Order;

import java.sql.Date;

public class OrderView {

    private Stage stage;
    private TableView<Order> table;
    private TextField userInput = new TextField();
    private TextField statusInput = new TextField();
    private TextField quantityInput = new TextField();

    public OrderView(Stage stage) {
        this.stage = stage;
    }
    
    //View buat customer
    public void showOrderView() {
        VBox root = new VBox();

        table = createOrderTable();
        GridPane form = createOrderForm();

        VBox.setMargin(form, new Insets(20));
        root.getChildren().addAll(table, form);

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle("View Order");
        stage.show();
    }
    
    
    //View buat cashier
    public void showReceiptOrderView() {
        VBox root = new VBox();

        table = createOrderTable();

        root.getChildren().addAll(table);

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle("View Order");
        stage.show();
    }

    private TableView<Order> createOrderTable() {
        TableView<Order> table = new TableView<>();

        TableColumn<Order, Number> idColumn = new TableColumn<>("Order ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("OrderID"));

        TableColumn<Order, String> userColumn = new TableColumn<>("User");
        userColumn.setCellValueFactory(new PropertyValueFactory<>("OrderUser"));

        TableColumn<Order, String> statusColumn = new TableColumn<>("Order Status");
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("OrderStatus"));

        TableColumn<Order, Date> dateColumn = new TableColumn<>("Order Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("OrderDate"));

        TableColumn<Order, Number> totalColumn = new TableColumn<>("Total");
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("OrderTotal"));

        table.getColumns().addAll(idColumn, userColumn, statusColumn, dateColumn, totalColumn);

        // Menambahkan listener untuk menanggapi klik pada baris
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                handleOrderSelection(newSelection);
            }
        });

        return table;
    }

    private GridPane createOrderForm() {
        GridPane form = new GridPane();
        form.setVgap(20);
        form.setHgap(10);

        form.add(new Label("User:"), 0, 0);
        form.add(userInput, 1, 0);
        form.add(new Label("Status:"), 0, 1);
        form.add(statusInput, 1, 1);
        form.add(new Label("Quantity:"), 0, 2);
        form.add(quantityInput, 1, 2);

        return form;
    }

    private void handleOrderSelection(Order selectedOrder) {
        // Menampilkan detail order saat suatu order dipilih
        OrderDetailView orderDetailView = new OrderDetailView(new Stage());
        orderDetailView.showOrderDetailView(selectedOrder);
    }
}
