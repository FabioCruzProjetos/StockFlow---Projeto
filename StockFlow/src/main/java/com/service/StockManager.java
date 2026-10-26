package com.service;

import com.model.MovementType;
import com.model.Product;
import com.model.StockMovement;
import com.model.exception.InsufficientStockException;
import com.model.exception.InvalidQuantityException;

import java.util.ArrayList;
import java.util.List;

public class StockManager {

    private final ProductManager productManager;
    private final List<StockMovement> stockMovementsHistory;

    public StockManager(ProductManager productManager) {
        if (productManager == null) {
            throw new IllegalArgumentException("Invalid ProductManager");
        }

        this.productManager = productManager;
        this.stockMovementsHistory = new ArrayList<>();
    }

    public void addStock(int productID, double quantity) {
        Product product = productManager.getProductByID(productID);
        double quantityValidated = validateQuantity(quantity);

        double newStock = product.getActualStock() + quantityValidated;

        StockMovement movement = new StockMovement(
                product.getId(),
                quantityValidated,
                MovementType.ADD
        );

        product.setActualStock(newStock);
        stockMovementsHistory.add(movement);
    }

    public void removeStock(int productID, double quantity) {
        Product product = productManager.getProductByID(productID);
        double quantityValidated = validateQuantity(quantity);

        if (product.getActualStock() < quantityValidated) {
            throw new InsufficientStockException(
                    "Quantity is greater than actual stock"
            );
        }

        double newStock = product.getActualStock() - quantityValidated;

        StockMovement movement = new StockMovement(
                product.getId(),
                quantityValidated,
                MovementType.REMOVE
        );

        product.setActualStock(newStock);
        stockMovementsHistory.add(movement);
    }

    public List<StockMovement> getStockMovementsHistory() {
        return new ArrayList<>(stockMovementsHistory);
    }

    public List<StockMovement> getStockMovementsHistoryOfProduct(int productID) {
        Product product = productManager.getProductByID(productID);

        List<StockMovement> movementsList = new ArrayList<>();

        for (StockMovement movement : stockMovementsHistory) {
            if (movement.getProductId() == product.getId()) {
                movementsList.add(movement);
            }
        }

        return movementsList;
    }

    private double validateQuantity(double quantity) {
        if (!Double.isFinite(quantity) || quantity <= 0) {
            throw new InvalidQuantityException("Invalid quantity");
        }

        return quantity;
    }
}