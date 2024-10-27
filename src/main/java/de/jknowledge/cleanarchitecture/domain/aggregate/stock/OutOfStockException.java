package de.jknowledge.cleanarchitecture.domain.aggregate.stock;

public class OutOfStockException extends RuntimeException {

    public OutOfStockException(String message) {
        super(message);
    }
}
