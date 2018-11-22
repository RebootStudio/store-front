package us.andrewspastries.storefront.shopping_cart;

import us.andrewspastries.storefront.catalog.StoreItem;

import java.util.ArrayList;

public class ShoppingCart{
    private int itemCount;
    private ArrayList<CartItem> cartItems = new ArrayList<>();

    public void addItem(StoreItem desiredItem, int quantity) {
        CartItem shoppingCartAddition = CartItemFactory.createCartItem(desiredItem, quantity);
        cartItems.add(shoppingCartAddition);
    }

    public int getItemCount() {
        int cartItemCount = 0;
        for(CartItem cartItem : cartItems){
            cartItemCount += cartItem.getQuantity();
        }
        return cartItemCount;
    }
}
