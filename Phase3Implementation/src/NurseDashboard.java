import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NurseDashboard {

    public static void display(Stage window,  User user) {
        VBox layout = new VBox(10);
        Label title = new Label("UPCOMING APPOINTMENTS");
        // Simulate patient list for today's appointments
        ListView<String> patientList = new ListView<>();
        patientList.getItems().addAll("Patient 1", "Patient 2", "Patient 3"); // Example data

        Button addPatientButton = new Button("Add New Patient");
        addPatientButton.setOnAction(e -> {
            // Logic to add new patient
        	Stage addPatientStage = new Stage();
        	AddPatientInformation addPatientInformation = new AddPatientInformation();
        	addPatientInformation.start(addPatientStage);
        });

        // Example links to other pages
        Button patientSearchButton = new Button("Patient Search");
        Button vitalsButton = new Button("Vitals");
        Button chatWithPatientButton = new Button("Contact Patients");
        chatWithPatientButton.setOnAction(event -> {
        	ChatScreen chat = new ChatScreen();
        	chat.setRole(user.getRole());
        	chat.start(window);
        });

        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> window.close()); // Simple logout logic

        layout.getChildren().addAll(title, patientList, addPatientButton, patientSearchButton, vitalsButton, chatWithPatientButton, logoutButton);
        Scene scene = new Scene(layout, 300, 400);
        window.setScene(scene);
        window.setTitle("Nurse Dashboard");
        window.show();
    }
}
