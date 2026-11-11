
import com.model.MovementType;
import com.model.StockMovement;
import com.model.exception.InvalidIDException;
import com.model.exception.InvalidMovementTypeException;
import com.model.exception.InvalidQuantityException;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class StockMovementTest {

    // Valid stock movement creation tests

    @Test
    public void creatingValidStockMovement() {
        // Act
        StockMovement stockMovement =
                new StockMovement(1, 10, MovementType.ADD);

        // Assert
        assertEquals(1, stockMovement.getProductId());
        assertEquals(10, stockMovement.getQuantity(), 0);
        assertEquals(MovementType.ADD, stockMovement.getMovementType());
        assertTrue(stockMovement.getMovementId() > 0);
    }

    @Test
    public void differentStockMovementsHaveDifferentIDs() {
        // Arrange & Act
        StockMovement firstMovement =
                new StockMovement(1, 10, MovementType.ADD);

        StockMovement secondMovement =
                new StockMovement(1, 10, MovementType.REMOVE);

        // Assert
        assertNotEquals(
                firstMovement.getMovementId(),
                secondMovement.getMovementId()
        );
    }

    @Test
    public void consecutiveMovementsHaveConsecutiveIDsAfterFailedCreation() {
        // Arrange
        StockMovement firstMovement =
                new StockMovement(1, 10, MovementType.ADD);

        // Act & Assert
        assertThrows(InvalidIDException.class, () ->
                new StockMovement(-12, 10, MovementType.ADD)
        );

        // Act
        StockMovement secondMovement =
                new StockMovement(1, 10, MovementType.REMOVE);

        // Assert
        assertEquals(
                firstMovement.getMovementId() + 1,
                secondMovement.getMovementId()
        );
    }

    // Product ID validation tests

    @Test
    public void creatingStockMovementWithNegativeProductIdThrowsException() {
        assertThrows(InvalidIDException.class, () ->
                new StockMovement(-1, 10, MovementType.ADD)
        );
    }

    @Test
    public void creatingStockMovementWithZeroProductIdThrowsException() {
        assertThrows(InvalidIDException.class, () ->
                new StockMovement(0, 10, MovementType.ADD)
        );
    }

    // Quantity validation tests

    @Test
    public void creatingStockMovementWithNegativeQuantityThrowsException() {
        assertThrows(InvalidQuantityException.class, () ->
                new StockMovement(1, -10, MovementType.ADD)
        );
    }

    @Test
    public void creatingStockMovementWithZeroQuantityThrowsException() {
        assertThrows(InvalidQuantityException.class, () ->
                new StockMovement(1, 0, MovementType.ADD)
        );
    }

    @Test
    public void creatingStockMovementWithPositiveInfiniteQuantityThrowsException() {
        assertThrows(InvalidQuantityException.class, () ->
                new StockMovement(
                        1, Double.POSITIVE_INFINITY, MovementType.ADD
                )
        );
    }

    @Test
    public void creatingStockMovementWithNegativeInfiniteQuantityThrowsException() {
        assertThrows(InvalidQuantityException.class, () ->
                new StockMovement(
                        1, Double.NEGATIVE_INFINITY, MovementType.ADD
                )
        );
    }

    @Test
    public void creatingStockMovementWithNaNQuantityThrowsException() {
        assertThrows(InvalidQuantityException.class, () ->
                new StockMovement(1, Double.NaN, MovementType.ADD)
        );
    }

    // Movement type validation tests

    @Test
    public void creatingStockMovementWithNullMovementTypeThrowsException() {
        assertThrows(InvalidMovementTypeException.class, () ->
                new StockMovement(1, 1, null)
        );
    }

    // Date tests

    @Test
    public void creatingStockMovementAssignsCurrentDate() {
        // Arrange
        LocalDateTime before = LocalDateTime.now();

        // Act
        StockMovement stockMovement =
                new StockMovement(1, 1, MovementType.ADD);

        LocalDateTime after = LocalDateTime.now();

        // Assert
        assertNotNull(stockMovement.getDate());
        assertFalse(stockMovement.getDate().isBefore(before));
        assertFalse(stockMovement.getDate().isAfter(after));
    }
}
