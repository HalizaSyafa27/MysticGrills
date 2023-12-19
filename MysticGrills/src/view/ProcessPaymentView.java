package view;

package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Order;
import repository.OrderRepository;

public class ProcessPaymentView {

    private Stage stage;
    private TextField orderIdInput = new TextField();
    private ChoiceBox<String> paymentTypeInput = new ChoiceBox<>();
    private TextField paymentAmountInput = new TextField();

    public ProcessPaymentView(Stage stage) {
        this.stage = stage;
    }

    public boolean showProcessPaymentView(int orderId, double orderTotal) {
        orderIdInput.setText(String.valueOf(orderId));

        paymentTypeInput.getItems().addAll("Cash", "Debit", "Credit");
        paymentAmountInput.setText(String.valueOf(orderTotal));

        VBox root = new VBox();

        GridPane form = createPaymentForm();

        VBox.setMargin(form, new Insets(20));
        root.getChildren().addAll(form);

        Scene scene = new Scene(root, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Proses Pembayaran");
        stage.showAndWait();  // Menunggu sampai user menutup jendela ini

        // Mengembalikan true jika pembayaran berhasil, false jika tidak
        return /* Logika pembayaran berhasil atau tidak */;
    }

    private GridPane createPaymentForm() {
        GridPane form = new GridPane();
        form.setVgap(20);
        form.setHgap(10);

        form.add(new Label("Order ID:"), 0, 0);
        form.add(orderIdInput, 1, 0);

        form.add(new Label("Payment Type:"), 0, 1);
        form.add(paymentTypeInput, 1, 1);

        form.add(new Label("Payment Amount:"), 0, 2);
        form.add(paymentAmountInput, 1, 2);

        Button confirmButton = new Button("Confirmation Payment");
        form.add(confirmButton, 0, 3);

        confirmButton.setOnAction(e -> {
            handleConfirmPayment();
        });

        return form;
    }

    private void handleConfirmPayment() {
    	  // Mendapatkan data input
    	    int orderId = Integer.parseInt(orderIdInput.getText());
    	    String paymentType = paymentTypeInput.getValue();
    	    double paymentAmount = Double.parseDouble(paymentAmountInput.getText());

    	    // Memperbarui status pesanan menjadi "Paid"
    	    OrderRepository orderRepository = new OrderRepository(); // Gantilah dengan kelas repository sesuai kebutuhan
    	    Order updatedOrder = orderRepository.getOrderById(orderId);

    	    if (updatedOrder != null) {
    	        updatedOrder.setOrderStatus("Paid");
    	        orderRepository.updateOrder(updatedOrder);
    	        stage.close();
    	    } else {
    	        System.out.println("Order dengan ID " + orderId + " tidak ditemukan.");
    	    }
    }
}
