package com.example;

import java.sql.SQLException;

import com.example.presentation.Controller;
import com.example.presentation.View;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws SQLException {
        View view = new View();
        new Controller(view);
    }
}
