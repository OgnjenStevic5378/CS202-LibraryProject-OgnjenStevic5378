module com.example.libraryproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.jsoup;

    // That's needed for StatisticScene
    opens DataClass to javafx.base;

    opens Scenes to javafx.fxml;
    exports Scenes;
}

