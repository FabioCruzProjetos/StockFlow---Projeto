
package com.model;

import com.model.exception.InvalidIDException;
import com.model.exception.InvalidMovementTypeException;
import com.model.exception.InvalidQuantityException;

import java.time.LocalDateTime;

public class StockMovement {

    private static int nextMovementId = 1;

    private final int movementId;
    private final int productId;
    private final double quantity;
    private final MovementType movementType;
    private final LocalDateTime date;

    public StockMovement(int productId, double quantity, MovementType movementType) {

        this.productId = validateId(productId);
        this.quantity = validateQuantity(quantity);
        this.movementType = validateMovementType(movementType);

        this.movementId = nextMovementId;
        nextMovementId++;

        this.date = LocalDateTime.now();
    }

    private int validateId(int id) {
        if (id <= 0) {
            throw new InvalidIDException("Invalid ID");
        }

        return id;
    }

    private double validateQuantity(double quantity) {
        if (!Double.isFinite(quantity) || quantity <= 0) {
            throw new InvalidQuantityException("Invalid quantity");
        }

        return quantity;
    }

    private MovementType validateMovementType(MovementType movementType) {
        if (movementType == null) {
            throw new InvalidMovementTypeException("Invalid movement type");
        }

        return movementType;
    }

    public int getMovementId() {
        return movementId;
    }

    public int getProductId() {
        return productId;
    }

    public double getQuantity() {
        return quantity;
    }

    public MovementType getMovementType() {
        return movementType;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public String toString(){
        return "Movement" + movementId + " " + productId + " " + quantity + " " + movementType.toString();
    }
}
