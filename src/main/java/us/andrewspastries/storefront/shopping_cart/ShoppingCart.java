package us.andrewspastries.storefront.shopping_cart;

import us.andrewspastries.storefront.catalog.StoreItem;

import java.math.BigDecimal;
import java.util.ArrayList;

public class ShoppingCart{
    private int itemCount;
    private ArrayList<CartItem> cartItems = new ArrayList<>();

    public void addItem(StoreItem desiredItem, int quantity) {
        cartItems.add( CartItemFactory.createCartItem(desiredItem, quantity));
    }

    public int getItemCount() {
        int cartItemCount = 0;
        for (CartItem cartItem : cartItems) {
            cartItemCount += cartItem.getQuantity();
        }
        return cartItemCount;
    }

    public BigDecimal getCartSubtotal() {
        BigDecimal cartSubtotal = BigDecimal.ZERO;
        for (CartItem cartItem : cartItems) {
            cartSubtotal= new BigDecimal(String.valueOf(cartSubtotal.add(cartItem.cartItemSubtotal())));
        }
        return cartSubtotal;
    }

    public void changeCartItemQuantity(StoreItem cartItem, int changedQuantity) {
    }
}