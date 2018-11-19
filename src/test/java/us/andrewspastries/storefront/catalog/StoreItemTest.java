package us.andrewspastries.storefront.catalog;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
    @DisplayName("changeDescription() throws InvalidInputException exceptions with appropriate messages.")
    public void changeDescription_GivenInvalidDescriptionStrings_ThrowsExceptionsWithCorrectMessages() {
        assertAll("InvalidInputException error messages:",
                () -> assertInvalidDescriptionThrowsException(null,
                        "New description cannot be a null value."),
                () -> assertInvalidDescriptionThrowsException(TEST_DESCRIPTION_500_CHARS + "1",
                        "New description cannot exceed 500 characters.")
        );
    }

    @Test
    @DisplayName("changePrice() throws InvalidInputException exceptions with appropriate messages.")
    public void changePrice_GivenInvalidPrice_ThrowsExceptionsWithCorrectMessages() {
        assertAll("InvalidInputException error messages:",
                () -> assertInvalidPriceThrowsException("-1.00",
                        "New price cannot be a negative number."),
                () -> assertInvalidPriceThrowsException("1000000000.00",
                        "New price cannot be over $999,999.99"),
                () -> assertInvalidPriceThrowsException("BOB",
                        "New price must be a numeric value.")
        );
    }

    @Test
    @DisplayName("changePrice() changes the price of the StoreItem object.")
    public void changePrice_GivenAValidPrice_ChangesThePrice() {
        underTest.changePrice("1.25");
        assertEquals("1.25", underTest.getPrice().toString(),
                "StoreItem object price does not match given value.");
    }
    @Test
    public void changeImageUrl_GivenNewUrl_ChangesImageUrlToNewUrl(){
        underTest.changeImageUrl("./images/sample.jpg");
        assertEquals("./images/sample.jpg",underTest.getImageUrl(),
                "StoreItem object imageUrl does not match given value.");
    }

    @Test
    @Disabled("TODO: Find a regex for url validation.")
    public void changeImageUrl_GivenAStringThatIsNotAnURL_ThrowsInvalidInputException(){
        RuntimeException exception = assertThrows(InvalidInputException.class,
                ()-> underTest.changeImageUrl("@$`$%"),
                "InvalidInputException not thrown");
        assertEquals("Passed URL contains invalid characters.", exception.getMessage(),
                "Exception did not contain the expected message.");
    }

    private void assertInvalidPriceThrowsException(String newPrice, String expectedMessage) {
        RuntimeException exception = assertThrows(InvalidInputException.class,
                () -> underTest.changePrice(newPrice),
                "InvalidInputException was not thrown.");
        assertEquals(expectedMessage, exception.getMessage(),
                "Exception did not contain the expected message.");
    }

    private void assertInvalidDescriptionThrowsException(String newDescription, String expectedMessage) {
        InvalidInputException exception = assertThrows(InvalidInputException.class,
                ()-> underTest.changeDescription(newDescription),
        "InvalidInputException was not thrown.");
        assertEquals(expectedMessage, exception.getMessage(),
                "Exception did not contain the expected message.");
    }

    private void assertItemName(String itemName) {
        StoreItem testStoreItem = StoreItemFactory.createNewStoreItem(itemName);
        assertEquals(itemName, testStoreItem.getItemName(), "StoreItem names not set properly.");
    }



}
