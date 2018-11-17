package us.andrewspastries.storefront.catalog;

public class StoreItemFactory{
    public static StoreItem createNewStoreItem(String itemName) {
        return new StoreItem(itemName);
    }

}
