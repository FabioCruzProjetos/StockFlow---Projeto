package com.model;

public class Product {

    private final String id;
    private String name;
    private String supplier;
    private String description;

    private double price;
    private double actualStock;
    private double minimalStock;

    public Product(String id, String name, String supplier,String description, double price, double actualStock, double minimalStock) {

        this.id = verifyString(id);
        this.name = verifyString(name);
        this.supplier = verifyString(supplier);
        this.description = verifyString(description);
        this.price = verifyPrice(price);
        this.actualStock = verifyStock(actualStock);
        this.minimalStock = verifyStock(minimalStock);

    }

    private String verifyString(String stringToBeVerified) {

        if (stringToBeVerified == null) {
            throw new IllegalArgumentException("Text cannot be null");
        }

        if (stringToBeVerified.isBlank()) {
            throw new IllegalArgumentException("Invalid Text");
        }

        return stringToBeVerified;
    }

    private double verifyPrice(double priceToBeVerified) {

        if (Double.isFinite(priceToBeVerified)) {
            throw new IllegalArgumentException("Invalid Value");
        }

        if (priceToBeVerified <= 0) {
            throw new IllegalArgumentException("Invalid Value");
        }

        return priceToBeVerified;
    }

    private double verifyStock(double stockToBeVerified) {

        if (Double.isFinite(stockToBeVerified)) {
            throw new IllegalArgumentException("Invalid Value");
        }

        if (stockToBeVerified < 0) {
            throw new IllegalArgumentException("Invalid Value");
        }

        return stockToBeVerified;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getSupplier() {
        return supplier;
    }

    public double getActualStock() {
        return actualStock;
    }

    public double getMinimalStock() {
        return minimalStock;
    }

    public void setName(String name) {
        this.name = verifyString(name);
    }

    public void setDescription(String description) {
        this.description = verifyString(description);
    }

    public void setPrice(double price) {
        this.price = verifyPrice(price);
    }

    public void setSupplier(String supplier) {
        this.supplier = verifyString(supplier);
    }

    public void setActualStock(double actualStock) {
        this.actualStock = verifyStock(actualStock);
    }

    public void setMinimalStock(double minimalStock) {
        this.minimalStock = verifyStock(minimalStock);
    }

    @Override
    public String toString() {
        return "Product: " + getId() + " " + getName() + " " + getDescription() + " " + getSupplier() + " " + getPrice();
    }
}
