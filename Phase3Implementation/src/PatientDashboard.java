import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PatientDashboard {

    public static void display(Stage window) {
        VBox layout = new VBox(10);
        Label title = new Label("PATIENT OVERVIEW");

        Label nameLabel = new Label("Name: [Patient Name]");
        Label dobLabel = new Label("Date of Birth: [DOB]");
        Label allergiesLabel = new Label("Allergies: [Allergies]");
        Label medicationLabel = new Label("Medication: [Medication]");
        // Add more labels as needed

        Button editPhoneButton = new Button("Edit Phone Number");
        Button chatWithDoctorButton = new Button("Chat with Doctor/Nurse");

        Button logoutButton = new Button("Logout");

        layout.getChildren().addAll(title, nameLabel, dobLabel, allergiesLabel, medicationLabel, editPhoneButton, chatWithDoctorButton, logoutButton);
        Scene scene = new Scene(layout, 300, 400);
        window.setScene(scene);
        window.setTitle("Patient Overview");
        window.show();
    }
}
