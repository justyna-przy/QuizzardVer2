package com.Controllers;

import com.Classes.Stats;
import com.Classes.SurvData;
import com.Services.SceneChanger;
import com.Services.ScoreHandler;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LeaderBoard {

    @FXML
    private Label correctLabel;

    @FXML
    private TableView<SurvData> table;

    @FXML
    private TableColumn<SurvData, String> Username;

    @FXML
    private TableColumn<SurvData, Integer> Score;


    public void exitApp(ActionEvent event) throws IOException {
        SceneChanger.changeScene("/home.fxml");
    }


    @FXML
    private void onNextButtonClicked(ActionEvent event) throws IOException {
        SceneChanger.changeScene("/home.fxml");
    }

    List<SurvData> highScores = new ArrayList<>();
    ObservableList<SurvData> OList = FXCollections.observableArrayList();

    public void initialize(){

        Stats stats = new Stats("src/main/resources/surv.csv");
        highScores = stats.findHighScores();
        Collections.sort(highScores, Comparator.comparingInt(SurvData::getHighScore).reversed());

        Username.setCellValueFactory(new PropertyValueFactory<SurvData, String>("username"));
        Score.setCellValueFactory(new PropertyValueFactory<SurvData, Integer>("highScore"));

        for(int i = 0; i < 10; i++){
            OList.add(highScores.get(i));
        }

        correctLabel.setText("You got " + ScoreHandler.getSurvScore() + " Correct!");


        table.setItems(OList);


    }
}
