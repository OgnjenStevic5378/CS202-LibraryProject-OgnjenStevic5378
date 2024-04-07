package Scenes.DashboardStages;

import DataClass.StatisticData;
import Database.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class StatisticStage {
    private Stage stage = new Stage();
    private Button closeButton;
    private ArrayList<StatisticData> statisticDataArrayList; // ArrayList used to store all red data
    private ArrayList<StatisticData> statisticDataReturned = new ArrayList<StatisticData>(); // Objects from "statisticDataArrayList" that end_date != NULL
    private ArrayList<StatisticData> statisticDataNotReturned = new ArrayList<StatisticData>(); // Objects from "statisticDataArrayList" that end_date == NULL

    public StatisticStage() {
        setCloseButton();
        getStatisticDataArrayList();
        organiseArray();

        setStage();
    }

    private void getStatisticDataArrayList() {
        this.statisticDataArrayList = DataBase.getStatisticData();
    }

    private void organiseArray() {
        for (StatisticData statisticData : statisticDataArrayList) {
            if (statisticData.getCdAndClientEndDate() == null)
                this.statisticDataNotReturned.add(statisticData);
            else
                this.statisticDataReturned.add(statisticData);
        }
        this.statisticDataArrayList.clear();
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage() {
        BorderPane root = new BorderPane();

        VBox centerVBox = new VBox(new Label("Not Returned CDs:"), getNotReturnedTableView(), new Label("Retunded CD:"), getReturnedTableView());
        root.setCenter(centerVBox);

        root.setBottom(closeButton);

        this.stage.setTitle("Statistic");
        this.stage.setScene(new Scene(root, 750, 400)); // Initialization of the root to a Scene at Scene
    }

    private TableView<StatisticData> getNotReturnedTableView() {
        TableView<StatisticData> tableView = new TableView<>();

        // Convert the ArrayList to ObservableList (required by TableView)
        ObservableList<StatisticData> statisticData = FXCollections.observableArrayList(this.statisticDataNotReturned);
        tableView.setItems(statisticData);

        // Define columns
        TableColumn idIssueColumn = new TableColumn<>("ID Issue:");
        TableColumn idClientColumn = new TableColumn<>("ID Client:");
        TableColumn nameClientColumn = new TableColumn<>("Client's Name:");
        TableColumn idCDColumn = new TableColumn<>("ID CD:");
        TableColumn nameCDColumn = new TableColumn<>("CD Name:");
        TableColumn beginDateColumn = new TableColumn<>("Begin Date:");

        // Values
        idIssueColumn.setCellValueFactory(new PropertyValueFactory<StatisticData, Integer>("idCDAndClient"));
        idClientColumn.setCellValueFactory(new PropertyValueFactory<StatisticData, Integer>("idClientProperty"));
        nameClientColumn.setCellValueFactory(new PropertyValueFactory<StatisticData, String>("nameClientProperty"));
        idCDColumn.setCellValueFactory(new PropertyValueFactory<StatisticData, Integer>("idCDProperty"));
        nameCDColumn.setCellValueFactory(new PropertyValueFactory<StatisticData, String>("nameCDProperty"));
        beginDateColumn.setCellValueFactory(new PropertyValueFactory<StatisticData, String>("cdAndClientBeginDateProperty"));

        tableView.getColumns().addAll(idIssueColumn, idClientColumn, nameClientColumn, idCDColumn, nameCDColumn, beginDateColumn);

        // Setting max height (6) of rows
        tableView.setFixedCellSize(30);
        tableView.setMaxHeight(5 * tableView.getFixedCellSize() + 30);
        return tableView;
    }

    private TableView<StatisticData> getReturnedTableView() {
        TableView<StatisticData> tableView = new TableView<>();

        // Convert the ArrayList to ObservableList (required by TableView)
        ObservableList<StatisticData> statisticData = FXCollections.observableArrayList(this.statisticDataReturned);
        tableView.setItems(statisticData);

        // Define columns
        TableColumn idIssueColumn = new TableColumn("ID Issue:");
        TableColumn idClientColumn = new TableColumn("ID Client:");
        TableColumn nameClientColumn = new TableColumn<>("Client's Name:");
        TableColumn idCDColumn = new TableColumn<>("ID CD:");
        TableColumn nameCDColumn = new TableColumn<>("CD Name:");
        TableColumn beginDateColumn = new TableColumn<>("Begin Date:");
        TableColumn endDateColumn = new TableColumn<>("End Date:");

        // Values
        idIssueColumn.setCellValueFactory(new PropertyValueFactory<StatisticData, Integer>("idCDAndClient"));
        idClientColumn.setCellValueFactory(new PropertyValueFactory<StatisticData, Integer>("idClientProperty"));
        nameClientColumn.setCellValueFactory(new PropertyValueFactory<StatisticData, String>("nameClientProperty"));
        idCDColumn.setCellValueFactory(new PropertyValueFactory<StatisticData, Integer>("idCDProperty"));
        nameCDColumn.setCellValueFactory(new PropertyValueFactory<StatisticData, String>("nameCDProperty"));
        beginDateColumn.setCellValueFactory(new PropertyValueFactory<StatisticData, String>("cdAndClientBeginDateProperty"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<StatisticData, String>("cdAndClientEndDateProperty"));

        tableView.getColumns().addAll(idIssueColumn, idClientColumn, nameClientColumn, idCDColumn, nameCDColumn, beginDateColumn, endDateColumn);

        // Setting max height (6) of rows
        tableView.setFixedCellSize(30);
        tableView.setMaxHeight(5 * tableView.getFixedCellSize() + 30);
        return tableView;
    }

    public void setCloseButton() {
        this.closeButton = new Button("Close");
        this.closeButton.setStyle("-fx-background-color: #cf142b; -fx-text-fill: white;");

        // Closing this stage after it's usage
        this.closeButton.setOnAction(actionEvent -> {
            this.stage.close();
        });
    }
}
