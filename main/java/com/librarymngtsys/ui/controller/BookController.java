package com.librarymngtsys.ui.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BookController {

    @FXML private TextField titleField;

    @FXML private TextField genreField;

    @FXML private TextField publishedYearField;




    public String getTitle(){return  titleField.getText();}
    public String getGenre(){return  genreField.getText();}
    public Integer getPublishedYear() {

        String text = publishedYearField.getText();
        return (text == null || text.trim().isEmpty())
                ? null
                : Integer.parseInt(text);

    }


    public void cleanBookTabFields() {
        titleField.clear();
        genreField.clear();
        publishedYearField.clear();
    }


}
