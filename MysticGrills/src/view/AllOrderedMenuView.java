package view;

import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Menu;
import model.OrderItem;

import java.util.List;

public class AllOrderedMenuView {

    private Stage stage;
    private TableView<OrderItem> orderItemTable = new TableView<>();
    VBox root = new VBox(10);

    public AllOrderedMenuView(List<OrderItem> orderItems) {
        this.stage = new Stage();
        initComponents(orderItems);
    }

    private void initComponents(List<OrderItem> orderItems) {
        TableColumn<OrderItem, Integer> idColumn = new TableColumn<>("Menu ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("menuId"));

        TableColumn<OrderItem, String> nameColumn = new TableColumn<>("Menu Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("menuName"));

        TableColumn<OrderItem, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn<OrderItem, Integer> totalPriceColumn = new TableColumn<>("Total Price");
        totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        orderItemTable.getColumns().addAll(idColumn, nameColumn, quantityColumn, totalPriceColumn);
        orderItemTable.setItems(FXCollections.observableArrayList(orderItems));

        root.getChildren().addAll(orderItemTable);
    }

    public void showAllOrderMenuView() {
        Scene scene = new Scene(root, 400, 300);
        stage.setScene(scene);
        stage.setTitle("All Ordered Menu");
        stage.show();
    }
}
