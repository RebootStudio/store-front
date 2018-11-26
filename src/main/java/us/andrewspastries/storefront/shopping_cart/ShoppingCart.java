package us.andrewspastries.storefront.shopping_cart;

import us.andrewspastries.storefront.catalog.StoreItem;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class ShoppingCart{
    private int itemCount;
    private ArrayList<CartItem> cartItems = new ArrayList<>();



    public void addItem(StoreItem desiredItem, int quantity) {
        cartItems.add(CartItemFactory.createCartItem(desiredItem, quantity));
    }

    public int getItemCount() {
        return cartItems.stream()
                        .mapToInt(CartItem::getQuantity)
                        .sum();
    }

    public BigDecimal getCartSubtotal() {
        return BigDecimal.valueOf(
                cartItems.stream()
                         .mapToDouble(CartItem::cartItemSubtotalAsDouble)
                         .sum())
                         .setScale(2, RoundingMode.HALF_UP);
    }

    public void changeCartItemQuantity(StoreItem cartItem, int changedQuantity) {
    }
}