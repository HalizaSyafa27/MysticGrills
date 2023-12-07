package view;

import java.util.HashMap;
import controller.UserController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import repository.UserRepository;
public class RegisterView {

	private UserController userController;
	private UserRepository userRepository;
	private Stage stage;

	public RegisterView(Stage stage) {
		// TODO Auto-generated constructor stub
		super();
		userRepository = new UserRepository();
		this.userController = new UserController(new HashMap<>(), userRepository);
		this.stage = stage;
	}

	//label dan textfields
	Label labelUsername = new Label("Username:");
	TextField textFieldUsername = new TextField();

	Label labelEmail = new Label("Email:");
	TextField textFieldEmail = new TextField();

	Label labelPassword = new Label("Password:");
	PasswordField passwordField = new PasswordField();

	Label labelConfirmPassword = new Label("Confirm Password:");
	PasswordField confirmPasswordField = new PasswordField();

	Button buttonRegister = new Button("Register");
	Label errorLabel = new Label();

	public void showRegisterView() {

		VBox page = new VBox(10);

		GridPane grid = new GridPane();
		grid.setVgap(10);
		grid.setHgap(10);


		grid.add(labelUsername, 0, 0);
		grid.add(textFieldUsername, 1, 0);
		grid.add(labelEmail, 0, 1);
		grid.add(textFieldEmail, 1, 1);
		grid.add(labelPassword, 0, 2);
		grid.add(passwordField, 1, 2);
		grid.add(labelConfirmPassword, 0, 3);
		grid.add(confirmPasswordField, 1, 3);
		grid.add(buttonRegister, 1, 4);
		grid.add(errorLabel, 1, 5);

		buttonRegister.setOnAction(e -> {
			handleRegistration(
					textFieldUsername.getText(), textFieldEmail.getText(),
					passwordField.getText(), confirmPasswordField.getText());
		});

		page.getChildren().addAll(new UnauthorizedMenuBar(stage), grid);

		Scene scene = new Scene(page, 300, 300);
		stage.setScene(scene);
		stage.show();
	}

	private void handleRegistration(String username, String email, String password, String confirmPassword) {
		if (userController.isRegistrationValid(username, email, password, confirmPassword)) {
			userController.registerUser(username, email, password, confirmPassword, this.errorLabel);
			errorLabel.setText("Registrasi berhasil!");
		} else {
			// Logika lanjutan jika registrasi gagal
			errorLabel.setText("Registrasi gagal. Mohon periksa input Anda.");
		} 
		clearInputFields();
	}

	private void clearInputFields() {
		// TODO Auto-generated method stub
		textFieldUsername.setText("");
		textFieldEmail.setText("");
		passwordField.setText("");
		confirmPasswordField.setText("");
	}
}

