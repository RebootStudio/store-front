package us.andrewspastries.storefront.shopping_cart;

import us.andrewspastries.storefront.catalog.StoreItem;

public class ShoppingCart{
    private int itemCount;

    public void addItem(StoreItem desiredItem, int quantity) {
        itemCount = quantity;
    }

    public int getItemCount() {
        return itemCount;
    }
}
