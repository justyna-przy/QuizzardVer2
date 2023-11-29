package com.Services;

import com.Controllers.*;
import com.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class SceneChanger {

    public static void changeScene(String fxml) throws IOException {
        changeScene(fxml, null, null, null);
    }

    public static void changeScene(String fxml, String mode, String difficulty, String topic) throws IOException {
        FXMLLoader loader = new FXMLLoader(SceneChanger.class.getResource(fxml));
        Parent root = loader.load();

        if ("/quiz.fxml".equals(fxml)) {
            QuizController controller = loader.getController();
            controller.initialize(mode, difficulty, topic);
            System.out.println("Calling quiz controller inti");
        } else if ("/stats.fxml".equals(fxml)) {
            StatsController controller = loader.getController();
            controller.initializeStats();
        }else if ("/score.fxml".equals(fxml)){
            ScoreController controller = loader.getController();
            controller.initializeScore();
        }else if("/survival.fxml".equals(fxml)) {
            SurvivalController controller = loader.getController();
            System.out.println("Calling Survival controller intizial");
            controller.initial();
        }else if("/survboard.fxml".equals(fxml)){
            LeaderBoard controller = loader.getController();
            controller.initialize();
        }
        Main.getStage().getScene().setRoot(root);
    }
}