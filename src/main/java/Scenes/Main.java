package Scenes;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Welcome to CDProject!
 * This project has been developed by Ognjen Stević (5378) as part of the CS202 course.
 * Feel free to explore the codebase and don't hesitate to reach out if you have any questions.
 * Happy coding! 💗
 */

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        SceneLogin sceneLogin = new SceneLogin();
        stage.setScene(sceneLogin.getScene());

        stage.setTitle("Login");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}