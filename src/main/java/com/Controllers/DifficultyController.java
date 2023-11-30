package com.Controllers;

import com.Services.SceneChanger;


import java.io.IOException;

public class DifficultyController {


    public void goHome() throws IOException {
        SceneChanger.changeScene("/home.fxml");
    }


    public void goToStats() throws IOException {
        SceneChanger.changeScene("/stats.fxml");
    }


    public void exitApp(){

    }






    public void selectIntermedd() throws IOException {
        SceneChanger.changeScene("/quiz.fxml", "Difficulty", "medium", null);

    }

    public void selectNovice() throws IOException {
        SceneChanger.changeScene("/quiz.fxml", "Difficulty", "easy", null);
    }

    public void selectExpert() throws IOException {
        SceneChanger.changeScene("/quiz.fxml", "Difficulty", "hard", null);
    }

}
