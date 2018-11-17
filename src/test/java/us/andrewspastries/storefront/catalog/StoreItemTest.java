package us.andrewspastries.storefront.catalog;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import us.andrewspastries.storefront.TestConstants;
import us.andrewspastries.storefront.catalog.StoreItem.InvalidDescriptionException;

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
    public void newStoreItemObjectsShouldHaveDefaultValuesForDescription() {
        assertEquals(StoreItem.DEFAULT_STORE_ITEM_DESCRIPTION, underTest.getDescription(),
                "New StoreItem object description does not match default value.");
    }

    @Test
    @DisplayName("changeDescription() changes the description of the StoreItem object.")
    public void changeDescription_GivenANewObject_ChangesTheDescriptionToGivenString() {
        underTest.changeDescription("This is the new description.");
        assertEquals("This is the new description.",underTest.getDescription(),
                "StoreItem object description does not match given value.");
    }
    @Test
    @DisplayName("changeDescription() throws an InvalidDescriptionException when passed a null.")
    public void changeDescription_GivenANullString_ThrowsAnException(){
        assertThrows(InvalidDescriptionException.class,
                () -> underTest.changeDescription(null),
                "InvalidDescriptionException not thrown.");
    }
    @Test
    @DisplayName("changeDescription() throws an InvalidDescriptionException when passed a string with 501 or more characters.")
    public void changeDescription_GivenA501CharacterString_ThrowsAnException(){
        assertThrows(InvalidDescriptionException.class,
                ()-> underTest.changeDescription(TEST_DESCRIPTION_500_CHARS+"1"),
                "InvalidDescriptionException not thrown.");
    }
    @Test
    @DisplayName("changeDescription() InvalidDescriptionExceptions throw appropriate error messages.")
    public void changeDescription_GivenInvalidDescriptionStrings_ThrowsExceptionsWithCorrectMessages(){
        assertAll("InvalidDescriptionException error messages:",
                () -> assertInvalidDescriptionExceptionMessage(null,
                        "New description cannot be a null value."),
                () -> assertInvalidDescriptionExceptionMessage(TEST_DESCRIPTION_500_CHARS+"1",
                        "New description cannot exceed 500 characters.")
        );
    }
    @Test
    @DisplayName("StoreItem objects have a default value of '$0.00'.")
    private void assertInvalidDescriptionExceptionMessage(String newDescription, String expectedExceptionMessage) {
        try{
            underTest.changeDescription(newDescription);
            fail("InvalidDescriptionException not thrown.");
        }catch(InvalidDescriptionException e){
            assertEquals(expectedExceptionMessage, e.getMessage());
        }
    }

    private void assertItemName(String itemName) {
        StoreItem testStoreItem = StoreItemFactory.createNewStoreItem(itemName);
        assertEquals(itemName, testStoreItem.getItemName(), "StoreItem names not set properly.");
    }


}
