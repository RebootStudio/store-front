package us.andrewspastries.storefront.shopping_cart;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import us.andrewspastries.storefront.catalog.StoreItem;
import us.andrewspastries.storefront.catalog.StoreItemFactory;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShoppingCartTest{
    private ShoppingCart underTest;
    private StoreItem testItem1;
    private StoreItem testItem2;

    @BeforeEach
    public void setup() {
        underTest = ShoppingCartFactory.createNewShoppingCart();
        testItem1 = StoreItemFactory.createNewStoreItem("Test Item 1");
        testItem2 = StoreItemFactory.createNewStoreItem("Test Item 2");
        testItem2.changePrice("2.50");
    }

    @Test
    @DisplayName("addItem() adds items to the ShoppingCart object.")
    public void addItem_GivenItemsWithDifferingQuantities_AddsCartItemsToCart() {
        assertAll("addItem() quantity tests:",
                () -> assertAddItemQuantity(1),
                () -> assertAddItemQuantity(2),
                () -> assertAddItemWithDifferingStoreItems(1, 1),
                () -> assertAddItemWithDifferingStoreItems(24, 43)
        );
    }

    @Test
    @DisplayName("addItem() throws InvalidCartItemQuantityException exception.")
    public void addItem_GivenOneItemWithALessThanOneQuantity_ThrowsException(){
        RuntimeException e = assertThrows(InvalidCartItemQuantityException.class,
                ()-> underTest.addItem(testItem1,0),
                "InvalidCartItemQuantityException was not thrown.");
        assertEquals("CartItem cannot be created with a quantity less than one.", e.getMessage(),
                "Exception did not contain the expected message.");
    }
    @Test
    @DisplayName("getCartSubtotal() calculates the ShoppingCart object's subtotal.")
    public void getCartSubtotal_GivenOneItemWithDifferingQuantities_ReturnsCartSubtotal() {
        assertAll("getCartSubtotal() subtotal tests:",
                () -> assertCartSubtotalForOneItem(1, "1.00"),
                () -> assertCartSubtotalForOneItem(2, "2.00"),
                () -> assertCartSubtotalForTwoItems(1, 1, "3.50"),
                () -> assertCartSubtotalForTwoItems(3, 2, "8.00")
        );

    }


    private void assertCartSubtotalForTwoItems(int item1Quantity, int item2Quantity, String assertedSubtotal) {
        underTest = ShoppingCartFactory.createNewShoppingCart();
        underTest.addItem(testItem1, item1Quantity);
        underTest.addItem(testItem2, item2Quantity);
        assertEquals(new BigDecimal(assertedSubtotal), underTest.getCartSubtotal());
    }

    private void assertAddItemWithDifferingStoreItems(int item1Quantity, int item2Quantity) {
        underTest = ShoppingCartFactory.createNewShoppingCart();
        underTest.addItem(testItem1, item1Quantity);
        underTest.addItem(testItem2, item2Quantity);
        assertEquals(item1Quantity + item2Quantity, underTest.getItemCount(),
                "ShoppingCart with different StoreItems did not add up item count correctly.");
    }

    private void assertCartSubtotalForOneItem(int itemQuantity, String cartSubtotal) {
        underTest = ShoppingCartFactory.createNewShoppingCart();
        underTest.addItem(testItem1, itemQuantity);
        assertEquals(new BigDecimal(cartSubtotal), underTest.getCartSubtotal());
    }

    private void assertAddItemQuantity(int addedQuantity) {
        underTest = ShoppingCartFactory.createNewShoppingCart();
        underTest.addItem(testItem1, addedQuantity);
        assertEquals(addedQuantity, underTest.getItemCount(),
                "ShoppingCart item count does not match expected value.");
    }


}
