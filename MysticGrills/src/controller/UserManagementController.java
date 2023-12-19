package controller;

import javafx.collections.FXCollections;
import javafx.scene.control.TableView;
import model.User;
import repository.UserManagementRepository;
import view.UserManagementView;

public class UserManagementController {
	private UserManagementView view;
	private UserManagementRepository repository;
	private TableView<User> userTable;
	
	public UserManagementController(UserManagementView view, UserManagementRepository repository, TableView<User> userTable) {
		this.view = view;
		this.repository = repository;
		this.userTable = userTable;
		this.view.setButtonHandlers(this);
		loadDataIntoTable();
	}
	
	// Untuk mengambil data dan di load ke table, fungsi ini jg digunakan untuk merefresh table setelah mengubah user role atau menghapus user
	private void loadDataIntoTable() {
		userTable.setItems(FXCollections.observableArrayList(repository.getAllUser()));
	}
	
	//handle yg digunakan untuk mengubah user role
	public void handleChange() {
		User selectedUser = userTable.getSelectionModel().getSelectedItem();
		if (selectedUser != null) {
			String role = view.getRoleBox().getValue();
			selectedUser.setRole(role);
			repository.updateUser(selectedUser);
			loadDataIntoTable();
		}
	}
	
	//handle yang digunakan untuk menghapus user 
	public void handleDelete() {
		User selectedUser = userTable.getSelectionModel().getSelectedItem();
		if (selectedUser != null) {
			repository.deleteUser(selectedUser.getId());
			loadDataIntoTable();
		}
	}
}
