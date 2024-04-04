package Scenes;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;


public class SceneAboutCD {
    private Button buttonGoBack;
    private Scene scene;
    TextArea textArea = new TextArea();

    public SceneAboutCD() {
        setButtonClose();
        setTextArea();

        setScene();
    }

    public void setButtonClose() {
        this.buttonGoBack = new Button("Go Back");

        // This block switch SceneLogin and SceneDashboard
        this.buttonGoBack.setOnAction(actionEvent -> {
            SceneLogin sceneLogin = new SceneLogin();
            Window window = scene.getWindow();

            // Idk what is going on here but it works!
            if (window instanceof Stage) {
                Stage stage = (Stage) window;
                stage.setTitle("Login");

                stage.setScene(sceneLogin.getScene());
            }
        });
    }

    public void setScene() {
        VBox root = new VBox(Supa.getImg(), textArea, buttonGoBack);

        this.scene = new Scene(root, 700, 1000);
    }

    private void setTextArea() {
        textArea.setText(Supa.getText());
        textArea.setMaxWidth(700);
        textArea.setDisable(true);
        textArea.setWrapText(true);
    }


    public Scene getScene() {
        return scene;
    }
}
