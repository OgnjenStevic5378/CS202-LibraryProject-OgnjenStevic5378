package Scenes;

import DataClass.Employee;
import Database.DataBase;
import Encryption.SHA256;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.List;

public class SceneLogin {
    private TextField textFieldUsername = new TextField();
    private PasswordField passwordField = new PasswordField();
    private Button buttonLogin;
    private Button buttonClose;
    private Button buttonAbout;
    private Scene scene;


    public SceneLogin() {
        // Button initialization
        setButtonLogin();
        setButtonClose();
        setButtonAbout();

        setScene();
    }

    // Button initialization
    public void setButtonLogin() {
        this.buttonLogin = new Button("Login");

        // This block switch SceneLogin and SceneDashboard
        this.buttonLogin.setOnAction(actionEvent -> {

            if (isLoginGood()) { // Checks Password and Username
                SceneDashboard sceneDashboard = new SceneDashboard();
                Window window = scene.getWindow();

                // Idk what is going on here but it works!
                if (window instanceof Stage) {
                    Stage stage = (Stage) window;

                    stage.setTitle("Dashboard");
                    stage.setScene(sceneDashboard.getScene());
                }
            } else { // if the Password or Username are incorrect or NULL
                AlertManager.showInvalidPasswordOrUsernameAlert();
            }
        });
    }

    // Button initialization
    public void setButtonClose() {
        this.buttonClose = new Button("Close");

        this.buttonClose.setOnAction(actionEvent -> {
            Window window = scene.getWindow();

            // It's similar with the method above
            if (window instanceof Stage) {
                Stage stage = (Stage) window;
                stage.close();
            }
        });
    }

    public void setButtonAbout() {
        this.buttonAbout = new Button("About (JSoup)");

        this.buttonAbout.setOnAction(actionEvent -> {

            SceneAboutCD sceneAboutCD = new SceneAboutCD();
            Window window = scene.getWindow();

            // Idk what is going on here but it works!
            if (window instanceof Stage) {
                Stage stage = (Stage) window;

                stage.setTitle("About CDs (JSoup)");
                stage.setScene(sceneAboutCD.getScene());
            }
        });
    }

    // Method used to check if password is correct
    private boolean isLoginGood() {
        String hashedPasswordField = SHA256.getHashedString(passwordField.getText());
        List<Employee> emplpoyees = DataBase.getAllEmployee();

        // Comparing each
        for (Employee employee : emplpoyees)
            if (employee.getUsername().equals(textFieldUsername.getText()) && employee.getPassword().equals(hashedPasswordField))
                return true;
        return false;
    }

    public Scene getScene() {
        return scene;
    }

    // Scene builder
    public void setScene() {
        BorderPane root = new BorderPane();

        Label labelUsername = new Label("Username: ");
        Label labelPassword = new Label("Password: ");

        HBox hBoxUsername = new HBox(labelUsername, textFieldUsername); // Pane for Username
        HBox hBoxPassword = new HBox(labelPassword, passwordField); // Pane for Password
        HBox hBoxButton = new HBox(buttonLogin, buttonAbout, buttonClose); // Pane for Buttons

        root.setCenter(new VBox(hBoxUsername, hBoxPassword, hBoxButton));

        this.scene = new Scene(root, 500, 500); // Initialization of the root to a Scene and specifying dimensions
    }
}
