package view;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Menu;
import repository.MenuItemManagementRepository;
import controller.MenuItemManagementController;


public class MenuItemManagementView {
	
	//meng-initialize + declare komponen - komponen UI
	private Stage stage;
	@SuppressWarnings("unused")
	private MenuItemManagementController menuItemController;
	private MenuItemManagementRepository menuItemRepository;
	
    private BorderPane borderContainer = new BorderPane();
    private GridPane gridContainer = new GridPane();;
    private FlowPane flowContainer = new FlowPane();
    
    private TableView<Menu> menuTable = new TableView<>();
    
    private TextField menuIdField = new TextField();
    private TextField menuNameField = new TextField();
    private TextField menuDescField = new TextField();
    private TextField menuPriceField = new TextField();
    
    private Button updateButton = new Button("Update Menu");
    private Button addButton = new Button("Add Menu");
    private Button deleteButton = new Button("Delete Menu");
    
    private Label menuIdLbl = new Label("Menu ID");
    private Label menuNameLbl = new Label("Menu Name");
    private Label menuDescLbl = new Label("Description");
    private Label menuPriceLbl = new Label("Price");
    public Label validationLbl = new Label();
    
    private ScrollPane scrollPane = new ScrollPane();
    private Scene scene = new Scene(scrollPane, 800, 400);
    
    public MenuItemManagementView(Stage stage) {
        super();
        menuItemRepository = new MenuItemManagementRepository();
        this.menuItemController = new MenuItemManagementController(this, menuItemRepository, menuTable);
        this.stage = stage;
    }

    //menambahkan komponen ke dalam grid dan semacamnya
    public void addComponent() {
        gridContainer.add(menuIdLbl, 0, 0);
        gridContainer.add(menuNameLbl, 0, 1);
        gridContainer.add(menuDescLbl, 0, 2);
        gridContainer.add(menuPriceLbl, 0, 3);

        gridContainer.add(menuIdField, 1, 0);
        gridContainer.add(menuNameField, 1, 1);
        gridContainer.add(menuDescField, 1, 2);
        gridContainer.add(menuPriceField, 1, 3);

        flowContainer.getChildren().addAll(updateButton, addButton, deleteButton);
        gridContainer.add(flowContainer, 1, 4);
        
        gridContainer.add(validationLbl, 1, 5);
        
        borderContainer.setTop(gridContainer);
        borderContainer.setBottom(menuTable);
    }

    //mengatur - atur kembali komponen agar komponen terlihat lebih rapih
    public void arrangeComponent() {
        BorderPane.setAlignment(gridContainer, Pos.CENTER);

        borderContainer.setPadding(new Insets(10));
        gridContainer.setAlignment(Pos.CENTER);
        gridContainer.setVgap(10);
        gridContainer.setHgap(5);
        flowContainer.setAlignment(Pos.CENTER_LEFT);
        flowContainer.setHgap(5);

        menuIdField.setMaxWidth(250);
        menuNameField.setMaxWidth(250);
        menuDescField.setMaxWidth(250);
        menuPriceField.setMaxWidth(250);

        menuIdLbl.setMinWidth(50);
        menuNameLbl.setMinWidth(50);
        menuDescLbl.setMinWidth(50);
        menuPriceLbl.setMinWidth(50);
    }
    
    //Untuk menampilkan pesan validasi
    public void setValidationMessage(String message) {
    	validationLbl.setText(message);
    }

    //mengatur table (nambahin nama kolom tabel, ukuran tabel, sama apa yang masuk ke kolom tabel itu nanti)
    @SuppressWarnings("unchecked")
    private void setTable() {
		TableColumn<Menu, Integer> idColumn = new TableColumn<Menu, Integer>("Menu ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<Menu, Integer>("id"));  //bwt nambahin data ke table
		idColumn.setMinWidth(scrollPane.getWidth() / 4);
		
		TableColumn<Menu, String> nameColumn = new TableColumn<Menu, String>("Menu Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<Menu, String>("name"));  //bwt nambahin data ke table
		nameColumn.setMinWidth(scrollPane.getWidth() / 4);
		
		TableColumn<Menu, String> descColumn = new TableColumn<Menu, String>("Description");
		descColumn.setCellValueFactory(new PropertyValueFactory<Menu, String>("desc"));
		descColumn.setMinWidth(scrollPane.getWidth() / 4);
		
		TableColumn<Menu, Integer> priceColumn = new TableColumn<Menu, Integer>("Price");
		priceColumn.setCellValueFactory(new PropertyValueFactory<Menu, Integer>("price"));
		priceColumn.setMinWidth(scrollPane.getWidth() / 4);
		
		menuTable.getColumns().addAll(idColumn, nameColumn, descColumn, priceColumn);
		menuTable.setOnMouseClicked(tableMouseEvent());
	}
    
    //mengambil data dari kolom tabel yang dipilih lalu menampilkannya di field yang sudah disediakan
    private EventHandler<MouseEvent> tableMouseEvent(){
		return new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				TableSelectionModel<Menu> tableSelectionModel = menuTable.getSelectionModel();
				tableSelectionModel.setSelectionMode(SelectionMode.SINGLE);
				Menu menu = tableSelectionModel.getSelectedItem();
				
				menuIdField.setText(String.valueOf(menu.getId()));
				menuNameField.setText(menu.getName());
				menuDescField.setText(menu.getDesc());
				menuPriceField.setText(String.valueOf(menu.getPrice()));
			}
		};
	}
    
    //memasang cara kerja button dengan menggunakan controller
    public void setButtonHandlers(MenuItemManagementController controller) {
        updateButton.setOnAction(e -> {
        	controller.handleUpdate();
        	refreshAllValue();
        });
        addButton.setOnAction(e -> {
        	controller.handleAdd();
        	refreshAllValue();
        });
        deleteButton.setOnAction(e -> {
        	controller.handleDelete();
        	refreshAllValue();
        });
    }
    
    //menghapus isi field setelah button di klik
    private void refreshAllValue() {
		menuIdField.setText("");
		menuNameField.setText("");
		menuDescField.setText("");
		menuPriceField.setText("");
	}
    
    //menggabungkan add, arrange, dan kawan - kawannya secara terpisah dari showMenuItemManagementView biar lebih rapih
    public void combine() {
    	addComponent();
    	arrangeComponent();
    	setTable();
    	menuIdField.setDisable(true);
    	scrollPane.setContent(borderContainer);
        scrollPane.setFitToWidth(true);
    }
    
  //fungsi yang nanti dipanggil di AdminMenuBar buat meng-show UI MenuItemView
    public void showMenuItemView() {
        combine();

        stage.setTitle("Menu Item Management");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    //Getter + Setter
	public TextField getMenuNameField() {
		return menuNameField;
	}

	public void setMenuNameField(TextField menuNameField) {
		this.menuNameField = menuNameField;
	}

	public TextField getMenuDescField() {
		return menuDescField;
	}

	public void setMenuDescField(TextField menuDescField) {
		this.menuDescField = menuDescField;
	}

	public TextField getMenuPriceField() {
		return menuPriceField;
	}

	public void setMenuPriceField(TextField menuPriceField) {
		this.menuPriceField = menuPriceField;
	} 
}