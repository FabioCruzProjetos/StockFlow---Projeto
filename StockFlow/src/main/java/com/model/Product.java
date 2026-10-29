
package com.model;

/**
 * Represents a product in the stock management system.
 * Stores product information, including its name, supplier,
 * description, price, current stock, and minimum stock level.
 *
 * Each product receives a unique ID within the current application session.
 *
 * @author Fabio Cruz
 */
public class Product {

    private static int idProducts = 1;

    private String name;
    private final int id;
    private String supplier;
    private String description;
    private double price;
    private double actualStock;
    private double minimalStock;

    /**
     * Creates a product with the specified information.
     *
     * @param name the product name
     * @param supplier the product supplier
     * @param description a description of the product
     * @param price the product price, which must be finite and greater than zero
     * @param actualStock the initial stock quantity, which must be finite and non-negative
     * @param minimalStock the minimum stock level, which must be finite and non-negative
     * @throws IllegalArgumentException if a string is null or blank,
     * or if a numeric value violates its validation rules
     */
    public Product(String name, String supplier, String description,
                   double price, double actualStock, double minimalStock) {

        this.name = verifyString(name);
        this.supplier = verifyString(supplier);
        this.description = verifyString(description);
        this.price = verifyPrice(price);
        this.actualStock = verifyStock(actualStock);
        this.minimalStock = verifyStock(minimalStock);

        this.id = idProducts;
        idProducts += 1;
    }

    /**
     * Creates a product with zero initial stock.
     *
     * @param name the product name
     * @param supplier the product supplier
     * @param description a description of the product
     * @param price the product price, which must be finite and greater than zero
     * @param minimalStock the minimum stock level, which must be finite and non-negative
     * @throws IllegalArgumentException if a string is null or blank,
     * or if a numeric value violates its validation rules
     */
    public Product(String name, String supplier, String description,
                   double price, double minimalStock) {
        this(name, supplier, description, price, 0, minimalStock);
    }

    /**
     * Validates a string value.
     *
     * @param stringToBeVerified the string to validate
     * @return the validated string
     * @throws IllegalArgumentException if the string is null or blank
     */
    private String verifyString(String stringToBeVerified) {
        if (stringToBeVerified == null || stringToBeVerified.isBlank()) {
            throw new IllegalArgumentException("String cannot be null or blank");
        }

        return stringToBeVerified;
    }

    /**
     * Validates a product price.
     *
     * @param priceToBeVerified the price to validate
     * @return the validated price
     * @throws IllegalArgumentException if the price is not finite
     * or is less than or equal to zero
     */
    private double verifyPrice(double priceToBeVerified) {
        if (!Double.isFinite(priceToBeVerified) || priceToBeVerified <= 0) {
            throw new IllegalArgumentException("Price must be finite and greater than zero");
        }

        return priceToBeVerified;
    }

    /**
     * Validates a stock quantity or minimum stock level.
     *
     * @param stockToBeVerified the stock value to validate
     * @return the validated stock value
     * @throws IllegalArgumentException if the value is not finite or is negative
     */
    private double verifyStock(double stockToBeVerified) {
        if (!Double.isFinite(stockToBeVerified) || stockToBeVerified < 0) {
            throw new IllegalArgumentException("Stock must be finite and non-negative");
        }

        return stockToBeVerified;
    }

    /**
     * Returns the product ID.
     *
     * @return the product ID
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the product name.
     *
     * @return the product name
     */
    public String getName() {
        return name;
    }

    /**
     * Updates the product name.
     *
     * @param name the new product name
     * @throws IllegalArgumentException if the name is null or blank
     */
    public void setName(String name) {
        this.name = verifyString(name);
    }

    /**
     * Returns the product supplier.
     *
     * @return the product supplier
     */
    public String getSupplier() {
        return supplier;
    }

    /**
     * Updates the product supplier.
     *
     * @param supplier the new product supplier
     * @throws IllegalArgumentException if the supplier is null or blank
     */
    public void setSupplier(String supplier) {
        this.supplier = verifyString(supplier);
    }

    /**
     * Returns the product description.
     *
     * @return the product description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Updates the product description.
     *
     * @param description the new product description
     * @throws IllegalArgumentException if the description is null or blank
     */
    public void setDescription(String description) {
        this.description = verifyString(description);
    }

    /**
     * Returns the product price.
     *
     * @return the product price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Updates the product price.
     *
     * @param price the new product price, which must be finite and greater than zero
     * @throws IllegalArgumentException if the price is not finite
     * or is less than or equal to zero
     */
    public void setPrice(double price) {
        this.price = verifyPrice(price);
    }

    /**
     * Returns the current stock quantity.
     *
     * @return the current stock quantity
     */
    public double getActualStock() {
        return actualStock;
    }

    /**
     * Updates the current stock quantity.
     *
     * @param actualStock the new stock quantity, which must be finite and non-negative
     * @throws IllegalArgumentException if the value is not finite or is negative
     */
    public void setActualStock(double actualStock) {
        this.actualStock = verifyStock(actualStock);
    }

    /**
     * Returns the minimum stock level.
     *
     * @return the minimum stock level
     */
    public double getMinimalStock() {
        return minimalStock;
    }

    /**
     * Updates the minimum stock level.
     *
     * @param minimalStock the new minimum stock level, which must be finite and non-negative
     * @throws IllegalArgumentException if the value is not finite or is negative
     */
    public void setMinimalStock(double minimalStock) {
        this.minimalStock = verifyStock(minimalStock);
    }

    /**
     * Returns a string representation of the product, including
     * its ID, name, description, supplier, price, current stock,
     * and minimum stock level.
     *
     * @return a formatted string containing the product information
     */
    @Override
    public String toString() {
        return "Product: " + id
                + " | Name: " + name
                + " | Description: " + description
                + " | Supplier: " + supplier
                + " | Price: " + price
                + " | Actual Stock: " + actualStock
                + " | Minimal Stock: " + minimalStock;
    }
}
