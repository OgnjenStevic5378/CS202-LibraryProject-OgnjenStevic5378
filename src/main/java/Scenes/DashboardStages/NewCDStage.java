package Scenes.DashboardStages;

import Database.DataBase;
import Exceptions.genreNullException;
import Scenes.AlertManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * CREATE TABLE cd (
 * id_cd INT NOT NULL PRIMARY KEY,
 * name varchar(30) NOT NULL,
 * genre ENUM('movie', 'series', 'podcast', 'music', 'documentary') NOT NULL,
 * publisher_year INT
 * );
 */

public class NewCDStage {
    private Stage stage = new Stage();
    private TextField nameTextField = new TextField();
    private ComboBox<String> genreComboBox = new ComboBox<>();
    private TextField publisherYearTextField = new TextField();
    private Button closeButton;
    private Button addButton;

    public NewCDStage() {
        setAddButton();
        setCloseButton();
        setGenreComboBox();
        setStage();
    }

    // Method used to set genreComboBox
    private void setGenreComboBox() {
        genreComboBox.getItems().addAll("movie", "series", "podcast", "music", "documentary");
        genreComboBox.setPromptText("Choose Genre");
    }

    public void setStage() {
        BorderPane root = new BorderPane();

        Label labelID = new Label("ID: ");
        Label labelName = new Label("Name: ");
        Label labelGenre = new Label("Genre: ");
        Label labelPublisherYear = new Label("Publisher Year: ");

        HBox nameHBox = new HBox(labelName, nameTextField); // Pane for CD Name
        HBox genreHBox = new HBox(labelGenre, genreComboBox); // Pane for CD Genre
        HBox publisherHBox = new HBox(labelPublisherYear, publisherYearTextField); // Pane for CD Publisher Year
        HBox hBoxButton = new HBox(addButton, closeButton); // Pane Buttons

        root.setCenter(new VBox(nameHBox, genreHBox, publisherHBox, hBoxButton));

        this.stage.setTitle("New CD");
        this.stage.setScene(new Scene(root, 300, 200)); // Initialization of the root to a Scene at Scene
    }

    public void setCloseButton() {
        this.closeButton = new Button("Close");
        this.closeButton.setStyle("-fx-background-color: #cf142b; -fx-text-fill: white;");

        // Closing this stage after it's usage
        this.closeButton.setOnAction(actionEvent -> {
            this.stage.close();
        });
    }

    public void setAddButton() {
        this.addButton = new Button("Add");

        // Executing MySQL adding a new Client
        this.addButton.setOnAction(actionEvent -> {
            Integer publisherYear = null;

            // Reading everything from TextFields
            String name = nameTextField.getText();
            String genre = genreComboBox.getValue();
            String publisherYearString = publisherYearTextField.getText();

            // Casting to int if publisherYearString is not NULL. publisherYearString may be NULL
            if (!publisherYearString.isEmpty())
                publisherYear = Integer.parseInt(publisherYearString);

            // Genre mustn't be NULL
            try {
                if (genre == null || genre.isEmpty()) {
                    throw new genreNullException();
                } else {
                    // Executing MySQL query for adding a new CD
                    DataBase.addNewCD(name, genre, publisherYear);
                }
            } catch (genreNullException e) {
                AlertManager.showGenreNullAlert();
            }
        });
    }

    public Stage getStage() {
        return stage;
    }
}
