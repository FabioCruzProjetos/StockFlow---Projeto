
import com.model.Product;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductTest {

    @Test
    public void creatingValidProduct() {
        String name = "Bacalhau";
        String description = "Bacalhau Posta 400-600";
        String supplier = "Ibergloria";
        double price = 16.5;
        double actualStock = 10;
        double minimalStock = 3;

        Product product = new Product(
                name, supplier, description,
                price, actualStock, minimalStock
        );

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
        String name = "Bacalhau";
        String description = "Bacalhau Posta 400-600";
        String supplier = "Ibergloria";
        double price = 16.5;
        double minimalStock = 3;

        Product product = new Product(
                name, supplier, description, price, minimalStock
        );

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
        Product firstProduct = new Product(
                "Bacalhau", "Ibergloria", "Posta", 16.5, 10, 3
        );

        Product secondProduct = new Product(
                "Salmão", "Fornecedor B", "Lombo", 12.5, 5, 2
        );

        assertNotEquals(firstProduct.getId(), secondProduct.getId());
    }

    @Test
    public void creatingProductWithNullName() {
        assertThrows(IllegalArgumentException.class, () ->
                new Product(null, "Ibergloria", "Bacalhau", 15, 12, 7)
        );
    }

    @Test
    public void creatingProductWithEmptyName() {
        assertThrows(IllegalArgumentException.class, () ->
                new Product("", "Ibergloria", "Bacalhau", 15, 12, 7)
        );
    }

    @Test
    public void creatingProductWithBlankName() {
        assertThrows(IllegalArgumentException.class, () ->
                new Product("   ", "Ibergloria", "Bacalhau", 15, 12, 7)
        );
    }

    @Test
    public void creatingProductWithNullSupplier() {
        assertThrows(IllegalArgumentException.class, () ->
                new Product("Bacalhau", null, "Bacalhau", 15, 12, 7)
        );
    }

    @Test
    public void creatingProductWithEmptySupplier() {
        assertThrows(IllegalArgumentException.class, () ->
                new Product("Bacalhau", "", "Bacalhau", 15, 12, 7)
        );
    }

    @Test
    public void creatingProductWithNullDescription() {
        assertThrows(IllegalArgumentException.class, () ->
                new Product("Bacalhau", "Ibergloria", null, 15, 12, 7)
        );
    }

    @Test
    public void creatingProductWithEmptyDescription() {
        assertThrows(IllegalArgumentException.class, () ->
                new Product("Bacalhau", "Ibergloria", "", 15, 12, 7)
        );
    }

    @Test
    public void creatingProductWithNegativePrice() {
        assertThrows(IllegalArgumentException.class, () ->
                new Product("Bacalhau", "Ibergloria", "Posta", -15, 12, 7)
        );
    }

    @Test
    public void creatingProductWithZeroPrice() {
        assertThrows(IllegalArgumentException.class, () ->
                new Product("Bacalhau", "Ibergloria", "Posta", 0, 12, 7)
        );
    }

    @Test
    public void creatingProductWithNaNPrice() {
        assertThrows(IllegalArgumentException.class, () ->
                new Product("Bacalhau", "Ibergloria", "Posta",
                        Double.NaN, 12, 7)
        );
    }

    @Test
    public void creatingProductWithInfinitePrice() {
        assertThrows(IllegalArgumentException.class, () ->
                new Product("Bacalhau", "Ibergloria", "Posta",
                        Double.POSITIVE_INFINITY, 12, 7)
        );
    }

    @Test
    public void creatingProductWithNegativeActualStock() {
        assertThrows(IllegalArgumentException.class, () ->
                new Product("Bacalhau", "Ibergloria", "Posta", 15, -12, 7)
        );
    }

    @Test
    public void creatingProductWithNaNActualStock() {
        assertThrows(IllegalArgumentException.class, () ->
                new Product("Bacalhau", "Ibergloria", "Posta",
                        15, Double.NaN, 7)
        );
    }

    @Test
    public void creatingProductWithPositiveInfiniteActualStock() {
        assertThrows(IllegalArgumentException.class, () ->
                new Product("Bacalhau", "Ibergloria", "Posta",
                        15, Double.POSITIVE_INFINITY, 7)
        );
    }

    @Test
    public void creatingProductWithNegativeInfiniteActualStock() {
        assertThrows(IllegalArgumentException.class, () ->
                new Product("Bacalhau", "Ibergloria", "Posta",
                        15, Double.NEGATIVE_INFINITY, 7)
        );
    }

    @Test
    public void creatingProductWithNegativeMinimalStock() {
        assertThrows(IllegalArgumentException.class, () ->
                new Product("Bacalhau", "Ibergloria", "Posta", 15, 12, -7)
        );
    }

    @Test
    public void creatingProductWithNaNMinimalStock() {
        assertThrows(IllegalArgumentException.class, () ->
                new Product("Bacalhau", "Ibergloria", "Posta",
                        15, 12, Double.NaN)
        );
    }

    @Test
    public void creatingProductWithPositiveInfiniteMinimalStock() {
        assertThrows(IllegalArgumentException.class, () ->
                new Product("Bacalhau", "Ibergloria", "Posta",
                        15, 12, Double.POSITIVE_INFINITY)
        );
    }

    @Test
    public void creatingProductWithNegativeInfiniteMinimalStock() {
        assertThrows(IllegalArgumentException.class, () ->
                new Product("Bacalhau", "Ibergloria", "Posta",
                        15, 12, Double.NEGATIVE_INFINITY)
        );
    }

    @Test
    public void creatingProductWithZeroStockIsValid() {
        Product product = new Product(
                "Bacalhau", "Ibergloria", "Posta", 15, 0, 0
        );

        assertEquals(0, product.getActualStock(), 0.01);
        assertEquals(0, product.getMinimalStock(), 0.01);
    }
}
