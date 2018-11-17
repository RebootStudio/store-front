package us.andrewspastries.storefront.catalog;

public class StoreItem{

    protected static final String DEFAULT_STORE_ITEM_DESCRIPTION = "This is the default store item description.";
    private String itemName;
    private String description = DEFAULT_STORE_ITEM_DESCRIPTION;

    public StoreItem(String itemName) {

        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    public String getDescription() {
        return description;
    }

    public void changeDescription(String newDescription) {
        checkForInvalidDescription(newDescription);
        description = newDescription;
    }

    private void checkForInvalidDescription(String newDescription) {
        if (newDescription == null) throw new InvalidDescriptionException("New description cannot be a null value.");
        if (newDescription.length() > 500)
            throw new InvalidDescriptionException("New description cannot exceed 500 characters.");
    }

    protected class InvalidDescriptionException extends RuntimeException{

        private String message;

        public InvalidDescriptionException(String message) {

            this.message = message;
        }

        @Override
        public String getMessage() {
            return message;
        }
    }
}
