package us.andrewspastries.storefront.catalog;

import java.math.BigDecimal;

public class StoreItem{

    protected static final BigDecimal DEFAULT_PRICE = new BigDecimal("1.00");
    protected static final String DEFAULT_STORE_ITEM_DESCRIPTION = "This is the default store item description.";
    protected static final String DEFAULT_IMAGE_URL = "./images/default.jpg";
    private String itemName;
    private String description = DEFAULT_STORE_ITEM_DESCRIPTION;
    private BigDecimal price = DEFAULT_PRICE;
    private String imageUrl = DEFAULT_IMAGE_URL;

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

    public BigDecimal getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void changePrice(String newPrice) {
        BigDecimal priceChangeCanidate = createPriceChangeCanidate(newPrice);
        if(priceChangeCanidate.floatValue()<0) throw new InvalidPriceException("New price cannot be a negative number.");
        if(priceChangeCanidate.intValue()>=1000000)throw new InvalidPriceException("New price cannot be over $999,999.99");
    }

    private BigDecimal createPriceChangeCanidate(String newPrice) {
        BigDecimal priceChangeCanidate;
        try{
            priceChangeCanidate = new BigDecimal(newPrice);
        }catch (NumberFormatException e){
            throw new InvalidPriceException("New price must be a numeric value.");
        }
        return priceChangeCanidate;
    }

    protected class InvalidPriceException extends RuntimeException{
        private String message;

        public InvalidPriceException(String message) {

            this.message = message;
        }

        @Override
        public String getMessage() {
            return message;
        }
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
