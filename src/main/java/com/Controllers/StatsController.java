package com.Controllers;

import com.Classes.CurrentUser;
import com.Classes.Stats;
import com.Main;
import com.Services.SceneChanger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.List;

public class StatsController {

    @FXML
    private Label userMean, userMedian, userSD, yourMean, yourMedian, yourSD, yourStats;


    public void goHome() throws IOException {
        SceneChanger.changeScene("/home.fxml");
    }


    public void goToStats() throws IOException {
        SceneChanger.changeScene("/stats.fxml");
    }


    public void exitApp(){

    }



    private CurrentUser currentUser;

    public void setCurrentUser(CurrentUser currentUser) {
        this.currentUser = currentUser;
    }



    public void initializeStats() {
        Stats quizStats = new Stats("src/main/resources/stats.csv");
        quizStats.loadCSVScores("src/main/resources/stats.csv");
        yourStats.setText("Your Statistics, "+ Main.getCurrentUser().getUsername() + "!");
        userMean.setText(String.valueOf(quizStats.calcUserMean()));
        userMedian.setText(String.valueOf(quizStats.calcUserMedian()));
        userSD.setText(String.valueOf(quizStats.calcUserSD()));

        List<Double> yourScores = quizStats.findYourScore(Main.getCurrentUser().getUsername(), "src/main/resources/stats.csv");
        yourMean.setText(String.valueOf(quizStats.findYourMean(yourScores)));
        yourMedian.setText(String.valueOf(quizStats.findYourMedian(yourScores)));
        yourSD.setText(String.valueOf(quizStats.findYourSD(yourScores)));

    }


}
