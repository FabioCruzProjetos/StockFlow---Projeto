
package com;

import com.model.Product;
import com.model.StockMovement;
import com.service.ProductManager;
import com.service.StockManager;
import com.model.exception.InsufficientStockException;

public class Main {

    public static void main(String[] args) {

        // Create products
        Product product1 = new Product(
                "Posta de Bacalhau",
                "CaixaMar",
                "Posta de Bacalhau 400-600",
                18.5,
                5
        );

        Product product2 = new Product(
                "Posta de Bacalhau",
                "CaixaMar",
                "Posta de Bacalhau 300-600",
                14.5,
                5
        );

        // Initialize managers
        ProductManager productManager = new ProductManager();
        StockManager stockManager = new StockManager(productManager);

        // Add products
        productManager.addProduct(product1);
        productManager.addProduct(product2);

        // Perform stock movements
        stockManager.addStock(product1.getId(), 10);
        stockManager.removeStock(product1.getId(), 3);
        stockManager.addStock(product2.getId(), 10);

        // Display products
        System.out.println("===== PRODUCTS =====");
        System.out.println(product1);
        System.out.println(product2);

        // Display complete stock movement history
        System.out.println("\n===== STOCK MOVEMENT HISTORY =====");

        for (StockMovement stockMovement :
                stockManager.getStockMovementsHistory()) {

            System.out.println(stockMovement);
        }

        // Display movement history for product 1
        System.out.println("\n===== PRODUCT 1 MOVEMENT HISTORY =====");

        for (StockMovement stockMovement :
                stockManager.getStockMovementsHistoryOfProduct(product1.getId())) {

            System.out.println(stockMovement);
        }

        // Display current stock
        System.out.println("\n===== CURRENT STOCK =====");
        System.out.println(
                product1.getName() + ": " + product1.getActualStock()
        );

        // Test insufficient stock handling
        System.out.println("\n===== INSUFFICIENT STOCK TEST =====");

        try {
            stockManager.removeStock(product1.getId(), 100);
        } catch (InsufficientStockException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Verify stock remains unchanged
        System.out.println(
                "Stock after failed removal: " + product1.getActualStock()
        );

        // Verify movement history remains unchanged
        System.out.println(
                "Total movements: "
                        + stockManager.getStockMovementsHistory().size()
        );
    }
}
