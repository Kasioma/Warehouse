package com.example.bussinessLogic.validators;

import java.util.regex.Pattern;

import com.example.model.Product;

public class ProductNameVal implements Validator<Product> {
    private static final String NAME_PATTERN = "^[A-Za-z\\s]+$";

    @Override
    public void validate(Product c) {
        Pattern pattern = Pattern.compile(NAME_PATTERN);
        if (!pattern.matcher(c.getProductName()).matches()) {
            throw new IllegalArgumentException("is not a valid name");
        }
    }
}
