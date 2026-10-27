
import com.model.MovementType;
import com.model.Product;
import com.model.StockMovement;
import com.model.exception.InsufficientStockException;
import com.model.exception.InvalidQuantityException;
import com.model.exception.ProductDontExistException;
import com.service.ProductManager;
import com.service.StockManager;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class StockManagerTest {

    @Test
    public void validStockManagerCreation() {
        ProductManager productManager = new ProductManager();
        StockManager stockManager = new StockManager(productManager);

        assertNotNull(stockManager);
    }

    @Test
    public void stockManagerDoesNotAcceptNull() {
        assertThrows(IllegalArgumentException.class, () ->
                new StockManager(null)
        );
    }

    @Test
    public void addValidStock() {
        ProductManager productManager = new ProductManager();
        Product product = new Product(" a ", " a ", " a ", 1, 10, 10);
        StockManager stockManager = new StockManager(productManager);

        productManager.addProduct(product);

        stockManager.addStock(product.getId(), 5);

        assertEquals(15, product.getActualStock(), 0);
        assertEquals(1, stockManager.getStockMovementsHistory().size());

        StockMovement movement =
                stockManager.getStockMovementsHistory().get(0);

        assertEquals(product.getId(), movement.getProductId());
        assertEquals(5, movement.getQuantity(), 0);
        assertEquals(MovementType.ADD, movement.getMovementType());
    }

    @Test
    public void addZeroStockIsRejected() {
        assertInvalidAddQuantity(0);
    }

    @Test
    public void addNegativeStockIsRejected() {
        assertInvalidAddQuantity(-5);
    }

    @Test
    public void addPositiveInfiniteStockIsRejected() {
        assertInvalidAddQuantity(Double.POSITIVE_INFINITY);
    }

    @Test
    public void addNegativeInfiniteStockIsRejected() {
        assertInvalidAddQuantity(Double.NEGATIVE_INFINITY);
    }

    @Test
    public void addNaNStockIsRejected() {
        assertInvalidAddQuantity(Double.NaN);
    }

    @Test
    public void removeValidStock() {
        ProductManager productManager = new ProductManager();
        Product product = new Product(" a ", " a ", " a ", 1, 10, 10);
        StockManager stockManager = new StockManager(productManager);

        productManager.addProduct(product);

        stockManager.removeStock(product.getId(), 5);

        assertEquals(5, product.getActualStock(), 0);
        assertEquals(1, stockManager.getStockMovementsHistory().size());

        StockMovement movement =
                stockManager.getStockMovementsHistory().get(0);

        assertEquals(product.getId(), movement.getProductId());
        assertEquals(5, movement.getQuantity(), 0);
        assertEquals(MovementType.REMOVE, movement.getMovementType());
    }

    @Test
    public void removeZeroStockIsRejected() {
        assertInvalidRemoveQuantity(0);
    }

    @Test
    public void removeNegativeStockIsRejected() {
        assertInvalidRemoveQuantity(-5);
    }

    @Test
    public void removePositiveInfiniteStockIsRejected() {
        assertInvalidRemoveQuantity(Double.POSITIVE_INFINITY);
    }

    @Test
    public void removeNegativeInfiniteStockIsRejected() {
        assertInvalidRemoveQuantity(Double.NEGATIVE_INFINITY);
    }

    @Test
    public void removeNaNStockIsRejected() {
        assertInvalidRemoveQuantity(Double.NaN);
    }

    @Test
    public void removeQuantityGreaterThanActualStockIsRejected() {
        ProductManager productManager = new ProductManager();
        Product product = new Product(" a ", " a ", " a ", 1, 10, 10);
        StockManager stockManager = new StockManager(productManager);

        productManager.addProduct(product);

        assertThrows(InsufficientStockException.class, () ->
                stockManager.removeStock(product.getId(), 20)
        );

        assertEquals(10, product.getActualStock(), 0);
        assertTrue(stockManager.getStockMovementsHistory().isEmpty());
    }

    @Test
    public void addStockToInvalidProductIdIsRejected() {
        ProductManager productManager = new ProductManager();
        StockManager stockManager = new StockManager(productManager);

        assertThrows(ProductDontExistException.class, () ->
                stockManager.addStock(5, 5)
        );

        assertTrue(stockManager.getStockMovementsHistory().isEmpty());
    }

    @Test
    public void removeStockFromInvalidProductIdIsRejected() {
        ProductManager productManager = new ProductManager();
        StockManager stockManager = new StockManager(productManager);

        assertThrows(ProductDontExistException.class, () ->
                stockManager.removeStock(5, 5)
        );

        assertTrue(stockManager.getStockMovementsHistory().isEmpty());
    }

    @Test
    public void getHistoryOfInvalidProductIdIsRejected() {
        ProductManager productManager = new ProductManager();
        StockManager stockManager = new StockManager(productManager);

        assertThrows(ProductDontExistException.class, () ->
                stockManager.getStockMovementsHistoryOfProduct(5)
        );
    }

    @Test
    public void emptyHistoryIsReturnedWhenProductHasNoMovements() {
        ProductManager productManager = new ProductManager();
        Product product = new Product(" a ", " a ", " a ", 1, 10, 10);
        StockManager stockManager = new StockManager(productManager);

        productManager.addProduct(product);

        List<StockMovement> history =
                stockManager.getStockMovementsHistoryOfProduct(product.getId());

        assertNotNull(history);
        assertTrue(history.isEmpty());
    }

    @Test
    public void stockManagerReturnsHistoryInCorrectOrder() {
        ProductManager productManager = new ProductManager();

        Product product1 = new Product(" a ", " a ", " a ", 1, 10, 10);
        Product product2 = new Product(" b ", " b ", " b ", 1, 5, 10);

        StockManager stockManager = new StockManager(productManager);

        productManager.addProduct(product1);
        productManager.addProduct(product2);

        stockManager.addStock(product2.getId(), 10);
        stockManager.removeStock(product1.getId(), 3);
        stockManager.addStock(product1.getId(), 5);

        List<StockMovement> history =
                stockManager.getStockMovementsHistory();

        assertEquals(3, history.size());

        assertEquals(product2.getId(), history.get(0).getProductId());
        assertEquals(10, history.get(0).getQuantity(), 0);
        assertEquals(MovementType.ADD, history.get(0).getMovementType());

        assertEquals(product1.getId(), history.get(1).getProductId());
        assertEquals(3, history.get(1).getQuantity(), 0);
        assertEquals(MovementType.REMOVE, history.get(1).getMovementType());

        assertEquals(product1.getId(), history.get(2).getProductId());
        assertEquals(5, history.get(2).getQuantity(), 0);
        assertEquals(MovementType.ADD, history.get(2).getMovementType());

        assertEquals(15, product2.getActualStock(), 0);
        assertEquals(12, product1.getActualStock(), 0);
    }

    @Test
    public void stockManagerReturnsOnlyHistoryOfRequestedProduct() {
        ProductManager productManager = new ProductManager();

        Product product1 = new Product(" a ", " a ", " a ", 1, 10, 10);
        Product product2 = new Product(" b ", " b ", " b ", 1, 5, 10);

        StockManager stockManager = new StockManager(productManager);

        productManager.addProduct(product1);
        productManager.addProduct(product2);

        stockManager.addStock(product2.getId(), 10);
        stockManager.removeStock(product1.getId(), 3);
        stockManager.addStock(product1.getId(), 5);

        List<StockMovement> history =
                stockManager.getStockMovementsHistoryOfProduct(product1.getId());

        assertEquals(2, history.size());

        for (StockMovement movement : history) {
            assertEquals(product1.getId(), movement.getProductId());
        }

        assertEquals(MovementType.REMOVE, history.get(0).getMovementType());
        assertEquals(MovementType.ADD, history.get(1).getMovementType());
    }

    @Test
    public void modifyingReturnedHistoryDoesNotChangeInternalHistory() {
        ProductManager productManager = new ProductManager();
        Product product = new Product(" a ", " a ", " a ", 1, 10, 10);
        StockManager stockManager = new StockManager(productManager);

        productManager.addProduct(product);
        stockManager.addStock(product.getId(), 5);

        List<StockMovement> returnedHistory =
                stockManager.getStockMovementsHistory();

        returnedHistory.clear();

        assertEquals(1, stockManager.getStockMovementsHistory().size());
    }

    private void assertInvalidAddQuantity(double quantity) {
        ProductManager productManager = new ProductManager();
        Product product = new Product(" a ", " a ", " a ", 1, 10, 10);
        StockManager stockManager = new StockManager(productManager);

        productManager.addProduct(product);

        assertThrows(InvalidQuantityException.class, () ->
                stockManager.addStock(product.getId(), quantity)
        );

        assertEquals(10, product.getActualStock(), 0);
        assertTrue(stockManager.getStockMovementsHistory().isEmpty());
    }

    private void assertInvalidRemoveQuantity(double quantity) {
        ProductManager productManager = new ProductManager();
        Product product = new Product(" a ", " a ", " a ", 1, 10, 10);
        StockManager stockManager = new StockManager(productManager);

        productManager.addProduct(product);

        assertThrows(InvalidQuantityException.class, () ->
                stockManager.removeStock(product.getId(), quantity)
        );

        assertEquals(10, product.getActualStock(), 0);
        assertTrue(stockManager.getStockMovementsHistory().isEmpty());
    }
}
