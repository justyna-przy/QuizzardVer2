package com.Services;

import com.Controllers.*;
import com.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class SceneChanger {

    /**
     * This class is a static class used by controllers that use the initialize method.
     * changeScene method is overloaded.
     *
     * @param fxml
     * @throws IOException
     */

    public static void changeScene(String fxml) throws IOException {
        changeScene(fxml, null, null, null);
    }

    public static void changeScene(String fxml, String mode, String difficulty, String topic) throws IOException {
        FXMLLoader loader = new FXMLLoader(SceneChanger.class.getResource(fxml));
        Parent root = loader.load();

        if ("/quiz.fxml".equals(fxml)) {
            QuizController controller = loader.getController();
            controller.initialize(mode, difficulty, topic);
        } else if ("/stats.fxml".equals(fxml)) {
            StatsController controller = loader.getController();
            controller.initializeStats();
        }else if ("/score.fxml".equals(fxml)){
            ScoreController controller = loader.getController();
            controller.initializeScore();
        }else if("/survival.fxml".equals(fxml)) {
            SurvivalController controller = loader.getController();
            controller.initial();
        }else if("/survboard.fxml".equals(fxml)){
            LeaderBoard controller = loader.getController();
            controller.initialize();
        }
        Main.getStage().getScene().setRoot(root);
    }
}