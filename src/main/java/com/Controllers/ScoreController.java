package com.Controllers;

import com.Services.SceneChanger;
import com.Services.ScoreHandler;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class ScoreController {
    @FXML
    private Label score;

    @FXML
    private Label percentage;


    public void goHome() throws IOException {
        SceneChanger.changeScene("/home.fxml");
    }


    public void goToStats() throws IOException {
        SceneChanger.changeScene("/stats.fxml");
    }


    public void exitApp(){

    }






    public void initializeScore(){

        double scoreInt = ScoreHandler.getScore();
        int calculatedPercentage = (int) ((scoreInt/6) * 100);

        score.setText(Integer.toString(ScoreHandler.getScore()) + "/6");
        percentage.setText(String.valueOf("You got " + calculatedPercentage + "%!"));


    }

    public void onNextButtonClicked(ActionEvent event) throws IOException {
        SceneChanger.changeScene("/stats.fxml");
    }
}
