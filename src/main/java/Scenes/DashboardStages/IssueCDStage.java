package Scenes.DashboardStages;

import Database.DataBase;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Date;
import java.text.SimpleDateFormat;

import static Database.DataBase.issueCD;

public class IssueCDStage {
    private Stage stage = new Stage();
    private TextField idCDTextField = new TextField();
    private TextField idClientTextField = new TextField();
    private TextField currentDateTextField = new TextField();
    private final Date currentDate = new Date(System.currentTimeMillis());
    private Button closeButton;
    private Button addButton;

    public IssueCDStage() {
        setAddButton();
        setCloseButton();
        setCurrentDateTextField();

        setStage();
    }

    private void setStage() {
        BorderPane root = new BorderPane();

        Label labelIssueID = new Label("Issue ID: ");
        Label labelCDID = new Label("CD ID: ");
        Label labelClientID = new Label("Client ID: ");
        Label labelCurrentDate = new Label("Current Date: ");

        HBox cdIDHBox = new HBox(labelCDID, idCDTextField); // HBox used for CD ID
        HBox clientIDHBox = new HBox(labelClientID, idClientTextField); // HBox used for Client ID
        HBox currentDateHBox = new HBox(labelCurrentDate, currentDateTextField); // HBox used for Current Date
        HBox hBoxButton = new HBox(addButton, closeButton); // HBox used for Buttons
        root.setCenter(new VBox(cdIDHBox, clientIDHBox, currentDateHBox, hBoxButton));

        this.stage.setTitle("Issue CD");
        this.stage.setScene(new Scene(root, 300, 200));
    }

    public void setAddButton() {
        this.addButton = new Button("Add");

        // Executing MySQL adding a new Client
        this.addButton.setOnAction(actionEvent -> {
            // Reading everything from TextFields
            int idCD = Integer.parseInt(idCDTextField.getText());
            int idClient = Integer.parseInt(idClientTextField.getText());

            // Database
            DataBase.issueCD(idCD, idClient, this.currentDate);
        });
    }

    public Stage getStage() {
        return stage;
    }

    public void setCloseButton() {
        this.closeButton = new Button("Close");
        this.closeButton.setStyle("-fx-background-color: #cf142b; -fx-text-fill: white;");

        // Closing this stage after it's usage
        this.closeButton.setOnAction(actionEvent -> {
            this.stage.close();
        });
    }

    private void setCurrentDateTextField() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // The format
        currentDateTextField.setText(formatter.format(this.currentDate)); // Setting data to TextField
        currentDateTextField.setDisable(true);
    }
}
