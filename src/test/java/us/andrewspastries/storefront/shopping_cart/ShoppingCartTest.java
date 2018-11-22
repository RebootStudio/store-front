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
    private StoreItem testItem1;
    @BeforeEach
    public void setup() {
        underTest = ShoppingCartFactory.createNewShoppingCart();
        testItem1 = StoreItemFactory.createNewStoreItem("Test Item 1");

    }

    @Test
    @DisplayName("addItem() updates item count of new ShoppingCart object.")
    public void addItem_AddItemToANewShoppingCart_ItemCountIsQuantityPassed() {
        assertAll("addItem() quantity tests:",
                () -> assertAddItemQuantity(1),
                () -> assertAddItemQuantity(2)
        );
    }
    @Test
    public void addItem_AddTwoDifferentItems_ItemCountIsAMountOfItemsAdded(){
        StoreItem testItem2 = StoreItemFactory.createNewStoreItem("Test Item 2");
        underTest.addItem(testItem1,1);
        underTest.addItem(testItem2,1);
        assertEquals(2, underTest.getItemCount());
    }

    private void assertAddItemQuantity(int addedQuantity) {
        underTest = ShoppingCartFactory.createNewShoppingCart();
        underTest.addItem(testItem1, addedQuantity);
        assertEquals(addedQuantity, underTest.getItemCount(),
                "ShoppingCart item count does not match expected value.");
    }


}
