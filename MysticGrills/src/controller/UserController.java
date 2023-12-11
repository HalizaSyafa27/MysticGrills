package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import model.User;
import repository.UserRepository;
import view.AdminMenuBar;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class UserController {
	private Map<String, User> userDatabase;
	private final UserRepository userRepository;

	public UserController(Map<String, User> userDatabase, UserRepository userRepository) {
		this.userDatabase = userDatabase;
		this.userRepository = userRepository;
	}

	public boolean registerUser(String username, String email, String password, String confirmPassword,
			Label errorLabel) {
		if (isRegistrationValid(username, email, password, confirmPassword)) {
			User newUser = new User(username, email, password, "Customer");

			if (userRepository != null) {
				// panggil method createUser dari UserRepository
				if (userRepository.createUser(newUser.getUsername(), newUser.getEmail(), newUser.getPassword())) {
					userDatabase.put(email, newUser);
					return true; // registrasi berhasil
				} else {
					// registrasi gagal
					errorLabel.setText("Registrasi gagal.");
					return false;
				}
			}

		} else {
			errorLabel.setText("Username tidak boleh kosong.");
			return false;
		}
		return false;
	}

	public boolean loginUser(String email, String password, Label errorLabel) {
		
		if(userRepository.isUserExists(email)) {
			if (isLoginValid(email, password)) {
//			UserRepository userRepository = new UserRepository();
//			ResultSet userData = userRepository.getUserData(email, password);
			ResultSet userData = this.userRepository.getUserData(email, password);

			try {
				if (userData != null && userData.next()) {
					return true; // Login berhasil
				} else {
					// Pengguna tidak ditemukan
					errorLabel.setText("Email tidak terdaftar");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		   }
		}
		 else {
			// Login tidak valid
			errorLabel.setText("Login gagal. Mohon periksa input Anda.");
		}

		return false; // Login gagal
	}

	public boolean isRegistrationValid(String username, String email, String password, String confirmPassword) {
		return !username.isEmpty() && isEmailValid(email) && isPasswordValid(password)
				&& isConfirmPasswordValid(password, confirmPassword);
	}

	private boolean isConfirmPasswordValid(String password, String confirmPassword) {
		// TODO Auto-generated method stub
		return password.equals(confirmPassword);
	}

	private boolean isLoginValid(String email, String password) {	
	    // Memeriksa apakah pengguna dengan email yang diberikan ada di database
	    if (userRepository.isUserExists(email)) {
	        // Memeriksa apakah password yang diberikan sesuai dengan password di database
	        return userRepository.isUserValid(email, password);
	    }

	    return false;
	}

	private boolean isEmailValid(String email) {
		return !email.isEmpty() && email.toLowerCase().endsWith("@gmail.com");
	}

	private boolean isPasswordValid(String password) {
		return !password.isEmpty() && password.length() >= 6;
	}
}
