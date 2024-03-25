import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NurseDashboard {

    public static void display(Stage window) {
        VBox layout = new VBox(10);
        Label label = new Label("Nurse Dashboard");
        Button recordVitalsButton = new Button("Record Patient Vitals");

        recordVitalsButton.setOnAction(e -> {
            System.out.println("Recording patient vitals...");
            // Add functionality to record patient vitals
        });

        layout.getChildren().addAll(label, recordVitalsButton);
        Scene scene = new Scene(layout, 300, 200);
        window.setScene(scene);
        window.show();
    }
}
