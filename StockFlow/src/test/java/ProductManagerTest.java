
import com.model.Product;
import com.service.ProductManager;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ProductManagerTest {

    private final ProductManager productManager = new ProductManager();

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
        assertThrows(IllegalArgumentException.class, () -> {
            new ProductManager(0);
        });
    }

    @Test
    public void createProductManagerWithNegativeCapacityThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ProductManager(-150);
        });
    }

    @Test
    public void createProductManagerWithCustomCapacity() {
        ProductManager productManager2 = new ProductManager(150);

        assertEquals(150, productManager2.getMaxProducts());
    }

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

        assertThrows(IllegalArgumentException.class, () -> {
            productManager.addProduct(product);
        });

        assertEquals(1, productManager.getSize());
    }

    @Test
    public void addProductWhenCapacityIsFullThrowsException() {
        ProductManager productManager1 = new ProductManager(1);

        Product product1 = new Product(
                "Bacalhau", "Ibergloria", "Bacalhau Posta 300-600", 16.5, 10
        );
        Product product2 = new Product(
                "Bacalhau", "Ibergloria", "Bacalhau Posta 400-600", 16.5, 10
        );

        productManager1.addProduct(product1);

        assertThrows(IllegalArgumentException.class, () -> {
            productManager1.addProduct(product2);
        });

        assertEquals(1, productManager1.getSize());
        assertEquals(
                product1.getId(),
                productManager1.getProductByID(product1.getId()).getId()
        );
        assertNull(productManager1.getProductByID(product2.getId()));
    }

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
        assertNull(productManager.getProductByID(product3.getId()));
    }

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
    public void getProductByIdReturnsNullWhenProductDoesNotExist() {
        Product product1 = new Product(
                "Bacalhau", "Ibergloria", "Bacalhau Posta 300-600", 10, 15
        );

        assertNull(productManager.getProductByID(product1.getId()));
    }

    @Test
    public void getProductByIdWithZeroThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            productManager.getProductByID(0);
        });
    }

    @Test
    public void getProductByIdWithNegativeIdThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            productManager.getProductByID(-12);
        });
    }

    @Test
    public void removeProductByIdWithZeroThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            productManager.removeProductByID(0);
        });
    }

    @Test
    public void removeProductByIdWithNegativeIdThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
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
        assertNull(productManager.getProductByID(product1.getId()));
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
