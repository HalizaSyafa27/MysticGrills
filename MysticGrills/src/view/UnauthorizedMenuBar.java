package view;

import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

public class UnauthorizedMenuBar extends MenuBar{

	Menu loginMenu, registerMenu;
	Label loginLabel, registerLabel;

	public UnauthorizedMenuBar(Stage stage) {
		loginLabel = new Label("Login");
		registerLabel = new Label("Register");
		loginMenu = new Menu("", loginLabel);
		registerMenu = new Menu("", registerLabel);

		this.getMenus().addAll(loginMenu, registerMenu);

		loginLabel.setOnMouseClicked(e ->{
			openLoginPage(stage);
		});

		registerLabel.setOnMouseClicked(e ->{
			openRegisterPage(stage);
		});
	}

	private void openRegisterPage(Stage stage) {
		// Membuat dan menampilkan halaman Registrasi
		RegisterView registerView = new RegisterView(stage);
		registerView.showRegisterView();
	}
//	
	private void openLoginPage(Stage stage) {
		LoginView loginView = new LoginView(stage);
		loginView.showLoginView();
	}

}
