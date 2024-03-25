import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DoctorDashboard {

    public static void display(Stage window) {
        VBox layout = new VBox(10);
        Label label = new Label("Doctor Dashboard");
        Button manageAppointmentsButton = new Button("Manage Appointments");

        manageAppointmentsButton.setOnAction(e -> {
            System.out.println("Managing appointments...");
            // Add functionality to manage appointments
        });

        layout.getChildren().addAll(label, manageAppointmentsButton);
        Scene scene = new Scene(layout, 300, 200);
        window.setScene(scene);
        window.show();
    }
}
