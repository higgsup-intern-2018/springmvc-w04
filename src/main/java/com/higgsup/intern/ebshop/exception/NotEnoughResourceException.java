package com.higgsup.intern.ebshop.exception;

public class NotEnoughResourceException extends ServiceException {
    public NotEnoughResourceException() {
        super();
    }
    public NotEnoughResourceException(String message, Throwable cause) {
        super(message, cause);
    }
    public NotEnoughResourceException(String message) {
        super(message);
    }
    public NotEnoughResourceException(Throwable cause) {
        super(cause);
    }
}
