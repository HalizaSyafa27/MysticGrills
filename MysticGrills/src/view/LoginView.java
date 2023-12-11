package view;

import java.util.HashMap;
import controller.UserController;
import database.Connect;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;
import repository.UserRepository;

public class LoginView{
	
	private UserController userController;
    private UserRepository userRepository;
    private Stage stage;
//	
	public LoginView(Stage stage) {
		// TODO Auto-generated constructor stub
		super();
		userRepository = new UserRepository();
	    this.userController = new UserController(new HashMap<>(), userRepository);
	    this.stage = stage;
	}
	
	//label dan textfields
    Label labelEmail = new Label("Email:");
    TextField textFieldEmail = new TextField();

    Label labelPassword = new Label("Password:");
    PasswordField passwordField = new PasswordField();

    Button buttonLogin = new Button("Login");
    Label errorLabel = new Label();
    
	public void showLoginView() {
		VBox page = new VBox(10);

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);

        grid.add(labelEmail, 0, 0);
        grid.add(textFieldEmail, 1, 0);
        grid.add(labelPassword, 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(buttonLogin, 1, 2);
        grid.add(errorLabel, 1, 3);

        
        buttonLogin.setOnAction(e -> {
			handleLogin(
					textFieldEmail.getText(),
					passwordField.getText());
		});

		page.getChildren().addAll(new UnauthorizedMenuBar(stage), grid);

        Scene scene = new Scene(page, 300, 300);
        stage.setScene(scene);
        stage.show();
    }
	
	
    private void handleLogin(String email, String password) {
    	Connect connect = new Connect();
        if (userController.loginUser(email, password, this.errorLabel)) {
            // Logika lanjutan setelah login berhasil
        	String userRole = User.getRoleByEmail(email, connect);
            // Cek apakah role adalah admin
            if ("admin".equalsIgnoreCase(userRole)) {
                // Tampilkan AdminMenuBar
                AdminMenuBar adminMenuBar = new AdminMenuBar(stage);
                adminMenuBar.showAdminMenuBar();
            } else if("customer".equalsIgnoreCase(userRole)) {
            	// Tampilkan CustomerMenuBar
                CustomerMenuBar customerMenuBar = new CustomerMenuBar(stage);
                customerMenuBar.showCustomerBar();
            }
            stage.close();
        } else {
            // Logika lanjutan jika login gagal
        	errorLabel.setText("Login gagal. Mohon periksa input Anda.");
        }
        clearInputFields();
    }

	private void clearInputFields() {
		// TODO Auto-generated method stub
	    textFieldEmail.setText("");
	    passwordField.setText("");
	}

}
