package com.orasi.selenium.exceptions;

import org.openqa.selenium.WebDriver;

import com.orasi.selenium.UIException;

public class PageInitialization extends UIException {
    private static final long serialVersionUID = 3407361723082329697L;

    public PageInitialization(String message, WebDriver driver) {
        super(message, driver);
    }

}
