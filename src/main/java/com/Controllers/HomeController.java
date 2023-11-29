package com.Controllers;

import com.Services.SceneChanger;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;


import java.io.IOException;

public class HomeController {

    @FXML
    private Circle circle;


    public void goHome() throws IOException {
        SceneChanger.changeScene("/home.fxml");
    }


    public void goToStats() throws IOException {
        SceneChanger.changeScene("/stats.fxml");
    }


    public void exitApp(){

    }



    public void startRandom() throws IOException {
        SceneChanger.changeScene("/quiz.fxml", "Random", null, null);
    }

    public void startSubject() throws IOException {
        SceneChanger.changeScene("/subject.fxml");

    }
    public void startDifficulty() throws IOException {
        SceneChanger.changeScene("/difficulty.fxml");

    }
    public void startSurvival() throws IOException {

        circle.setFill(Color.web("#5a02b2"));
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1), circle);
        scaleTransition.setToX(80); // Set the final scale factor for the X-axis
        scaleTransition.setToY(80); // Set the final scale factor for the Y-axis
        scaleTransition.play();

        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(event -> {
            try {
                // Move the scene-changing logic here
                SceneChanger.changeScene("/survintro.fxml");
            } catch (IOException e) {
                e.printStackTrace(); // Handle the exception appropriately
            }
        });

        pause.play();

    }
}
