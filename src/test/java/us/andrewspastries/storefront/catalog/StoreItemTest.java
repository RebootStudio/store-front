package us.andrewspastries.storefront.catalog;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import us.andrewspastries.storefront.catalog.StoreItem.InvalidDescriptionException;
import us.andrewspastries.storefront.catalog.StoreItem.InvalidPriceException;

import static org.junit.jupiter.api.Assertions.*;
import static us.andrewspastries.storefront.TestConstants.*;

@DisplayName("StoreItem unit tests")
public class StoreItemTest{
    private StoreItem underTest;

    @BeforeEach
    public void testMethodSetup() {
        underTest = StoreItemFactory.createNewStoreItem(ITEM_NAME);
    }

    @Test
    @DisplayName("Instantiating StoreItem objects with a given item name.")
    public void shoudInstantiateStoreItemWithAnItemName() {
        assertAll("Testing object creation",
                () -> assertItemName(ITEM_NAME),
                () -> assertItemName("Donut Holes")
        );
    }

    @Test
    @DisplayName("Testing default value of new StoreItem objects.")
    public void newStoreItemObjectsShouldHaveDefaultValues() {
        assertAll("Default values:",
                () -> assertEquals(StoreItem.DEFAULT_STORE_ITEM_DESCRIPTION, underTest.getDescription(),
                        "New StoreItem object description does not match default value."),
                () -> assertEquals(StoreItem.DEFAULT_PRICE, underTest.getPrice(),
                        "New StoreItem object price does not match default value."),
                () -> assertEquals(StoreItem.DEFAULT_IMAGE_URL, underTest.getImageUrl(),
                        "New StoreItem object image URL does not match default value.")
        );
    }

    @Test
    @DisplayName("changeDescription() changes the description of the StoreItem object.")
    public void changeDescription_GivenANewObject_ChangesTheDescriptionToGivenString() {
        underTest.changeDescription("This is the new description.");
        assertEquals("This is the new description.", underTest.getDescription(),
                "StoreItem object description does not match given value.");
    }

    @Test
    @DisplayName("changeDescription() throws InvalidDescriptionException exceptions with appropriate messages.")
    public void changeDescription_GivenInvalidDescriptionStrings_ThrowsExceptionsWithCorrectMessages() {
        assertAll("InvalidDescriptionException error messages:",
                () -> assertInvalidDescriptionExceptionMessage(null,
                        "New description cannot be a null value."),
                () -> assertInvalidDescriptionExceptionMessage(TEST_DESCRIPTION_500_CHARS + "1",
                        "New description cannot exceed 500 characters.")
        );
    }

    @Test
    @DisplayName("changePrice() throws InvalidPriceException exceptions with appropriate messages.")
    public void changePrice_GivenInvalidPrice_ThrowsExceptionsWithCorrectMessages() {
        assertAll("InvalidPriceException error messages:",
                () -> assertInvalidPriceExceptionThrown("-1.00",
                        "New price cannot be a negative number."),
                () -> assertInvalidPriceExceptionThrown("1000000000.00",
                        "New price cannot be over $999,999.99"),
                () -> assertInvalidPriceExceptionThrown("BOB",
                        "New price must be a numeric value.")
        );
    }

    private void assertInvalidPriceExceptionThrown(String newPrice, String expectedMessage) {
        RuntimeException exception = assertThrows(InvalidPriceException.class,
                () -> {
                    underTest.changePrice(newPrice);
                },
                "InvalidPriceException was not thrown.");
        assertEquals(expectedMessage, exception.getMessage(),
                "Exception did not contain the expected message.");
    }

    private void assertInvalidDescriptionExceptionMessage(String newDescription, String expectedExceptionMessage) {
        try {
            underTest.changeDescription(newDescription);
            fail("InvalidDescriptionException not thrown.");
        } catch (InvalidDescriptionException e) {
            assertEquals(expectedExceptionMessage, e.getMessage(), "Exception did not contain the expected message.");
        }
    }

    private void assertItemName(String itemName) {
        StoreItem testStoreItem = StoreItemFactory.createNewStoreItem(itemName);
        assertEquals(itemName, testStoreItem.getItemName(), "StoreItem names not set properly.");
    }


}
