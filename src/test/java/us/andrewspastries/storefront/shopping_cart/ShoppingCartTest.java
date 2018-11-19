package us.andrewspastries.storefront.shopping_cart;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import us.andrewspastries.storefront.catalog.StoreItem;
import us.andrewspastries.storefront.catalog.StoreItemFactory;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShoppingCartTest{
    private ShoppingCart underTest;

    @BeforeEach
    public void setup() {
        underTest = ShoppingCartFactory.createNewShoppingCart();
    }

    @Test
    @DisplayName("addItem() updates item count of new ShoppingCart object.")
    public void addItem_AddItemToANewShoppingCart_ItemCountIsQuantityPassed() {
        assertAll("addItem() quantity tests:",
                () -> assertAddItemQuantity(1),
                () -> assertAddItemQuantity(2)
        );
    }

    private void assertAddItemQuantity(int addedQuantity) {
        StoreItem testItem = StoreItemFactory.createNewStoreItem("Test Item 1");
        underTest.addItem(testItem, addedQuantity);
        assertEquals(addedQuantity, underTest.getItemCount(),
                "ShoppingCart item count does not match expected value.");
    }

}
