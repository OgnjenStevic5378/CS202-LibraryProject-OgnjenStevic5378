package Scenes;


import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * AlertManager Class is used for good way to manage Alerts.
 */

public class AlertManager {
    // Method like a constructor, used to be call in other methods. Reduces length of code.
    private static void showAlert(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    // Alert method for invalid password or username, also shown if TextField is NULL
    public static void showInvalidPasswordOrUsernameAlert() {
        showAlert(AlertType.ERROR, "Invalid Password or Username", "Invalid password or username. Please try again.");
    }

    public static void showSuccessfullyAlert() {
        showAlert(AlertType.INFORMATION, "Success", "Operation completed successfully.");
    }

    public static void showGenreNullAlert() {
        showAlert(AlertType.WARNING, "Genre is not selected", "The Genre field must not be empty.");
    }

    public static void showInvalidCDIDAlert() {
        showAlert(AlertType.ERROR, "Invalid CD ID", "You have entered a non-existent ID. Please try again.");
    }

    public static void showInvalidClientIDAlert() {
        showAlert(AlertType.ERROR, "Invalid Client ID", "You have entered a non-existent client. Please try again.");
    }
}
