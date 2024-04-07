package Scenes;

import Scenes.DashboardStages.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.Window;

public class SceneDashboard {
    private Button buttonNewClient; // Add a new Client to Database
    private Button buttonNewCD; // Add a new CD to Database
    private Button buttonStatistic; // See history of transactions
    private Button buttonIssueCD; // To rent a CD
    private Button buttonReturnCD; // To return a CD and calculate price for rented time
    private Button buttonLogout; // Logout

    private Scene scene;

    public SceneDashboard() {
        setButtonNewClient();
        setButtonNewCD();
        setButtonStatistic();
        setButtonIssueCD();
        setButtonReturnCD();
        setButtonLogout();

        setScene();
    }

    public void setButtonNewClient() {
        this.buttonNewClient = new Button("New Client");

        // The function opens a new Stage - form for entering a new Client
        this.buttonNewClient.setOnAction(actionEvent -> {
            NewClientStage newClientStage = new NewClientStage();
            newClientStage.getStage().show(); // Showing new Stage

            // Lambda function used to enable all buttons after its Stage has been closed
            newClientStage.getStage().setOnHidden(windowEvent -> enableAllButtons());

            // Used to disable all buttons because other buttons mustn't be enabled when a stage has been opened
            disableAllButtons();
        });
    }

    public void setButtonNewCD() {
        this.buttonNewCD = new Button("New CD");

        // The function opens a new Stage - form for entering a new CD
        this.buttonNewCD.setOnAction(actionEvent -> {
            NewCDStage newCDStage = new NewCDStage();
            newCDStage.getStage().show(); // Showing new Stage

            // Lambda function used to enable all buttons after its Stage has been closed
            newCDStage.getStage().setOnHidden(windowEvent -> enableAllButtons());

            // Used to disable all buttons because other buttons mustn't be enabled when a stage has been opened
            disableAllButtons();
        });
    }

    public void setButtonStatistic() {
        this.buttonStatistic = new Button("Statistic");

        // The function opens a new Stage - Window for showing statistic
        this.buttonStatistic.setOnAction(actionEvent -> {
            StatisticStage statisticStage = new StatisticStage();
            statisticStage.getStage().show(); // Showing new Stage

            // Lambda function used to enable all buttons after its Stage has been closed
            statisticStage.getStage().setOnHidden(windowEvent -> enableAllButtons());

            // Used to disable all buttons because other buttons mustn't be enabled when a stage has been opened
            disableAllButtons();
        });
    }

    public void setButtonIssueCD() {
        this.buttonIssueCD = new Button("Issue CD");

        // The function opens a new Stage - Window for showing statistic
        this.buttonIssueCD.setOnAction(actionEvent -> {
            IssueCDStage issueCDStage = new IssueCDStage();
            issueCDStage.getStage().show(); // Showing new Stage

            // Lambda function used to enable all buttons after its Stage has been closed
            issueCDStage.getStage().setOnHidden(windowEvent -> enableAllButtons());

            // Used to disable all buttons because other buttons mustn't be enabled when a stage has been opened
            disableAllButtons();
        });
    }

    public void setButtonReturnCD() {
        this.buttonReturnCD = new Button("Return CD");

        // The function opens a new Stage - Window for showing statistic
        this.buttonReturnCD.setOnAction(actionEvent -> {
            ReturnCDStage returnCDStage = new ReturnCDStage();
            returnCDStage.getStage().show(); // Showing new Stage

            // Lambda function used to enable all buttons after its Stage has been closed
            returnCDStage.getStage().setOnHidden(windowEvent -> enableAllButtons());

            // Used to disable all buttons because other buttons mustn't be enabled when a stage has been opened
            disableAllButtons();
        });
    }

    // Button used to go back to SceneLogin
    public void setButtonLogout() {
        this.buttonLogout = new Button("Logout");
        this.buttonLogout.setStyle("-fx-background-color: #cf142b; -fx-text-fill: white;");

        // This block switch SceneLogin and SceneDashboard
        this.buttonLogout.setOnAction(actionEvent -> {
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
        BorderPane root = new BorderPane();
        HBox hBoxButtons = new HBox(buttonNewClient, buttonNewCD, buttonStatistic, buttonIssueCD, buttonReturnCD, buttonLogout);
        hBoxButtons.setSpacing(10);

        root.setTop(hBoxButtons);
        this.scene = new Scene(root, 640, 360);
    }


    // Method used to disable all button (as said). Used when a new Stage is opened
    private void disableAllButtons() {
        this.buttonNewClient.setDisable(true);
        this.buttonLogout.setDisable(true);
        this.buttonNewCD.setDisable(true);
        this.buttonIssueCD.setDisable(true);
        this.buttonReturnCD.setDisable(true);
        this.buttonStatistic.setDisable(true);
    }

    // Method used to enable all button (as said). Used when the new Stage is closed
    private void enableAllButtons() {
        this.buttonNewClient.setDisable(false);
        this.buttonLogout.setDisable(false);
        this.buttonNewCD.setDisable(false);
        this.buttonIssueCD.setDisable(false);
        this.buttonReturnCD.setDisable(false);
        this.buttonStatistic.setDisable(false);
    }

    public Scene getScene() {
        return scene;
    }
}
