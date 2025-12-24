package com.librarymngtsys;

import com.librarymngtsys.service.DatabaseManager;
import com.librarymngtsys.ui.controller.MainController;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {


        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com.librarymngtsys/view/main.fxml")
        );


        Scene scene = new Scene(loader.load(), 1000,800);

        stage.setTitle("Library Management");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {

        DatabaseManager.Close();
    }




    public static void main(String[] args) {

        launch();


    }


}



