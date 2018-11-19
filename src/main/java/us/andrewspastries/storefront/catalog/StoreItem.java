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

    public BigDecimal getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void changeDescription(String newDescription) {
        checkForInvalidDescription(newDescription);
        description = newDescription;
    }

    public void changePrice(String newPrice) {
        BigDecimal priceChangeCanidate = createPriceChangeCanidate(newPrice);
        checkForInvalidPriceValue(priceChangeCanidate);
        price = priceChangeCanidate;
    }

    public void changeImageUrl(String newUrl) {
        checkForInvalidUrlValue(newUrl);
        imageUrl = newUrl;
    }

    private void checkForInvalidDescription(String newDescription) {
        if (newDescription == null) throw new InvalidInputException("New description cannot be a null value.");
        if (newDescription.length() > 500)
            throw new InvalidInputException("New description cannot exceed 500 characters.");
    }

    private void checkForInvalidPriceValue(BigDecimal priceChangeCanidate) {
        if (priceChangeCanidate.floatValue() < 0)
            throw new InvalidInputException("New price cannot be a negative number.");
        if (priceChangeCanidate.intValue() >= 1000000)
            throw new InvalidInputException("New price cannot be over $999,999.99");
    }

    private BigDecimal createPriceChangeCanidate(String newPrice) {
        BigDecimal priceChangeCanidate;
        try {
            priceChangeCanidate = new BigDecimal(newPrice);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("New price must be a numeric value.");
        }
        return priceChangeCanidate;
    }

    private void checkForInvalidUrlValue(String newUrl) {
        //TODO: Find a regex expression to determine invalid URLs
    }

}
