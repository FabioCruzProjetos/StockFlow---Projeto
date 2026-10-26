package com.service;

import com.model.Product;
import com.model.exception.FullCapacityException;
import com.model.exception.IDAlreadyInListException;
import com.model.exception.InvalidIDException;
import com.model.exception.ProductDontExistException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProductManager {

    private static final int maxProductByDefault = 200;

    private final List<Product> productsList;
    private final int maxProducts;

    public ProductManager() {
        this(maxProductByDefault);
    }

    public ProductManager(int maxProducts) {

        this.productsList = new ArrayList<>();
        this.maxProducts = verifyMaxProducts(maxProducts);
    }

    private int verifyMaxProducts(int maxToBeVerified) {

        if (maxToBeVerified <= 0) {
            throw new IllegalArgumentException("Invalid Value");
        }

        return maxToBeVerified;
    }

    public void addProduct(Product product){

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

    public List<Product> getProducts(){
        return new ArrayList<>(productsList);
    }

    public Product getProductByID(int id) {

        validateID(id);

        for (Product product : productsList) {
            if (product.getId() == id) {
                return product;
            }
        }

        throw new ProductDontExistException("Product dont Exist");
    }

    private boolean haveIDInList(int id) {

        validateID(id);

        for (Product product : productsList) {
            if (product.getId() == id) {
                return true;
            }
        }

        return false;
    }

    private void validateID(int id) {

        if (id <= 0) {
            throw new InvalidIDException("Invalid ID");
        }

    }

    public int getMaxProducts() {
        return maxProducts;
    }

    public int getSize() {
        return productsList.size();
    }

    public static int getMaxProductByDefault() {
        return maxProductByDefault;
    }
}
