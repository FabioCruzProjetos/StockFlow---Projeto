package com;

import com.model.Product;
import com.model.StockMovement;
import com.service.ProductManager;
import com.service.StockManager;

public class Main {

    public static void main(String[] args) {

        Product product1 = new Product("Posta de Bacalhau", "CaixaMar", "Posta de Bacalhau 400-600", 18.5, 5);
        Product product2 = new Product("Posta de Bacalhau", "CaixaMar", "Posta de Bacalhau 300-600", 18.5, 5);
        ProductManager productManager = new ProductManager();
        productManager.addProduct(product1);
        productManager.addProduct(product2);

        StockManager stockManager = new StockManager(productManager);

        stockManager.addStock(product1.getId(), 10);
        stockManager.removeStock(product1.getId(), 3);
        stockManager.addStock(product2.getId(), 10);

        IO.println(product1);

        for(StockMovement stockMovement : stockManager.getStockMovementsHistory()) {
            IO.println(stockMovement);
        }

        IO.println("");

        for(StockMovement stockMovement : stockManager.getStockMovementsHistoryOfProduct(product1.getId())) {
            IO.println(stockMovement);
        }

        IO.println(product1.getActualStock());

    }

}
