package Scenes.DashboardStages;

import Database.DataBase;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * CREATE TABLE client (
 * id_client INT NOT NULL PRIMARY KEY,
 * name varchar(30) NOT NULL,
 * last_name varchar(30) NOT NULL,
 * birth_date date
 * );
 */


public class NewClientStage {
    private Stage stage = new Stage();
    private TextField idTextField = new TextField();
    private TextField nameTextField = new TextField();
    private TextField lastnameTextField = new TextField();
    private DatePicker birthDateDatePicker;
    private String birthDateDatePickerString;
    private Button closeButton;
    private Button addButton;

    public NewClientStage() {
        setCloseButton();
        setAddButton();
        setBirthDateDatePicker();

        setStage();
    }

    private void setBirthDateDatePicker() {
        this.birthDateDatePicker = new DatePicker();

        // Disabling user editing of the birthDateDatePicker
        this.birthDateDatePicker.getEditor().setDisable(true);

        // Setting a fixed format for the birthDateDatePicker yyyy-MM-dd, because it's default MySQL format
        StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
            final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            @Override
            public String toString(LocalDate date) {
                if (date != null)
                    return dateFormatter.format(date);
                else
                    return "";
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        };

        this.birthDateDatePicker.setConverter(converter);

        // Setting an EventHandler to assign the selected date to this.birthDateDatePickerString
        EventHandler<ActionEvent> event = actionEvent -> {
            // Getting the value of the birthDateDatePicker
            this.birthDateDatePickerString = this.birthDateDatePicker.getEditor().getText();
        };

        // Adding an EventHandler to the birthDateDatePicker
        this.birthDateDatePicker.setOnAction(event);
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage() {
        BorderPane root = new BorderPane();

        Label labelID = new Label("ID: ");
        Label labelName = new Label("Name: ");
        Label labelLastname = new Label("Lastname: ");
        Label labelBirthdate = new Label("Birthdate: ");

        HBox idHBox = new HBox(labelID, idTextField); // Pane for ID
        HBox nameHBox = new HBox(labelName, nameTextField); // Pane for Client Name
        HBox lastnameHBox = new HBox(labelLastname, lastnameTextField); // Pane for Client Lastname
        HBox birthDateHBox = new HBox(labelBirthdate, birthDateDatePicker); // Pane for Client Birthdate
        HBox hBoxButton = new HBox(addButton, closeButton); // Pane for Buttons

        root.setCenter(new VBox(idHBox, nameHBox, lastnameHBox, birthDateHBox, hBoxButton)); // Setting Panes above to the root

        this.stage.setTitle("New Client");
        this.stage.setScene(new Scene(root, 300, 300)); // Initialization of the root to a Scene at Scene
    }

    public void setCloseButton() {
        this.closeButton = new Button("Close");

        // Closing this stage after it's usage
        this.closeButton.setOnAction(actionEvent -> {
            this.stage.close();
        });
    }

    public void setAddButton() {
        this.addButton = new Button("Add");

        // Executing MySQL adding a new Client
        this.addButton.setOnAction(actionEvent -> {
            // Reading everything from TextFields
            int idClient = Integer.parseInt(idTextField.getText());
            String name = nameTextField.getText();
            String lastName = lastnameTextField.getText();
            Date birthDate = Date.valueOf(birthDateDatePickerString);

            // Executing MySQL query for adding a new Client
            DataBase.addNewClient(idClient, name, lastName, birthDate);
        });
    }
}
