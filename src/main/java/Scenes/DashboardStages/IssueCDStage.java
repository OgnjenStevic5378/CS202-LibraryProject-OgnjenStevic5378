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

import static Database.DataBase.issueCD;

public class IssueCDStage {
    private Stage stage = new Stage();
    private TextField issueIDTextField = new TextField();
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

        HBox issueIDHBox = new HBox(labelIssueID, issueIDTextField); // HBox used for Issue ID
        HBox cdIDHBox = new HBox(labelCDID, idCDTextField); // HBox used for CD ID
        HBox clientIDHBox = new HBox(labelClientID, idClientTextField); // HBox used for Client ID
        HBox currentDateHBox = new HBox(labelCurrentDate, currentDateTextField); // HBox used for Current Date
        root.setCenter(new VBox(issueIDHBox, cdIDHBox, clientIDHBox, currentDateHBox));

        HBox hBoxButton = new HBox(addButton, closeButton);
        root.setBottom(hBoxButton);

        this.stage.setTitle("Issue CD");
        this.stage.setScene(new Scene(root, 300, 300));
    }

    public void setAddButton() {
        this.addButton = new Button("Add");

        // Executing MySQL adding a new Client
        this.addButton.setOnAction(actionEvent -> {
            // Reading everything from TextFields
            int issueID = Integer.parseInt(issueIDTextField.getText());
            int idCD = Integer.parseInt(idCDTextField.getText());
            int idClient = Integer.parseInt(idClientTextField.getText());

            // Database
            issueCD(issueID, idCD, idClient, this.currentDate);
        });
    }

    public Stage getStage() {
        return stage;
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
        currentDateTextField.setText(formatter.format(this.currentDate)); // Setting data to TextField
        currentDateTextField.setDisable(true);
    }
}