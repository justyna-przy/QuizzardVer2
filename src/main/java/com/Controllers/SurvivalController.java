package com.Controllers;

import com.QuizGameLogic.*;
import com.Services.SceneChanger;
import com.Services.ScoreHandler;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;
import java.io.IOException;




public class SurvivalController extends QuizController{


    @FXML
    private Label timer;


    private Timeline timeline;
    private final int totalCentiseconds = 30 * 100; // 30 seconds
    private int centisecondsRemaining = totalCentiseconds;




    // Flag to check if the score has been set

    private void startTimer() {

        timeline = new Timeline(new KeyFrame(Duration.millis(10), event -> {

            if (centisecondsRemaining > 1) {
                int seconds = centisecondsRemaining / 100;
                int centiseconds = centisecondsRemaining % 100;
                String timerText = String.format("%02d:%02d", seconds, centiseconds);
                timer.setText(timerText);
                centisecondsRemaining--;
            } else {
                // timer expired, handle quiz finish
                timeline.stop();

                try {
                    scorePage();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            }
        }));

        timeline.setCycleCount(totalCentiseconds);
        timeline.play();
    }
    QuizModel quizMode;

    @FXML
    public void initial() {
        quizMode = new TimerModel();
        super.loadQuestion(quizMode);
        startTimer();

    }


    @FXML
    private void onNextButtonClicked() throws IOException {
        if (userChoice == null) {
            return;
        } else if (userChoice.equals(currentQuestion.getCorrectAnswer())) {
            score.incSurvScore();
            userChoice = null;
            questionNum++;
            super.loadQuestion(quizMode);
        } else {

            timeline.stop();
            scorePage();

        }
    }


    protected void scorePage() throws IOException {

        ScoreHandler.setSurvScore(score.getSurvScore());
        SceneChanger.changeScene("/survboard.fxml");

    }


}
