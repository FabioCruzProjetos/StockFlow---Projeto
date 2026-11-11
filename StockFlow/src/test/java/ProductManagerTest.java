
import com.model.Product;
import com.model.exception.*;
import com.service.ProductManager;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ProductManagerTest {

    private final ProductManager productManager = new ProductManager();

    // Constructor tests

    @Test
    public void createProductManagerWithDefaultCapacity() {
        ProductManager productManager2 = new ProductManager();

        assertEquals(
                ProductManager.getMaxProductByDefault(),
                productManager2.getMaxProducts()
        );
    }

    @Test
    public void createProductManagerWithZeroCapacityThrowsException() {
        assertThrows(InvalidQuantityException.class, () -> {
            new ProductManager(0);
        });
    }

    @Test
    public void createProductManagerWithNegativeCapacityThrowsException() {
        assertThrows(InvalidQuantityException.class, () -> {
            new ProductManager(-150);
        });
    }

    @Test
    public void createProductManagerWithCustomCapacity() {
        ProductManager productManager2 = new ProductManager(150);

        assertEquals(150, productManager2.getMaxProducts());
    }

    // Add product tests

    @Test
    public void addValidProduct() {
        Product product = new Product(
                "Bacalhau", "Ibergloria", "Bacalhau Posta 400-600", 16.5, 10
        );

        productManager.addProduct(product);

        assertEquals(product, productManager.getProductByID(product.getId()));
    }

    @Test
    public void addNullProductThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            productManager.addProduct(null);
        });
    }

    @Test
    public void addDuplicateProductThrowsException() {
        Product product = new Product(
                "Bacalhau", "Ibergloria", "Bacalhau Posta 400-600", 16.5, 10
        );

        productManager.addProduct(product);

        assertThrows(IDAlreadyInListException.class, () -> {
            productManager.addProduct(product);
        });

        assertEquals(1, productManager.getSize());
    }

    @Test
    public void addProductWhenCapacityIsFullThrowsException() {
        // Arrange
        ProductManager productManager1 = new ProductManager(1);

        Product product1 = new Product(
                "Bacalhau", "Ibergloria", "Bacalhau Posta 300-600", 16.5, 10
        );

        Product product2 = new Product(
                "Bacalhau", "Ibergloria", "Bacalhau Posta 400-600", 16.5, 10
        );

        productManager1.addProduct(product1);

        // Act & Assert
        assertThrows(FullCapacityException.class, () -> {
            productManager1.addProduct(product2);
        });

        assertEquals(1, productManager1.getSize());

        assertEquals(
                product1.getId(),
                productManager1.getProductByID(product1.getId()).getId()
        );

        assertThrows(ProductDontExistException.class, () -> {
            productManager1.getProductByID(product2.getId());
        });
    }

    // Get products tests

    @Test
    public void getProductsReturnsAllAddedProducts() {
        Product product1 = new Product(
                "Bacalhau", "Ibergloria", "Bacalhau Posta 300-600", 10, 15
        );

        Product product2 = new Product(
                "Bacalhau", "Ibergloria", "Bacalhau Posta 400-600", 15, 10
        );

        productManager.addProduct(product1);
        productManager.addProduct(product2);

        List<Product> list = productManager.getProducts();

        assertEquals(2, list.size());
        assertEquals(product1.getId(), list.get(0).getId());
        assertEquals(product2.getId(), list.get(1).getId());
    }

    @Test
    public void getProductsReturnsIndependentList() {
        Product product1 = new Product(
                "Bacalhau", "Ibergloria", "Bacalhau Posta 300-600", 10, 15
        );

        Product product2 = new Product(
                "Bacalhau", "Ibergloria", "Bacalhau Posta 400-600", 15, 10
        );

        Product product3 = new Product(
                "Bacalhau", "Ibergloria", "Bacalhau Posta 200-600", 12, 12
        );

        productManager.addProduct(product1);
        productManager.addProduct(product2);

        List<Product> list = productManager.getProducts();
        list.add(product3);

        assertEquals(2, productManager.getSize());
        assertNotEquals(list.size(), productManager.getSize());

        assertEquals(
                product1.getId(),
                productManager.getProductByID(product1.getId()).getId()
        );

        assertEquals(
                product2.getId(),
                productManager.getProductByID(product2.getId()).getId()
        );

        assertThrows(ProductDontExistException.class, () -> {
            productManager.getProductByID(product3.getId());
        });
    }

    @Test
    public void getProductsReturnsReferencesToOriginalProducts() {
        // Arrange
        Product product = new Product(
                "Bacalhau",
                "Ibergloria",
                "Bacalhau Posta 400-600",
                16.5,
                10
        );

        productManager.addProduct(product);

        // Act
        List<Product> products = productManager.getProducts();
        products.get(0).setPrice(20);

        // Assert
        assertEquals(
                20,
                productManager.getProductByID(product.getId()).getPrice(),
                0.001
        );
    }

    // Get product by ID tests

    @Test
    public void getProductByIdReturnsProduct() {
        Product product1 = new Product(
                "Bacalhau", "Ibergloria", "Bacalhau Posta 300-600", 10, 15
        );

        productManager.addProduct(product1);

        assertEquals(
                product1.getId(),
                productManager.getProductByID(product1.getId()).getId()
        );
    }

    @Test
    public void getProductByIdThrowsExceptionWhenProductDoesNotExist() {
        Product product1 = new Product(
                "Bacalhau", "Ibergloria", "Bacalhau Posta 300-600", 10, 15
        );

        assertThrows(ProductDontExistException.class, () -> {
            productManager.getProductByID(product1.getId());
        });
    }

    @Test
    public void getProductByIdWithZeroThrowsException() {
        assertThrows(InvalidIDException.class, () -> {
            productManager.getProductByID(0);
        });
    }

    @Test
    public void getProductByIdWithNegativeIdThrowsException() {
        assertThrows(InvalidIDException.class, () -> {
            productManager.getProductByID(-12);
        });
    }

    // Remove product tests

    @Test
    public void removeProductByIdWithZeroThrowsException() {
        assertThrows(InvalidIDException.class, () -> {
            productManager.removeProductByID(0);
        });
    }

    @Test
    public void removeProductByIdWithNegativeIdThrowsException() {
        assertThrows(InvalidIDException.class, () -> {
            productManager.removeProductByID(-12);
        });
    }

    @Test
    public void removeProductByIdReturnsTrueWhenProductExists() {
        Product product1 = new Product(
                "Bacalhau", "Ibergloria", "Bacalhau Posta 300-600", 10, 15
        );

        productManager.addProduct(product1);

        assertTrue(productManager.removeProductByID(product1.getId()));

        assertThrows(ProductDontExistException.class, () -> {
            productManager.getProductByID(product1.getId());
        });

        assertEquals(0, productManager.getSize());
    }

    @Test
    public void removeProductByIdReturnsFalseWhenProductDoesNotExist() {
        Product product1 = new Product(
                "Bacalhau", "Ibergloria", "Bacalhau Posta 300-600", 10, 15
        );

        assertFalse(productManager.removeProductByID(product1.getId()));
    }

    @Test
    public void removeNonExistentProductDoesNotChangeList() {
        Product product1 = new Product(
                "Bacalhau", "Ibergloria", "Bacalhau Posta 300-600", 10, 15
        );

        Product product2 = new Product(
                "Bacalhau", "Ibergloria", "Bacalhau Posta 300-600", 10, 15
        );

        productManager.addProduct(product1);

        assertFalse(productManager.removeProductByID(product2.getId()));
        assertEquals(1, productManager.getSize());

        assertEquals(
                product1.getId(),
                productManager.getProductByID(product1.getId()).getId()
        );
    }
}
