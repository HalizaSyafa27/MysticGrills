package view;

import java.util.HashMap;
import controller.UserController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import repository.UserRepository;

public class UserView extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	private UserController userController;
    private UserRepository userRepository;

	public UserView() {
	    super();
	    this.userController = new UserController(new HashMap<>(), userRepository);
	}
	 
	// Buttons
	Button buttonRegister = new Button("Register");
	Button buttonLogin = new Button("Login");

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("User View");

		// Layout untuk UserView
		VBox layout = new VBox(10);
		layout.getChildren().addAll(new UnauthorizedMenuBar(primaryStage));

		Scene scene = new Scene(layout, 300, 300);
		primaryStage.setScene(scene);

		primaryStage.show();
	}
}
