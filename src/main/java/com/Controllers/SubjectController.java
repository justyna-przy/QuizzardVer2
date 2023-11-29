package com.Controllers;

import com.Services.SceneChanger;
import javafx.application.Platform;
import java.io.IOException;

public class SubjectController {

    public void goHome() throws IOException {
        SceneChanger.changeScene("/home.fxml");
    }


    public void goToStats() throws IOException {
        SceneChanger.changeScene("/stats.fxml");
    }


    public void exitApp(){

    }




    public void selectCompsci() throws IOException {
        SceneChanger.changeScene("/quiz.fxml", "Topic", null,  "Compsci");
    }

    public void selectDMaths() throws IOException {
        SceneChanger.changeScene("/quiz.fxml", "Topic", null,  "Discrete");
    }

    public void selectComporg() throws IOException {
        SceneChanger.changeScene("/quiz.fxml", "Topic", null,  "Comporg");
    }
}
