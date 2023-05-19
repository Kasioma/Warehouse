package com.example.bussinessLogic.validators;

import java.util.regex.Pattern;

import com.example.model.Client;

public class NameValidator implements Validator<Client> {
    private static final String NAME_PATTERN = "^[A-Za-z\\s]+$";

    @Override
    public void validate(Client c) {
        Pattern pattern = Pattern.compile(NAME_PATTERN);
        if (!pattern.matcher(c.getName()).matches()) {
            throw new IllegalArgumentException("is not a valid name");
        }

    }
}
