
package com.model;

import com.model.exception.InvalidIDException;
import com.model.exception.InvalidMovementTypeException;
import com.model.exception.InvalidQuantityException;

import java.time.LocalDateTime;

/**
 * Represents a stock movement in the stock management system.
 * Stores information about a stock movement, including the product ID,
 * quantity, movement type, and date.
 *
 * Each movement receives a unique ID within the current application session.
 */
public class StockMovement {

    private static int nextMovementId = 1;

    private final int movementId;
    private final int productId;
    private final double quantity;
    private final MovementType movementType;
    private final LocalDateTime date;

    /**
     * Creates a stock movement with the specified information.
     *
     * @param productId the ID of the product associated with the movement
     * @param quantity the quantity involved in the movement, which must be finite and greater than zero
     * @param movementType the type of movement
     * @throws InvalidIDException if the product ID is less than or equal to zero
     * @throws InvalidQuantityException if the quantity is not finite or is less than or equal to zero
     * @throws InvalidMovementTypeException if the movement type is null
     */
    public StockMovement(int productId, double quantity, MovementType movementType) {

        this.productId = validateId(productId);
        this.quantity = validateQuantity(quantity);
        this.movementType = validateMovementType(movementType);

        this.movementId = nextMovementId;
        nextMovementId++;

        this.date = LocalDateTime.now();
    }

    /**
     * Validates a product ID.
     *
     * @param id the ID to validate
     * @return the validated ID
     * @throws InvalidIDException if the ID is less than or equal to zero
     */
    private int validateId(int id) {
        if (id <= 0) {
            throw new InvalidIDException("Invalid ID");
        }

        return id;
    }

    /**
     * Validates a movement quantity.
     *
     * @param quantity the quantity to validate
     * @return the validated quantity
     * @throws InvalidQuantityException if the quantity is not finite
     * or is less than or equal to zero
     */
    private double validateQuantity(double quantity) {
        if (!Double.isFinite(quantity) || quantity <= 0) {
            throw new InvalidQuantityException("Invalid quantity");
        }

        return quantity;
    }

    /**
     * Validates a movement type.
     *
     * @param movementType the movement type to validate
     * @return the validated movement type
     * @throws InvalidMovementTypeException if the movement type is null
     */
    private MovementType validateMovementType(MovementType movementType) {
        if (movementType == null) {
            throw new InvalidMovementTypeException("Invalid movement type");
        }

        return movementType;
    }

    /**
     * Returns the movement ID.
     *
     * @return the movement ID
     */
    public int getMovementId() {
        return movementId;
    }

    /**
     * Returns the ID of the product associated with this movement.
     *
     * @return the product ID
     */
    public int getProductId() {
        return productId;
    }

    /**
     * Returns the quantity involved in this movement.
     *
     * @return the movement quantity
     */
    public double getQuantity() {
        return quantity;
    }

    /**
     * Returns the type of this movement.
     *
     * @return the movement type
     */
    public MovementType getMovementType() {
        return movementType;
    }

    /**
     * Returns the date and time when this movement was created.
     *
     * @return the movement date and time
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Returns a string representation of the stock movement,
     * including its ID, product ID, quantity, and movement type.
     *
     * @return a formatted string containing the movement information
     */
    @Override
    public String toString() {
        return "Movement " + movementId
                + " | Product ID: " + productId
                + " | Quantity: " + quantity
                + " | Type: " + movementType;
    }
}
