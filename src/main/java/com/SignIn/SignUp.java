package com.SignIn;

import com.Services.SceneChanger;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SignUp extends JFrame {
    public void signIn() throws IOException{
        checkPassword();
    }


    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField confirmpass;

    @FXML
    private Label errorMessage;





    public void checkPassword() throws IOException{
        String usernm = username.getText();
        String pass = password.getText(); //takes user inputted text from text box and stores it in String username
        String confirm = confirmpass.getText();


        if(pass.equals(confirm)){

            if(passwordChecker(confirm)){

                if (usernameChecker(usernm)){
                    writeSignup(usernm, pass);
                    SceneChanger.changeScene("/login.fxml");
                }else {
                    errorMessage.setText("Username is not correct");
                }

            }else{
                errorMessage.setText("Password is not correct");
            }

        }else{
            errorMessage.setText("Passwords do not match!");
        }
    }



    public boolean passwordChecker(String password){

        //regex string to check password
        /*
            - password must have a minimum length of 8 and maximum of 14
            - password may contain only letters, numbers and underscores
         */

        String regex = "^[a-zA-Z0-9_]{8,14}$";
        Pattern p = Pattern.compile(regex);

        if(password.isEmpty()){
            return false;
        }

        //returns result of matcher
        Matcher m = p.matcher(password);
        return m.matches();


    }

    public boolean usernameChecker(String username){

        String regex = "^[a-zA-Z0-9_]{6,20}$";
        Pattern p = Pattern.compile(regex);

        if(username.isEmpty()){
            return false;
        }

        //returns result of matcher
        Matcher m = p.matcher(username);
        return m.matches();
    }


    public void writeSignup(String username, String password) {


        if ((!username.isEmpty()) && (!password.isEmpty())) {
            try {
                FileWriter fw = new FileWriter("src/main/resources/login.txt", true);
                fw.write("\n" + username + "\t" + password); //typed username and password will be written to login.txt
                // "\n" writes the next data to new line
                fw.close();

                //writing to STATS.CSV
                FileWriter fw2 = new FileWriter("src/main/resources/stats.csv", true);
                fw2.write("\n" + username + ","); //typed username and password will be written to stats.csv

                FileWriter fw3 = new FileWriter("src/main/resources/surv.csv", true);
                fw3.write("\n" + username + ","); //typed username and password will be written to stats.csv

                fw2.close();
                fw3.close();


            } catch (Exception e) {
            }
        }
    }


}
