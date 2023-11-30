package com.Controllers;

import com.Services.SceneChanger;
import javafx.fxml.FXML;

import java.io.IOException;

public class SurvInfoController {


    public void exitApp() throws IOException {
        SceneChanger.changeScene("/home.fxml");
    }


    @FXML
    private void onNextButtonClicked() throws IOException {
        SceneChanger.changeScene("/survival.fxml");
    }
}
