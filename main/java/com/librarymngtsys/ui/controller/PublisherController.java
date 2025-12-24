package com.librarymngtsys.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PublisherController {

    @FXML
    private TextField publisherNameField;

    @FXML
    private TextField countryField;


    public String getPublisherName() {
        return publisherNameField.getText();
    }

    public String getCountry() {
        return countryField.getText();
    }


    public void cleanPublisherTabFields() {
        publisherNameField.clear();
        countryField.clear();

    }
}