package us.andrewspastries.storefront.shopping_cart;

import us.andrewspastries.storefront.catalog.StoreItem;

public class CartItem{
    final private StoreItem storeItem;
    private int quantity;

    public CartItem(StoreItem storeItem, int quantity) {

        this.storeItem = storeItem;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }
}
