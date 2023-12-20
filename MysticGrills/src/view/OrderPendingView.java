package view;

import java.util.List;
import controller.MenuItemManagementController;
import controller.OrderController;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableSelectionModel;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Menu;
import model.Order;
import repository.MenuItemManagementRepository;
import repository.OrderRepository;

public class OrderPendingView {
	private Stage stage;
	private OrderController orderController;
	private OrderRepository orderRepository;
	
	private BorderPane borderContainer = new BorderPane();
	private GridPane gridContainer = new GridPane();
	private FlowPane flowContainer = new FlowPane();
	
	private TableView<Order> orderTable = new TableView<>();
	
    private TextField orderIdField = new TextField();
    private TextField orderNameField = new TextField();
    private TextField orderDescField = new TextField();
    private TextField orderStatusField = new TextField();
    
    private Button updateButton = new Button("Update Order");
    private Button deleteButton = new Button("Delete Order");
    
    private Label orderIdLbl = new Label("Order ID");
    private Label orderNameLbl = new Label("Order Name");
    private Label orderDescLbl = new Label("Description");
    private Label orderStatusLbl = new Label("Order Status");
    public Label validationLbl = new Label();
    
    private ScrollPane scrollPane = new ScrollPane();
    private Scene scene = new Scene(scrollPane, 800, 400);
    
    public OrderPendingView(Stage stage) {
    	super();
    	orderRepository = new OrderRepository();
    	this.orderController = new OrderController();
    	this.stage = stage;
    }
    
    public void addComponent() {
        gridContainer.add(orderIdLbl, 0, 0);
        gridContainer.add(orderNameLbl, 0, 1);
        gridContainer.add(orderDescLbl, 0, 2);
        gridContainer.add(orderStatusLbl, 0, 4);

        gridContainer.add(orderIdField, 1, 0);
        gridContainer.add(orderNameField, 1, 1);
        gridContainer.add(orderDescField, 1, 2);
        gridContainer.add(orderStatusField, 1, 4);

        flowContainer.getChildren().addAll(updateButton, deleteButton);
        gridContainer.add(flowContainer, 1, 5);
        
        gridContainer.add(validationLbl, 1, 6);
        
        borderContainer.setTop(gridContainer);
        borderContainer.setBottom(orderTable);
    }
    
    public void arrangeComponent() {
        BorderPane.setAlignment(gridContainer, Pos.CENTER);

        borderContainer.setPadding(new Insets(10));
        gridContainer.setAlignment(Pos.CENTER);
        gridContainer.setVgap(10);
        gridContainer.setHgap(5);
        flowContainer.setAlignment(Pos.CENTER_LEFT);
        flowContainer.setHgap(5);

        orderIdField.setMaxWidth(250);
        orderNameField.setMaxWidth(250);
        orderDescField.setMaxWidth(250);
        orderStatusField.setMaxWidth(250);

        orderIdField.setMinWidth(50);
        orderNameField.setMinWidth(50);
        orderDescField.setMinWidth(50);
        orderStatusField.setMinWidth(50);
    }
    
    public void setValidationMessage(String message) {
    	validationLbl.setText(message);
    }
    
    private void setTable() {
		TableColumn<Order, Integer> idColumn = new TableColumn<Order, Integer>("Order ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<Order, Integer>("OrderID"));  //bwt nambahin data ke table
		idColumn.setMinWidth(scrollPane.getWidth() / 4);
		
		TableColumn<Order, String> nameColumn = new TableColumn<Order, String>("Order Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("OrderName"));  //bwt nambahin data ke table
		nameColumn.setMinWidth(scrollPane.getWidth() / 4);
		
		TableColumn<Order, String> descColumn = new TableColumn<Order, String>("Description");
		descColumn.setCellValueFactory(new PropertyValueFactory<Order, String>("OrderDesc"));
		descColumn.setMinWidth(scrollPane.getWidth() / 4);
		
		TableColumn<Order, Integer> statusColumn = new TableColumn<Order, Integer>("Status");
		statusColumn.setCellValueFactory(new PropertyValueFactory<Order, Integer>("OrderStatus"));
		statusColumn.setMinWidth(scrollPane.getWidth() / 4);
		
	    orderTable.getColumns().addAll(idColumn, nameColumn, descColumn, statusColumn);

	    List<Order> pendingOrders = orderController.getAllPendingOrders();
	    orderTable.getItems().addAll(pendingOrders);
	    orderTable.setOnMouseClicked(tableMouseEvent());
	}
    
    private EventHandler<MouseEvent> tableMouseEvent(){
		return new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				TableSelectionModel<Order> tableSelectionModel = orderTable.getSelectionModel();
				tableSelectionModel.setSelectionMode(SelectionMode.SINGLE);
				Order order = tableSelectionModel.getSelectedItem();
				
				orderIdField.setText(String.valueOf(order.getOrderID()));
				orderNameField.setText(order.getOrderName());
				orderDescField.setText(order.getOrderDesc());
				orderStatusField.setText(order.getOrderStatus());
			}
		};
	}
    
    public void setButtonHandlers(OrderController controller) {
        updateButton.setOnAction(e -> {
        	controller.handleUpdate();
        	refreshAllValue();
        });
        deleteButton.setOnAction(e -> {
        	controller.handleDelete();
        	refreshAllValue();
        });
    }
    
    //menghapus isi field setelah button di klik
    private void refreshAllValue() {
    	orderIdField.setText("");
    	orderNameField.setText("");
    	orderDescField.setText("");
    	orderStatusField.setText("");
    	
	}
    
    //menggabungkan add, arrange, dan kawan - kawannya secara terpisah dari showMenuItemManagementView biar lebih rapih
    public void combine() {
    	addComponent();
    	arrangeComponent();
    	setTable();
    	orderIdField.setDisable(true);
    	scrollPane.setContent(borderContainer);
        scrollPane.setFitToWidth(true);
    }
    
  //fungsi yang nanti dipanggil di AdminMenuBar buat meng-show UI MenuItemView
    public void showOrderPendingView() {
        combine();

        stage.setTitle("Order Pending View");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
