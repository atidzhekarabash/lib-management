package com.librarymngtsys.ui.controller;
import com.librarymngtsys.entity.Author;
import javafx.scene.control.TextField;

import javafx.fxml.FXML;





public class AuthorController {
    @FXML
    private TextField authorNameField;

    @FXML
    private TextField authorBirthYearField;


    public String getAuthorName() {
        return authorBirthYearField.getText();
    }

    public Integer getAuthorBirthYear() {
        String text = authorBirthYearField.getText();

        return (text == null || text.trim().isEmpty())
                ? null
                : Integer.parseInt(text);
    }


    public void cleanAuthorTabFields() {
        authorNameField.clear();
        authorBirthYearField.clear();
    }

}