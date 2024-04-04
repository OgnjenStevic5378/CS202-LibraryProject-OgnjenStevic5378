package Scenes.DashboardStages;

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

import static Database.DataBase.returnCD;

public class ReturnCDStage {
    private Stage stage = new Stage();
    private TextField issueIDTextField = new TextField();
    private TextField endDateTextField = new TextField();
    private final Date endDate = new Date(System.currentTimeMillis());
    private Button closeButton;
    private Button addButton;

    public ReturnCDStage() {
        setAddButton();
        setCloseButton();
        setCurrentDateTextField();

        setStage();
    }

    private void setStage() {
        BorderPane root = new BorderPane();

        Label labelIssueID = new Label("Issue ID: ");
        Label labelCurrentDate = new Label("Current Date: ");

        HBox issueIDHBox = new HBox(labelIssueID, issueIDTextField); // HBox used for Issue ID
        HBox currentDateHBox = new HBox(labelCurrentDate, endDateTextField); // HBox used for Current Date
        root.setCenter(new VBox(issueIDHBox, currentDateHBox));

        HBox hBoxButton = new HBox(addButton, closeButton);
        root.setBottom(hBoxButton);

        this.stage.setTitle("Return CD");
        this.stage.setScene(new Scene(root, 300, 300));
    }

    public void setAddButton() {
        this.addButton = new Button("Add");

        // Executing MySQL adding a new Client
        this.addButton.setOnAction(actionEvent -> {
            // Reading everything from TextFields
            int issueID = Integer.parseInt(issueIDTextField.getText());

            // Database
            returnCD(issueID, this.endDate);
        });
    }

    public Stage getStage() {
        return this.stage;
    }

    public void setCloseButton() {
        this.closeButton = new Button("Close");

        // Closing this stage after it's usage
        this.closeButton.setOnAction(actionEvent -> {
            this.stage.close();
        });
    }

    private void setCurrentDateTextField() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); // The format
        endDateTextField.setText(formatter.format(this.endDate)); // Setting data to TextField
        endDateTextField.setDisable(true);
    }
}
