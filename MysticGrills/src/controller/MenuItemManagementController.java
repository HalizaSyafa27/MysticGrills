package controller;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Menu;
import model.Order;
import model.OrderItem;
import repository.MenuItemManagementRepository;
import view.MenuItemManagementView;

public class MenuItemManagementController {
    private MenuItemManagementView view;
    private MenuItemManagementRepository repository;
    private TableView<Menu> menuTable;
    private TextField quantityInput; 
    
    public MenuItemManagementController(MenuItemManagementView view, MenuItemManagementRepository repository, TableView<Menu> menuTable) {
        this.view = view;
        this.repository = repository;
        this.menuTable = menuTable;
        this.view.setButtonHandlers(this);
        loadDataIntoTable();
    }

    Label errorLabel = new Label();
    
    // Untuk mengambil data dan di load ke table, fungsi ini jg digunakan untuk merefresh table setelah mengubah user role atau menghapus user
    private void loadDataIntoTable() {
    	 menuTable.setItems(FXCollections.observableArrayList(repository.getAllMenus()));
    }
    
    // handle yang digunakan untuk memperbarui menu
    public void handleUpdate() {
        Menu selectedMenu = menuTable.getSelectionModel().getSelectedItem();
        if (selectedMenu != null) {
            String name = view.getMenuNameField().getText();
            String desc = view.getMenuDescField().getText();
            int price = Integer.parseInt(view.getMenuPriceField().getText());

            //jika nama menu kosong maka input ga diterima dan keluar validasi
            if (name.isEmpty()) {
            	view.setValidationMessage("Menu name should be filled!");
            	return;
            }
            //jika nama menu diubah dan sama dengan nama menu yang lain maka input ga diterima dan keluar validasi
            if (!selectedMenu.getName().equalsIgnoreCase(name) && !isUniqueName(name, selectedMenu.getId())) {
                view.setValidationMessage("Menu name must be unique!");
                return;
            }
            //jika panjang deskripsi kurang atau sama dengan 10 char, maka input ga diterima dan keluar validasi
            if (desc.length() <= 10) {
            	view.setValidationMessage("Description has to be more than 10 characters!");
            	return;
            }
            //jika harga dibawa 2.5 maka input ga diterima dan keluar validasi
			if (price < 2.5) {
				view.setValidationMessage("Price should be greater or equal to (>=) 2.5!");
				return;
			}
            
            selectedMenu.setName(name);
            selectedMenu.setDesc(desc);
            selectedMenu.setPrice(price);
            repository.updateMenu(selectedMenu);
            loadDataIntoTable();
            view.setValidationMessage("Menu updated ^v^");
        }
    }

    //handle yang digunakan untuk menambahkan menu baru
    public void handleAdd() {
        String name = view.getMenuNameField().getText();
        String desc = view.getMenuDescField().getText();
        int price = Integer.parseInt(view.getMenuPriceField().getText());
        
        //jika nama menu kosong maka input ga diterima dan keluar validasi
        if (name.isEmpty()) {
        	view.setValidationMessage("Menu name should be filled!");
        	return;
        }
        //jika nama menu sudah ada maka input ga diterima dan keluar validasi
        if (!isUniqueName(name, 0)) {
            view.setValidationMessage("Menu name must be unique!");
            return;
        }
        //jika panjang deskripsi kurang atau sama dengan 10 char, maka input ga diterima dan keluar validasi
        if (desc.length() <= 10) {
        	view.setValidationMessage("Description has to be more than 10 characters!");
        	return;
        }
        //jika harga dibawa 2.5 maka input ga diterima dan keluar validasi
		if (price < 2.5) {
			view.setValidationMessage("Price should be greater or equal to 2.5");
			return;
		}
        
        Menu newMenu = new Menu(0, name, desc, price);
        repository.addMenuItem(newMenu);
        loadDataIntoTable();
        view.setValidationMessage("Menu added ^w^");
    }

    //handle yang digunakan untuk menghapus menu
    public void handleDelete() {
        Menu selectedMenu = menuTable.getSelectionModel().getSelectedItem();
        if (selectedMenu != null) {
            repository.deleteMenu(selectedMenu.getId());
            loadDataIntoTable();
            view.setValidationMessage("Menu deleted...");
        }
    }
    
    //digunakan untuk mengecek apakah nama menu sudah ada atau belum
    private boolean isUniqueName(String name, int currentId) {
        List<Menu> allMenus = repository.getAllMenus();
        for (Menu menu : allMenus) {
        	//kalo ditemukan nama yang sama padahal bukan nama sendiri maka nama tidak unik
            if (menu.getName().equalsIgnoreCase(name) && menu.getId() != currentId) {
                return false;
            }
        }
        //kalo ga ada berarti namanya unik
        return true;
    }
    
    public void handleAddToOrder() {
        // Implementasi logika tambahan saat menambahkan menu ke dalam pesanan
        Menu selectedMenu = menuTable.getSelectionModel().getSelectedItem();
        OrderController orderController = new OrderController();
        if (selectedMenu != null) {
            int quantity = Integer.parseInt(quantityInput.getText());
            orderController.addToOrder(selectedMenu, quantity);
        }
    }

}
