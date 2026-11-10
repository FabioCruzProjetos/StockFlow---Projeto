
package com.service;

import com.model.Product;
import com.model.exception.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Manages the collection of products in the stock management system.
 * Provides functionality to add, remove, retrieve, and manage products.
 *
 * The default maximum capacity is 200 products, but a custom capacity
 * can be specified when creating a ProductManager instance.
 *
 * @author Fabio Cruz
 */
public class ProductManager {

    private static final int maxProductByDefault = 200;

    private final List<Product> productsList;
    private final int maxProducts;

    /**
     * Creates a ProductManager with the default maximum capacity.
     */
    public ProductManager() {
        this(maxProductByDefault);
    }

    /**
     * Creates a ProductManager with a custom maximum capacity.
     *
     * @param maxProducts the maximum number of products the collection can hold
     * @throws InvalidQuantityException if the specified capacity is less than or equal to zero
     */
    public ProductManager(int maxProducts) {

        this.productsList = new ArrayList<>();
        this.maxProducts = verifyMaxProducts(maxProducts);
    }

    /**
     * Validates the maximum product capacity.
     *
     * @param maxToBeVerified the capacity value to validate
     * @return the validated capacity
     * @throws InvalidQuantityException if the specified capacity is less than or equal to zero
     */
    private int verifyMaxProducts(int maxToBeVerified) {

        if (maxToBeVerified <= 0) {
            throw new InvalidQuantityException("Invalid Value");
        }

        return maxToBeVerified;
    }

    /**
     * Adds a product to the collection.
     * Checks whether the product is valid, whether the collection
     * has reached its maximum capacity, and whether the product ID
     * already exists in the collection.
     *
     * @param product the product to add
     * @throws IllegalArgumentException if the product is null
     * @throws FullCapacityException if the collection has reached its maximum capacity
     * @throws IDAlreadyInListException if a product with the same ID already exists
     */
    public void addProduct(Product product) {

        if (product == null) {
            throw new IllegalArgumentException("Invalid Product");
        }

        if (productsList.size() >= maxProducts) {
            throw new FullCapacityException("Full Capacity");
        }

        if (haveIDInList(product.getId())) {
            throw new IDAlreadyInListException("Already Have this ID");
        }

        productsList.add(product);
    }

    /**
     * Removes a product from the collection using its ID.
     *
     * @param id the ID of the product to remove
     * @return true if the product was found and removed, false otherwise
     * @throws InvalidIDException if the ID is less than or equal to zero
     */
    public boolean removeProductByID(int id) {

        validateID(id);

        Iterator<Product> i = productsList.iterator();

        while (i.hasNext()) {
            if (i.next().getId() == id) {
                i.remove();
                return true;
            }
        }

        return false;
    }

    /**
     * Returns all products in the collection.
     * The returned list is a copy of the internal collection.
     *
     * @return a new list containing all products
     */
    public List<Product> getProducts() {
        return new ArrayList<>(productsList);
    }

    /**
     * Retrieves a product from the collection using its ID.
     *
     * @param id the ID of the product to retrieve
     * @return the product associated with the specified ID
     * @throws InvalidIDException if the ID is less than or equal to zero
     * @throws ProductDontExistException if no product with the specified ID exists
     */
    public Product getProductByID(int id) {

        validateID(id);

        for (Product product : productsList) {
            if (product.getId() == id) {
                return product;
            }
        }

        throw new ProductDontExistException("Product dont Exist");
    }

    /**
     * Checks whether a product ID already exists in the collection.
     *
     * @param id the product ID to search for
     * @return true if the ID exists in the collection, false otherwise
     * @throws InvalidIDException if the ID is less than or equal to zero
     */
    private boolean haveIDInList(int id) {

        validateID(id);

        for (Product product : productsList) {
            if (product.getId() == id) {
                return true;
            }
        }

        return false;
    }

    /**
     * Validates a product ID.
     *
     * @param id the ID to validate
     * @throws InvalidIDException if the ID is less than or equal to zero
     */
    private void validateID(int id) {

        if (id <= 0) {
            throw new InvalidIDException("Invalid ID");
        }

    }

    /**
     * Returns the maximum capacity of the collection.
     *
     * @return the maximum number of products the collection can hold
     */
    public int getMaxProducts() {
        return maxProducts;
    }

    /**
     * Returns the current number of products in the collection.
     *
     * @return the current number of products
     */
    public int getSize() {
        return productsList.size();
    }

    /**
     * Returns the default maximum product capacity.
     *
     * @return the default capacity
     */
    public static int getMaxProductByDefault() {
        return maxProductByDefault;
    }
}
