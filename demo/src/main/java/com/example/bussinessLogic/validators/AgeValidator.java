package com.example.bussinessLogic.validators;

import com.example.model.Client;

public class AgeValidator implements Validator<Client> {
    private static final int MIN_AGE = 12;
    private static final int MAX_AGE = 110;

    public void validate(Client c) {

        if (c.getAge() < MIN_AGE || c.getAge() > MAX_AGE) {
            throw new IllegalArgumentException("The Client Age limit is not respected!");
        }
    }
}
