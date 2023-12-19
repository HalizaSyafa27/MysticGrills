package controller;

import model.Menu;
import model.Order;
import repository.OrderRepository;

import java.util.List;

public class OrderController {
    private OrderRepository orderRepository;
    int orderId;
    
    public OrderController() {
        this.orderRepository = new OrderRepository();
    }

    public void handleAdd(Order order) {
        // Implementasi logika untuk menyimpan order ke database
        orderRepository.addOrder(order);
    }

    public List<Order> getAllOrders() {
        // Implementasi logika untuk mendapatkan semua order dari database
        return orderRepository.getAllOrders();
    }

    public Order getOrderById() {
        // Implementasi logika untuk mendapatkan order berdasarkan ID dari database
        return orderRepository.getOrderById(orderId);
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
    
    public void handleDelete() {
        // Implementasi logika untuk menghapus order dari database
        Order order = orderRepository.getOrderById(orderId);

        if (order != null) {
            orderRepository.deleteOrder(order);
            System.out.println("Order dengan ID " + orderId + " berhasil dihapus.");
        } else {
            System.out.println("Order dengan ID " + orderId + " tidak ditemukan.");
        }
    }
    
}
