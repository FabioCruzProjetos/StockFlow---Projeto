
import com.model.Product;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductTest {

    // Valid product creation tests

    @Test
    public void creatingValidProduct() {
        // Arrange
        String name = "Bacalhau";
        String description = "Bacalhau Posta 400-600";
        String supplier = "Ibergloria";
        double price = 16.5;
        double actualStock = 10;
        double minimalStock = 3;

        // Act
        Product product = new Product(
                name, supplier, description,
                price, actualStock, minimalStock
        );

        // Assert
        assertEquals(name, product.getName());
        assertTrue(product.getId() > 0);
        assertEquals(supplier, product.getSupplier());
        assertEquals(description, product.getDescription());
        assertEquals(price, product.getPrice(), 0.01);
        assertEquals(actualStock, product.getActualStock(), 0.01);
        assertEquals(minimalStock, product.getMinimalStock(), 0.01);
    }

    @Test
    public void creatingValidProductWithoutStock() {
        // Arrange
        String name = "Bacalhau";
        String description = "Bacalhau Posta 400-600";
        String supplier = "Ibergloria";
        double price = 16.5;
        double minimalStock = 3;

        // Act
        Product product = new Product(
                name, supplier, description, price, minimalStock
        );

        // Assert
        assertEquals(name, product.getName());
        assertTrue(product.getId() > 0);
        assertEquals(supplier, product.getSupplier());
        assertEquals(description, product.getDescription());
        assertEquals(price, product.getPrice(), 0.01);
        assertEquals(0, product.getActualStock(), 0.01);
        assertEquals(minimalStock, product.getMinimalStock(), 0.01);
    }

    @Test
    public void creatingTwoProductsShouldGenerateDifferentIds() {
        // Arrange & Act
        Product firstProduct = new Product(
                "Bacalhau", "Ibergloria", "Posta", 16.5, 10, 3
        );

        Product secondProduct = new Product(
                "Salmão", "Fornecedor B", "Lombo", 12.5, 5, 2
        );

        // Assert
        assertNotEquals(firstProduct.getId(), secondProduct.getId());
    }

    // Name validation tests

    @Test
    public void creatingProductWithNullNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Product(null, "Ibergloria", "Bacalhau", 15, 12, 7)
        );
    }

    @Test
    public void creatingProductWithEmptyNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Product("", "Ibergloria", "Bacalhau", 15, 12, 7)
        );
    }

    @Test
    public void creatingProductWithBlankNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Product("   ", "Ibergloria", "Bacalhau", 15, 12, 7)
        );
    }

    // Supplier validation tests

    @Test
    public void creatingProductWithNullSupplierThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Product("Bacalhau", null, "Bacalhau", 15, 12, 7)
        );
    }

    @Test
    public void creatingProductWithEmptySupplierThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Product("Bacalhau", "", "Bacalhau", 15, 12, 7)
        );
    }

    @Test
    public void creatingProductWithBlankSupplierThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Product("Bacalhau", "   ", "Bacalhau", 15, 12, 7)
        );
    }

    // Description validation tests

    @Test
    public void creatingProductWithNullDescriptionThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Product("Bacalhau", "Ibergloria", null, 15, 12, 7)
        );
    }

    @Test
    public void creatingProductWithEmptyDescriptionThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Product("Bacalhau", "Ibergloria", "", 15, 12, 7)
        );
    }

    @Test
    public void creatingProductWithBlankDescriptionThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Product("Bacalhau", "Ibergloria", "   ", 15, 12, 7)
        );
    }

    // Price validation tests

    @Test
    public void creatingProductWithNegativePriceThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Product("Bacalhau", "Ibergloria", "Posta", -15, 12, 7)
        );
    }

    @Test
    public void creatingProductWithZeroPriceThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Product("Bacalhau", "Ibergloria", "Posta", 0, 12, 7)
        );
    }

    @Test
    public void creatingProductWithNaNPriceThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Product(
                        "Bacalhau", "Ibergloria", "Posta",
                        Double.NaN, 12, 7
                )
        );
    }

    @Test
    public void creatingProductWithInfinitePriceThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Product(
                        "Bacalhau", "Ibergloria", "Posta",
                        Double.POSITIVE_INFINITY, 12, 7
                )
        );
    }

    // Actual stock validation tests

    @Test
    public void creatingProductWithNegativeActualStockThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Product("Bacalhau", "Ibergloria", "Posta", 15, -12, 7)
        );
    }

    @Test
    public void creatingProductWithNaNActualStockThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Product(
                        "Bacalhau", "Ibergloria", "Posta",
                        15, Double.NaN, 7
                )
        );
    }

    @Test
    public void creatingProductWithPositiveInfiniteActualStockThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Product(
                        "Bacalhau", "Ibergloria", "Posta",
                        15, Double.POSITIVE_INFINITY, 7
                )
        );
    }

    @Test
    public void creatingProductWithNegativeInfiniteActualStockThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Product(
                        "Bacalhau", "Ibergloria", "Posta",
                        15, Double.NEGATIVE_INFINITY, 7
                )
        );
    }

    // Minimum stock validation tests

    @Test
    public void creatingProductWithNegativeMinimalStockThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Product("Bacalhau", "Ibergloria", "Posta", 15, 12, -7)
        );
    }

    @Test
    public void creatingProductWithNaNMinimalStockThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Product(
                        "Bacalhau", "Ibergloria", "Posta",
                        15, 12, Double.NaN
                )
        );
    }

    @Test
    public void creatingProductWithPositiveInfiniteMinimalStockThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Product(
                        "Bacalhau", "Ibergloria", "Posta",
                        15, 12, Double.POSITIVE_INFINITY
                )
        );
    }

    @Test
    public void creatingProductWithNegativeInfiniteMinimalStockThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Product(
                        "Bacalhau", "Ibergloria", "Posta",
                        15, 12, Double.NEGATIVE_INFINITY
                )
        );
    }

    // Zero stock tests

    @Test
    public void creatingProductWithZeroStockIsValid() {
        // Act
        Product product = new Product(
                "Bacalhau", "Ibergloria", "Posta", 15, 0, 0
        );

        // Assert
        assertEquals(0, product.getActualStock(), 0.01);
        assertEquals(0, product.getMinimalStock(), 0.01);
    }
}
