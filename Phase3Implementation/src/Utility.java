package Phase3Implementation;
import javafx.scene.control.Alert;

public class Utility{
    // Class to show alert messages 
    public static void alert(String title, String message) {
    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
    	alert.setTitle(title);
    	alert.setHeaderText(null);
    	alert.setContentText(message);
    	alert.showAndWait();
    }
}