package controller;

import model.Menu;
import model.Order;
import model.OrderItem;
import repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class OrderController {
	private List<Order> orders; // Simpan daftar pesanan di sini
	private OrderRepository orderRepository;
	private TableView<Menu> menuTable;
	
	
	int orderId;

	public OrderController() {
		this.orders = new ArrayList<>();
		this.orderRepository = new OrderRepository();
	}

	// Method untuk menambahkan menu ke dalam pesanan
	public void addToOrder(Menu menu, int quantity) {
	    // Cek apakah pesanan sudah ada
	    Order existingOrder = findOrderForMenu(menu);

	    if (existingOrder != null) {
	        // Jika pesanan sudah ada, tambahkan jumlah menu yang dipesan
	        existingOrder.addOrderItem(new OrderItem(menu.getName(), menu.getPrice(), quantity));

	        // Update pesanan di database (jika diperlukan)
	        orderRepository.updateOrder(existingOrder);
	    }

	    // Feedback atau tindakan lainnya setelah penambahan ke dalam pesanan
	    System.out.println("Menu added to order successfully!");
	}

	public void handleUpdate() {
		// Implementasi logika untuk memperbarui status order menjadi "Paid" di database
		Order order = orderRepository.getOrderById(orderId);

		if (order != null) {
			order.setOrderStatus("Paid");
			orderRepository.updateOrder(order);
		} else {
			System.out.println("Order dengan ID " + orderId + " tidak ditemukan.");
		}
	}

//
	public void handleAdd(Order order) {
//  // Implementasi logika untuk menyimpan order ke database
		orderRepository.addOrder(order);
	}

//
	public List<Order> getAllOrders() {
//  // Implementasi logika untuk mendapatkan semua order dari database
		return orderRepository.getAllOrders();
	}

//
	public Order getOrderById() {
//  // Implementasi logika untuk mendapatkan order berdasarkan ID dari database
		return orderRepository.getOrderById(orderId);
	}

	public void handleDelete() {
//// Implementasi logika untuk menghapus order dari database
		Order order = orderRepository.getOrderById(orderId);
//
		if (order != null) {
			orderRepository.deleteOrder(order);
			System.out.println("Order dengan ID " + orderId + " berhasil dihapus.");
		} else {
			System.out.println("Order dengan ID " + orderId + " tidak ditemukan.");
		}
	}
	
	public Order findOrderForMenu(Menu menu) {
	    for (Order order : orders) {
	        if (order.containsMenu(menu)) {
	            return order;
	        }
	    }
	    return null;
	}
	
//	public Order getActiveOrder() {
//	    // Logika untuk mendapatkan pesanan aktif, misalnya yang belum dibayar atau yang sedang aktif
//	    // Anda bisa menyesuaikan logika ini sesuai kebutuhan aplikasi Anda
//	    // Contoh sederhana: ambil pesanan pertama dari daftar pesanan (jika ada)
//	    if (!orders.isEmpty()) {
//	        return orders.get(0);
//	    } else {
//	        return null;
//	    }
//	}
	

}
