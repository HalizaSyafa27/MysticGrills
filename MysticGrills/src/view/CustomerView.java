package view;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Menu;
import model.OrderItem;
import repository.MenuItemManagementRepository;
import repository.OrderItemRepository;
import repository.OrderRepository;
import controller.MenuItemManagementController;

import java.util.List;

public class CustomerView {

    private Stage stage;
    private MenuItemManagementRepository menuRepository;
    private OrderItemRepository orderItemRepository;
    private MenuItemManagementController menuItemManagementController;
    private TableView<Menu> menuTable = new TableView<>();
    private TextField quantityInput = new TextField();
    private Button addButton = new Button("Add Order");
    private Button viewAllOrderedButton = new Button("View All Ordered");
    VBox root = new VBox(10);

    public CustomerView(Stage stage) {
        super();
        this.stage = stage;
        this.menuRepository = new MenuItemManagementRepository();
        // Inisialisasi MenuItemManagementView dan MenuItemManagementController
        MenuItemManagementView menuItemManagementView = new MenuItemManagementView(new Stage());
        this.menuItemManagementController = new MenuItemManagementController(menuItemManagementView, menuRepository, menuTable);

//        this.menuItemManagementController = new MenuItemManagementController(this, menuRepository, menuTable);
        initComponents();
    }

    public void showCustomerView() {
        setupUI();
        setupEventHandlers();
        loadMenuData();
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Customer View");
        stage.show();
    }

    private void initComponents() {
        menuTable.setEditable(false);

        TableColumn<Menu, Integer> idColumn = new TableColumn<>("Menu ID");
//        idColumn.setCellValueFactory(cellData -> cellData.getValue().getId().asObject());
        idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());

        TableColumn<Menu, String> nameColumn = new TableColumn<>("Menu Name");
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));

        TableColumn<Menu, String> descColumn = new TableColumn<>("Description");
        descColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDesc()));

        TableColumn<Menu, Integer> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getPrice()).asObject());
        
        menuTable.getColumns().addAll(idColumn, nameColumn, descColumn, priceColumn);
    }

    private void setupUI() {
        root.getChildren().addAll(menuTable, addButton, viewAllOrderedButton);
    }

    private void setupEventHandlers() {
        addButton.setOnAction(event -> handleAddToOrder());
        viewAllOrderedButton.setOnAction(event -> openViewAllOrderedMenu());
    }

    private void loadMenuData() {
        try {
            List<Menu> menus = menuRepository.getAllMenus();
            menuTable.setItems(FXCollections.observableArrayList(menus));
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exception as needed
        }
    }

    private void handleAddToOrder() {
        // Implementasi logika tambahan saat menambahkan menu ke dalam pesanan
        Menu selectedMenu = menuTable.getSelectionModel().getSelectedItem();
        if (selectedMenu != null) {
            int quantity = Integer.parseInt(quantityInput.getText());
            menuItemManagementController.handleAddToOrder();
        }
    }

    private void openViewAllOrderedMenu() {
    	List<OrderItem> orderItems = orderItemRepository.getAllOrderItems();
    	AllOrderedMenuView allOrderMenuView = new AllOrderedMenuView(orderItems);
    	allOrderMenuView.showAllOrderMenuView();
    }
    
}

