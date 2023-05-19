package com.example.bussinessLogic.validators;

import com.example.model.Product;

public class StockValidator implements Validator<Product> {
    private static final int MIN_STOCK = 0;
    private static final int MAX_STOCK = 100000;

    public void validate(Product c) {

        if (c.getStock() < MIN_STOCK || c.getStock() > MAX_STOCK) {
            throw new IllegalArgumentException("The stock limit is not respected!");
        }
    }
}
