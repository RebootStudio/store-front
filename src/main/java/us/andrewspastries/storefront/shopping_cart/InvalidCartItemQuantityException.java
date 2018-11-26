package us.andrewspastries.storefront.shopping_cart;

public class InvalidCartItemQuantityException extends RuntimeException{
    private String message;

    public InvalidCartItemQuantityException(String message) {

        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
