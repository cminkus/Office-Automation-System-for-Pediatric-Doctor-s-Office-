import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DoctorDashboard {

    public static void display(Stage window) {
        VBox layout = new VBox(10);
        Label title = new Label("TODAY’S APPOINTMENT");

        ListView<String> patientList = new ListView<>();
        patientList.getItems().addAll("Patient A", "Patient B", "Patient C");

        Button addPatientButton = new Button("Add New Patient");

        Button patientSearchButton = new Button("Patient Search");
        Button recommendationsButton = new Button("Recommendations");
        Button vitalsButton = new Button("Vitals");
        Button contactPatientsButton = new Button("Contact Patients");
        Button prescriptionsButton = new Button("Prescriptions");

        Button logoutButton = new Button("Logout");

        layout.getChildren().addAll(title, patientList, addPatientButton, patientSearchButton, recommendationsButton, vitalsButton, contactPatientsButton, prescriptionsButton, logoutButton);
        Scene scene = new Scene(layout, 300, 400);
        window.setScene(scene);
        window.setTitle("Doctor Dashboard");
        window.show();
    }
}
