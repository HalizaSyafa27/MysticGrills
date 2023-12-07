package controller;

import java.util.HashMap;
import java.util.Map;
import model.User;
import repository.UserRepository;
import javafx.scene.control.Label;

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
		if (isLoginValid(email, password)) {
			return true;
		} else {
			errorLabel.setText("Login gagal!");
			return false;
		}
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
		return isEmailValid(email) && userDatabase.containsKey(email)
				&& userDatabase.get(email).getPassword().equals(password);
	}

	private boolean isEmailValid(String email) {
		return !email.isEmpty() && email.toLowerCase().endsWith("@gmail.com");
	}

	private boolean isPasswordValid(String password) {
		return !password.isEmpty() && password.length() >= 6;
	}
}
