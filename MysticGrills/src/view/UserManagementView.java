package view;

import controller.UserManagementController;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
import model.User;
import repository.UserManagementRepository;

public class UserManagementView {
	
	//meng-initialize + declare komponen - komponen UI
	private Stage stage;
	@SuppressWarnings("unused")
	private UserManagementController userManagementController;
	private UserManagementRepository userManagementRepository;
	
	private BorderPane borderContainer = new BorderPane();
	private GridPane gridContainer = new GridPane();
	private FlowPane flowContainer = new FlowPane();
	
	private TableView<User> userTable = new TableView<User>();
	
	private Label userIdLbl = new Label("User ID");
	private Label usernameLbl = new Label("Username");
	private Label userRoleLbl = new Label("Role");
	
	private TextField userIdField = new TextField();
	private TextField usernameField = new TextField();
	
	private ComboBox<String> roleBox = new ComboBox<>();
	
	private Button removeButton = new Button("Remove User");
	private Button changeButton = new Button("Change Role");
	
	private ScrollPane scrollPane = new ScrollPane();
	private Scene scene = new Scene(scrollPane, 800, 400);
	
	public UserManagementView(Stage stage) {
		super();
		this.userManagementRepository = new UserManagementRepository();
		this.userManagementController = new UserManagementController(this, userManagementRepository, userTable);
		this.stage = stage;
	}
	
	//menambahkan komponen ke dalam grid dan semacamnya
	public void addComponent() {
		roleBox.getItems().add("Admin");
		roleBox.getItems().add("Chef");
		roleBox.getItems().add("Waiter");
		roleBox.getItems().add("Cashier");
		roleBox.getItems().add("Customer");
		roleBox.getSelectionModel().clearSelection();
		
		gridContainer.add(userIdLbl, 0, 0);
		gridContainer.add(usernameLbl, 0, 1);
		gridContainer.add(userRoleLbl, 0, 2);
		
		gridContainer.add(userIdField, 1, 0);
		gridContainer.add(usernameField, 1, 1);
		gridContainer.add(roleBox, 1, 2);
		
		flowContainer.getChildren().add(removeButton);
		flowContainer.getChildren().add(changeButton);
		gridContainer.add(flowContainer, 1, 3);
		
		borderContainer.setTop(gridContainer);
		borderContainer.setBottom(userTable);
	}
	
	//mengatur - atur kembali komponen agar komponen terlihat lebih rapih
	public void arrangeComponent() {
		BorderPane.setAlignment(gridContainer, Pos.CENTER);
		
		borderContainer.setPadding(new Insets(10));
		gridContainer.setAlignment(Pos.CENTER);
		gridContainer.setVgap(10);
		gridContainer.setHgap(15);
		flowContainer.setAlignment(Pos.CENTER_LEFT);
		flowContainer.setHgap(5);
		
		userIdLbl.setMinWidth(50);
		usernameLbl.setMinWidth(50);
		userRoleLbl.setMinWidth(50);
		
		userIdField.setMaxWidth(250);
		usernameField.setMaxWidth(250);
		roleBox.setMaxWidth(250);
	}
	
	//mengatur table (nambahin nama kolom tabel, ukuran tabel, sama apa yang masuk ke kolom tabel itu nanti)
	@SuppressWarnings("unchecked")
	private void setTable() {
		
		TableColumn<User, Integer> idColumn = new TableColumn<User, Integer>("ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("id")); 
		idColumn.setMinWidth(scrollPane.getWidth() / 3);
		
		TableColumn<User, String> nameColumn = new TableColumn<User, String>("Username");
		nameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
		nameColumn.setMinWidth(scrollPane.getWidth() / 3);
		
		TableColumn<User, String> roleColumn = new TableColumn<User, String>("Role");
		roleColumn.setCellValueFactory(new PropertyValueFactory<User, String>("role"));
		roleColumn.setMinWidth(scrollPane.getWidth() / 3);
		
		userTable.getColumns().addAll(idColumn, nameColumn, roleColumn);
		userTable.setOnMouseClicked(tableMouseEvent());
	}
	
	//mengambil data dari kolom tabel yang dipilih lalu menampilkannya di field yang sudah disediakan
	private EventHandler<MouseEvent> tableMouseEvent(){
		return new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				TableSelectionModel<User> tableSelectionModel = userTable.getSelectionModel();
				tableSelectionModel.setSelectionMode(SelectionMode.SINGLE);
				User user = tableSelectionModel.getSelectedItem();
				
				userIdField.setText(String.valueOf(user.getId()));
				usernameField.setText(user.getUsername());
				roleBox.setValue(user.getRole());
			}
		};
	}
	
	//memasang cara kerja button dengan menggunakan controller
	public void setButtonHandlers(UserManagementController controller) {
		removeButton.setOnAction(e -> {
			controller.handleDelete();
			refreshAllValue();
		});
		changeButton.setOnAction(e -> {
			controller.handleChange();
			refreshAllValue();
		});
	}
	
	//menghapus isi field setelah button di klik
	private void refreshAllValue() {
		userIdField.setText("");
		usernameField.setText("");
		roleBox.setValue(null);
	}
	
	//menggabungkan add, arrange, dan kawan - kawannya secara terpisah dari showUserManagementView biar lebih rapih
	private void combine() {
		addComponent();
		arrangeComponent();
		setTable();
		userIdField.setDisable(true);
		usernameField.setDisable(true);
		scrollPane.setContent(borderContainer);
		scrollPane.setFitToWidth(true);
	}
	
	//fungsi yang nanti dipanggil di AdminMenuBar buat meng-show UI UserManagementView
	public void showUserManagementView() {
		combine();
		
		stage.setTitle("User Management");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}

	//Get + Set buat roleBox
	public ComboBox<String> getRoleBox() {
		return roleBox;
	}

	public void setRoleBox(ComboBox<String> roleBox) {
		this.roleBox = roleBox;
	}
}	
