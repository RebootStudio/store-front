package us.andrewspastries.storefront.catalog;

public class InvalidInputException extends RuntimeException{
    protected String message;

    public InvalidInputException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
