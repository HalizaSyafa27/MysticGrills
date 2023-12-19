package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Order;

public class OrderDetailView {

    private Stage stage;
    private TextField userInput = new TextField();
    private TextField statusInput = new TextField();
    private TextField quantityInput = new TextField();
    private Order selectedOrder;

    public OrderDetailView(Stage stage) {
        this.stage = stage;
    }

    public void showOrderDetailView(Order order) {
        this.selectedOrder = order;

        VBox root = new VBox();

        GridPane form = createOrderForm();

        VBox.setMargin(form, new Insets(20));
        root.getChildren().addAll(form);

        Scene scene = new Scene(root, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Detail Order");
        stage.show();
    }

    private GridPane createOrderForm() {
        GridPane form = new GridPane();
        form.setVgap(20);
        form.setHgap(10);

        form.add(new Label("Order ID:"), 0, 0);
        form.add(new Label(String.valueOf(selectedOrder.getOrderID())), 1, 0);

        form.add(new Label("User:"), 0, 1);
        form.add(new Label(selectedOrder.getOrderUser()), 1, 1);

        form.add(new Label("Status:"), 0, 2);
        form.add(new Label(selectedOrder.getOrderStatus()), 1, 2);

        form.add(new Label("Quantity:"), 0, 3);
        form.add(new Label(String.valueOf(selectedOrder.getOrderQuantity())), 1, 3);

        Button processOrderButton = new Button("Proses Order");
        form.add(processOrderButton, 0, 4);

        processOrderButton.setOnAction(e -> {
            handleProcessOrder();
        });

        return form;
    }

    private void handleProcessOrder() {
        // Menampilkan ProcessPaymentView dan melakukan update status order jika proses pembayaran berhasil
        ProcessPaymentView processPaymentView = new ProcessPaymentView(stage);
        boolean paymentSuccessful = processPaymentView.showProcessPaymentView(selectedOrder.getOrderID(), selectedOrder.getOrderTotal());

        if (paymentSuccessful) {
            // Update status order menjadi "Paid"
            selectedOrder.setOrderStatus("Paid");
            statusInput.setText("Paid");
        }
    }
}
