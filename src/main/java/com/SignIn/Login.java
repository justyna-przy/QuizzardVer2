package com.SignIn;
import java.io.FileReader;
import java.io.BufferedReader;

import com.Classes.CurrentUser;
import com.Main;
import com.Services.SceneChanger;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import java.io.IOException;

public class Login {


    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Label errorMessage;

    CurrentUser currentUser = new CurrentUser();


    public void userLogin() throws IOException {
        checkLogin();
    }

    public void userSignIn() throws IOException {
        SceneChanger.changeScene("/signin.fxml");
    }

    private void checkLogin() throws IOException { //when the login button is pressed the check login method is called

        //checks if username and password exist in database

        boolean matched = false;
        String usernameStr = username.getText(); //takes user inputted text from text box and stores it in String username
        String passwordStr = password.getText();
        if ((!usernameStr.isEmpty()) && (!passwordStr.isEmpty())) { //ensures the username and password field is not blank
            try {

                FileReader fr = new FileReader("src/main/resources/login.txt");
                BufferedReader br = new BufferedReader(fr);
                String line;
                while ((line = br.readLine()) != null) { //reads all the lines up until they become null
                    if (line.equals(usernameStr + "\t" + passwordStr)) {
                        currentUser.setUsername(usernameStr);
                        matched = true;
                        break;
                    }
                }
                fr.close();
            } catch (Exception e) {
                e.printStackTrace();
            }


            if (matched) {
                Main.setCurrentUser(currentUser);
                SceneChanger.changeScene("/home.fxml");


            } else {
                errorMessage.setText("Sorry, Incorrect username or password");
            }


        }

    }
}




