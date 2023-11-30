package com.Controllers;

import com.Classes.*;
import com.QuizGameLogic.*;
import com.Services.SceneChanger;
import com.Services.ScoreHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;




import java.io.IOException;
import java.util.*;

public class QuizController {

    @FXML
    private Label question;

    @FXML
    private Label questionNumLabel;

    @FXML
    public RadioButton opt1, opt2, opt3, opt4;

    @FXML
    public ProgressBar progressBar;

    double progress = 0.0;



    public void goHome() throws IOException {
        SceneChanger.changeScene("/home.fxml");
    }

    public void goToStats() throws IOException {
        SceneChanger.changeScene("/stats.fxml");
    }

    public void exitApp() throws IOException {
        SceneChanger.changeScene("/home.fxml");
    }



    protected QuizModel quizMode;
    protected QuestionData currentQuestion;
    @FXML
    Score score = new Score();
    String userChoice;
    int questionNum = 1;




    @FXML
    public void initialize(String mode, String difficulty, String topic) {
        if ("Random".equals(mode)) {
            quizMode = new RandomModel();
        }else if ("Difficulty".equals(mode)) {
            quizMode = new DifficultModel(difficulty);
        } else if ("Topic".equals(mode)) {
            quizMode = new TopicModel(topic);
        }

        progressBar.setProgress(progress);
        loadQuestion(quizMode);
    }


    @FXML
    private void onNextButtonClicked() {
        if(userChoice == null){
            return;
        }
        else if(userChoice.equals(currentQuestion.getCorrectAnswer())){
            score.incScore();
        }
        userChoice = null;
        questionNum++;
        progress += 0.166667;
        progressBar.setProgress(progress);
        loadQuestion(quizMode);
    }



    public void loadQuestion(QuizModel model){

        try {
            resetRadioButton();
            currentQuestion = model.getNextQuestion();

            if (currentQuestion != null) {
                String questionText = currentQuestion.getQuestion();
                List<String> options = currentQuestion.getOptions();
                question.setText(questionText);
                questionNumLabel.setText("Q" + questionNum + ".");
                opt1.setText(options.get(0));
                opt2.setText(options.get(1));
                opt3.setText(options.get(2));
                opt4.setText(options.get(3));

            } else {

                scorePage();

            }
        }catch (Exception e){
            System.out.println("Error occurred in loadQuestion() method");
        }

    }




    protected void scorePage() throws IOException {

        if (this instanceof SurvivalController) {
            ((SurvivalController) this).scorePage();
        } else {
            ScoreHandler.setScore(score.getScore());
            SceneChanger.changeScene("/score.fxml");
        }


        // Pass the user's score to ScoreController

    }




    private void resetRadioButton(){
        for (RadioButton btn : new RadioButton[]{ opt1, opt2, opt3, opt4 }) {
            btn.setSelected(false);
        }


    }

    @FXML
    public void opt1clicked(){
        userChoice = opt1.getText();
    }
    @FXML
    public void opt2clicked(){
        userChoice = opt2.getText();
    }

    @FXML
    public void opt3clicked(){
        userChoice = opt3.getText();
    }

    @FXML
    public void opt4clicked(){
        userChoice = opt4.getText();
    }



}
