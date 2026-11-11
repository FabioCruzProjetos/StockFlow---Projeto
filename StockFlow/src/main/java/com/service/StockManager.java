
package com.service;

import com.model.MovementType;
import com.model.Product;
import com.model.StockMovement;
import com.model.exception.InsufficientStockException;
import com.model.exception.InvalidQuantityException;
import com.model.exception.InvalidIDException;
import com.model.exception.ProductDontExistException;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages stock movements in the stock management system.
 * Provides functionality to add and remove stock, maintain a history
 * of stock movements, and retrieve movements associated with a product.
 *
 * @author Fabio
 */
public class StockManager {

    private final ProductManager productManager;
    private final List<StockMovement> stockMovementsHistory;

    /**
     * Creates a stock manager associated with a product manager.
     *
     * @param productManager the manager responsible for managing products
     * @throws IllegalArgumentException if the product manager is null
     */
    public StockManager(ProductManager productManager) {
        if (productManager == null) {
            throw new IllegalArgumentException("Invalid ProductManager");
        }

        this.productManager = productManager;
        this.stockMovementsHistory = new ArrayList<>();
    }

    /**
     * Adds a quantity of stock to a product and records the movement
     * in the stock movement history.
     *
     * @param productID the ID of the product whose stock will be increased
     * @param quantity the quantity of stock to be added
     * @throws InvalidIDException if the product ID is invalid
     * @throws ProductDontExistException if the product does not exist
     * @throws InvalidQuantityException if the quantity is not finite or is less than or equal to zero
     */
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

    /**
     * Removes a quantity of stock from a product and records the movement
     * in the stock movement history.
     *
     * @param productID the ID of the product whose stock will be decreased
     * @param quantity the quantity of stock to be removed
     * @throws InvalidIDException if the product ID is invalid
     * @throws ProductDontExistException if the product does not exist
     * @throws InvalidQuantityException if the quantity is not finite or is less than or equal to zero
     * @throws InsufficientStockException if the requested quantity exceeds the actual stock
     */
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

    /**
     * Returns all stock movements recorded in the history.
     * The returned list is a copy of the internal collection.
     *
     * @return a new list containing all stock movements
     */
    public List<StockMovement> getStockMovementsHistory() {
        return new ArrayList<>(stockMovementsHistory);
    }

    /**
     * Returns all stock movements associated with a specific product.
     *
     * @param productID the ID of the product
     * @return a list containing all stock movements associated with the product
     * @throws InvalidIDException if the product ID is invalid
     * @throws ProductDontExistException if the product does not exist
     */
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

    /**
     * Validates a stock movement quantity.
     *
     * @param quantity the quantity to be validated
     * @return the validated quantity
     * @throws InvalidQuantityException if the quantity is not finite or is less than or equal to zero
     */
    private double validateQuantity(double quantity) {
        if (!Double.isFinite(quantity) || quantity <= 0) {
            throw new InvalidQuantityException("Invalid quantity");
        }

        return quantity;
    }
}
