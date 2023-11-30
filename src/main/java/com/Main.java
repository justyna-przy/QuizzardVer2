package com;

import com.Classes.CurrentUser;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This is the main class for the Quizzard program.
 * It serves as the entry point for the application.
 *
 *
 * Authors: Tom, Justyna
 * Date: November 1, 2023
 */



public class Main extends Application {

    public static Stage stg;

    //current user must be created in main method to be accessed from any class/controller
    private static CurrentUser currentUser;


    public static CurrentUser getCurrentUser() {
        return currentUser;
    }


    public static void setCurrentUser(CurrentUser user) {
        currentUser = user;
    }

    //starting point for the GUI
    @Override
    public void start(Stage stage) throws IOException { //jfx starting point
        stg = stage;
        stage.setResizable(false);

        //this sets the initial scene to the login scene, it is the first thing the user sees
        Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));

        //adding an icon
        Image image = new Image(String.valueOf(getClass().getResource("/images/Qlogo.png")));
        stage.getIcons().add(image);

        stage.setTitle("Quizzard");
        //the set aspect ratio for the quiz cannot be changed
        stage.setScene(new Scene(root, 1024 ,768));
        stage.show();
    }



    public static Stage getStage() {
        return stg;
    }



    public static void main(String[] args) {
        launch(args);

    }

}