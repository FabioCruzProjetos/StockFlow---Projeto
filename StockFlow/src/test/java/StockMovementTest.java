import com.model.MovementType;
import com.model.StockMovement;
import com.model.exception.InvalidIDException;
import com.model.exception.InvalidMovementTypeException;
import com.model.exception.InvalidQuantityException;
import org.junit.Test;

import java.time.LocalDateTime;

import static java.lang.Double.*;
import static org.junit.Assert.*;

public class StockMovementTest {

    @Test
    public void validStockMovementCreation(){

        StockMovement stockMovement = new StockMovement(1,10,MovementType.ADD);

        assertEquals(1,stockMovement.getProductId());
        assertEquals(10,stockMovement.getQuantity(), 0);
        assertEquals(MovementType.ADD, stockMovement.getMovementType());
        assertTrue(stockMovement.getMovementId() > 0);

    }

    @Test
    public void differentStockMovementsHaveDifferentIDs(){

        StockMovement stockMovement = new StockMovement(1,10,MovementType.ADD);
        StockMovement stockMovement2 = new StockMovement(1,10,MovementType.REMOVE);

        assertNotEquals(stockMovement.getMovementId(), stockMovement2.getMovementId());

    }

    @Test
    public void consecutiveMovementsHaveConsecutiveIDsAfterFailingOne(){


        StockMovement stockMovement = new StockMovement(1,10,MovementType.ADD);

        assertThrows(InvalidIDException.class, () -> {
            new StockMovement(-12,10,MovementType.ADD);
        });

        StockMovement stockMovement2 = new StockMovement(1,10,MovementType.REMOVE);

        assertEquals(stockMovement.getMovementId() + 1, stockMovement2.getMovementId());
    }

    @Test
    public void negativeProductIdsAreRejected(){

        assertThrows(InvalidIDException.class, () -> {
            new StockMovement(-1,10,MovementType.ADD);
        });

    }

    @Test
    public void zeroProductIdIsRejected(){

        assertThrows(InvalidIDException.class, () -> {
            new StockMovement(0,10,MovementType.ADD);
        });

    }

    @Test
    public void negativeQuantityIsRejected(){

        assertThrows(InvalidQuantityException.class, () -> {
            new StockMovement(1,-10,MovementType.ADD);
        });

    }

    @Test
    public void zeroQuantityisRejected(){

        assertThrows(InvalidQuantityException.class, () -> {
            new StockMovement(1,0,MovementType.ADD);
        });

    }

    @Test
    public void positiveInfiniteQuantityIsRejected(){

        assertThrows(InvalidQuantityException.class, () -> {
            new StockMovement(1,POSITIVE_INFINITY,MovementType.ADD);
        });

    }

    @Test
    public void negativeInfiniteQuantityIsRejected(){

        assertThrows(InvalidQuantityException.class, () -> {
           new StockMovement(1,NEGATIVE_INFINITY,MovementType.ADD);
        });

    }

    @Test
    public void nanQuantityisRejected(){

        assertThrows(InvalidQuantityException.class, () -> {
            new StockMovement(1,NaN,MovementType.ADD);
        });

    }

    @Test
    public void nullMovementTypeIsRejected(){

        assertThrows(InvalidMovementTypeException.class, () -> {
            new StockMovement(1,1,null);
        });

    }

    @Test
    public void dateIsNotNull() {

        LocalDateTime before = LocalDateTime.now();

        StockMovement stockMovement = new StockMovement(1, 1, MovementType.ADD);

        LocalDateTime after = LocalDateTime.now();

        assertNotNull(stockMovement.getDate());
        assertFalse(stockMovement.getDate().isBefore(before));
        assertFalse(stockMovement.getDate().isAfter(after));
    }
}
