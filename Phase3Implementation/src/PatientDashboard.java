import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PatientDashboard {

    public static void display(Stage window) {
        VBox layout = new VBox(10);
        Label label = new Label("Patient Dashboard");
        Button viewAppointmentsButton = new Button("View My Appointments");

        viewAppointmentsButton.setOnAction(e -> {
            System.out.println("Viewing patient's appointments...");
            // Add functionality for patients to view their appointments
        });

        layout.getChildren().addAll(label, viewAppointmentsButton);
        Scene scene = new Scene(layout, 300, 200);
        window.setScene(scene);
        window.show();
    }
}
