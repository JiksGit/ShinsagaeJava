package com.sinse.electroshop.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String msg) {
        super(msg);
    }
    public ProductNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
    public ProductNotFoundException(Throwable cause) {
        super(cause);
    }
}
