package com.librarymngtsys.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SearchController {
    @FXML private TextField firstFilterCriteria;
    @FXML private TextField secondFilterCriteria;


    public String getFirstFilterCriteria(){return  firstFilterCriteria.getText();}
    public String getSecondFilterCriteria(){return  secondFilterCriteria.getText();}
}
