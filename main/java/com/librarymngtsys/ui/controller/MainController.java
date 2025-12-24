package com.librarymngtsys.ui.controller;

import com.librarymngtsys.entity.Author;
import com.librarymngtsys.entity.Book;
import com.librarymngtsys.entity.Publisher;
import com.librarymngtsys.service.BookService;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javax.swing.*;


public class MainController {

    @FXML
    private TableView<Book> tableView;
    @FXML
    private TableColumn<Book, String> authorNameCol;
    @FXML
    private TableColumn<Book, Integer> authorBirthYearCol;
    @FXML
    private TableColumn<Book, String> publisherNameCol;
    @FXML
    private TableColumn<Book, String> countryCol;
    @FXML
    private TableColumn<Book, String> titleCol;
    @FXML
    private TableColumn<Book, String> genreCol;
    @FXML
    private TableColumn<Book, Integer> publishedYearCol;


    @FXML
    private AuthorController authorViewController;
    @FXML
    private PublisherController publisherViewController;
    @FXML
    private BookController bookViewController;
    @FXML
    private SearchController searchViewController;


    @FXML
    Button deleteButton;
    @FXML
    Button addButton;
    @FXML
    Button updateButton;
    @FXML
    ToggleButton editButton;
    @FXML
    Button searchButton;
    @FXML
    ToggleButton cancelButton;

    private Book currentSelectedBook;

    @FXML
    private TabPane tabPane;
    private boolean editMode = true;


    private final BookService bookService = new BookService();

    final private ObservableList<Book> masterData = FXCollections.observableArrayList();
    private FilteredList<Book> filteredData;


    private Book selectBook;





    enum UIState {
        VIEW,
        SEARCH,
        EDIT,
        SELECT,
        CANCEL
    }

    private UIState uiState;


    @FXML
    public void initialize() {
        initializeTableView();


        hideButtonsOnStart();

        tabPane.getSelectionModel().selectedItemProperty().addListener((obs, o, n) -> {

            if(editMode){
                if("Search".equals(n.getText())){
                    uiState = UIState.SEARCH;
                }else {
                    uiState = UIState.VIEW;
                }
                updateUI();
            }

        });

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

            selectBook = newSelection;
            if(selectBook != null){
                uiState = UIState.SELECT;

                updateUI();
            }


        });

        editButton.selectedProperty().addListener((obs, wasSelected, isSelected) -> {

              if(selectBook != null){
                  editMode = !editMode;
                  uiState = UIState.EDIT;
                  updateUI();
              }
        });


        cancelButton.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
                     if(isSelected){
                         editMode =!editMode;
                         uiState = UIState.CANCEL;
                     }
                  updateUI();
        });



        masterData.addAll(bookService.getAllBooks());
        filteredData = new FilteredList<>(bookService.getAllBooks(), b -> true);
        tableView.setItems(filteredData);

    }





private  void hideButtonsOnStart(){

    setButtonVisibility(false,updateButton);
    setButtonVisibility(false,searchButton);
     setButtonVisibility(false,deleteButton);
     setButtonVisibility(false, editButton);
     setButtonVisibility(false, cancelButton);

}


 private  void updateUI(){

        switch (uiState) {

         case SEARCH:
             setButtonVisibility(true, searchButton);
             setButtonVisibility(false, addButton);
             break;

         case EDIT:
             setButtonVisibility(true, updateButton);
             setButtonVisibility(false, addButton);
             break;
         case SELECT:
             setButtonVisibility(true,deleteButton);
             setButtonVisibility(true, editButton);
             setButtonVisibility(true, cancelButton);

             break;
         case VIEW:
             setButtonVisibility(true, addButton);
             setButtonVisibility(false, searchButton);
             break;

         case CANCEL:
             setButtonVisibility(true,addButton);
             setButtonVisibility(false, updateButton);
             setButtonVisibility(false, deleteButton);
             setButtonVisibility(false, editButton);
             setButtonVisibility(false, cancelButton);
             break;
     }



 }





    private void initializeTableView() {


        authorNameCol.setCellValueFactory(data -> new SimpleStringProperty(
                data.getValue().getAuthor().getName()
        ));
        authorBirthYearCol.setCellValueFactory(data -> new SimpleIntegerProperty(
                data.getValue().getAuthor().getBirthYear()).asObject());


        publisherNameCol.setCellValueFactory(data -> new SimpleStringProperty(
                data.getValue().getPublisher().getName()
        ));
        countryCol.setCellValueFactory(data -> new SimpleStringProperty(
                data.getValue().getPublisher().getCountry()
        ));

        publishedYearCol.setCellValueFactory(data -> new SimpleIntegerProperty(
                data.getValue().getYear()).asObject());
        titleCol.setCellValueFactory(data -> new SimpleStringProperty(
                data.getValue().getTitle()
        ));
        genreCol.setCellValueFactory(data -> new SimpleStringProperty(
                data.getValue().getGenre()
        ));



        tableView.setItems(bookService.getAllBooks());
    }







    @FXML
    public void handleAllSave() {

            String authorName = authorViewController.getAuthorName();
            Integer authorBirthYear = authorViewController.getAuthorBirthYear();


            String publisherName = publisherViewController.getPublisherName();
            String country = publisherViewController.getCountry();


            String title = bookViewController.getTitle();
            String genre = bookViewController.getGenre();
            Integer year = bookViewController.getPublishedYear();

            Author author = new Author();
            Publisher pub = new Publisher();

            author.setName(authorName);
            author.setBirthYear(authorBirthYear);

            pub.setName(publisherName);
            pub.setCountry(country);
            bookService.saveAll(title, genre, year, author, pub);


        initializeTableView();



    }


    @FXML
    public void onDelete() {

        Book selectedBook = tableView.getSelectionModel().getSelectedItem();
        bookService.onDelete(selectedBook.getBookID());

        initializeTableView();

    }




    @FXML private void onEdit() {

        Book selectedBook = tableView.getSelectionModel().getSelectedItem();
        if (selectedBook != null) {
            currentSelectedBook = selectedBook;
        }


    }


    @FXML private void onUpdate() {


        String authorName = authorViewController.getAuthorName();
        if(authorName.isEmpty()){
            authorName = currentSelectedBook.getAuthor().getName();
        }

        Integer authorBirthYear = authorViewController.getAuthorBirthYear();
        if(authorBirthYear == null){
            authorBirthYear = currentSelectedBook.getAuthor().getBirthYear();
        }


        String publisherName = publisherViewController.getPublisherName();
        if(publisherName.isEmpty()){
            publisherName = currentSelectedBook.getPublisher().getName();
        }


        String publisherCountry = publisherViewController.getCountry();
        if(publisherCountry.isEmpty()){
            publisherCountry = currentSelectedBook.getPublisher().getCountry();
        }


        String title = bookViewController.getTitle();
        if(title.isEmpty()){title = currentSelectedBook.getTitle();}

        String genre = bookViewController.getGenre();
        if(genre.isEmpty()){genre = currentSelectedBook.getGenre();}

        Integer year = bookViewController.getPublishedYear();
        if(year == null){year = currentSelectedBook.getYear();}


        bookService.updateBookWithRelations(
                currentSelectedBook.getBookID(),
                title, genre, year,
                authorName, authorBirthYear,
                publisherName, publisherCountry
        );




        resetUpdateFields();
        initializeTableView();
    }




      @FXML  private void onSearch(){

          if (filteredData == null) return;

          String fCriteria = searchViewController.getFirstFilterCriteria().toLowerCase();
          String sCriteria = searchViewController.getSecondFilterCriteria().toLowerCase();


           final String fLower = fCriteria.toLowerCase();

           final String sLower = sCriteria.toLowerCase();



          filteredData.setPredicate(book -> {if(fLower.isEmpty() && sLower.isEmpty()) return true;

                return  book.getGenre().toLowerCase().contains(fLower) &&
                        book.getPublisher().getName().toLowerCase().contains(sLower);
          });

          filteredData.setPredicate(book -> {if(fLower.isEmpty() && sLower.isEmpty()) return true;
              Integer y = book.getYear();
              String publishedY = Integer.toString(y);

              return  book.getAuthor().getName().toLowerCase().contains(fLower) &&
                      publishedY.contains(sLower);



          });





      }



    private void resetUpdateFields() {
        authorViewController.cleanAuthorTabFields();
        publisherViewController.cleanPublisherTabFields();
        bookViewController.cleanBookTabFields();
    }


    private void setButtonVisibility(boolean visibility, Control control) {
        control.visibleProperty().unbind();
        control.setVisible(visibility);
    }

    }







