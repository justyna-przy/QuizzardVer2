package com.Controllers;

import com.Services.SceneChanger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class SurvInfoController {


    public void exitApp(ActionEvent event) throws IOException {
        SceneChanger.changeScene("/home.fxml");
    }


    @FXML
    private void onNextButtonClicked(ActionEvent event) throws IOException {
        SceneChanger.changeScene("/survival.fxml");
    }
}
