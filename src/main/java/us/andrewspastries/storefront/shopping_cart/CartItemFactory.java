package us.andrewspastries.storefront.shopping_cart;

import us.andrewspastries.storefront.catalog.StoreItem;

public class CartItemFactory{
    public static CartItem createCartItem(StoreItem desiredItem, int quantity) {
        return new CartItem(desiredItem, quantity);
    }
}
