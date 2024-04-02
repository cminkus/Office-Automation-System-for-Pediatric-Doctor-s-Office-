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
        addPatientButton.setOnAction(e -> {
        	AddPatientInformation addPatientInformation = new AddPatientInformation();
            addPatientInformation.start(new Stage());
        });
        
        Button patientSearchButton = new Button("Patient Search");
        patientSearchButton.setOnAction(event -> {
        	// NEEDS EDITING (to be able to search a patients name and display the proper information. believe this means that the PatientOverview class will also need updating to read the file and display info
            PatientOverview patientOverview = new PatientOverview();
            patientOverview.start(new Stage());
        });
        
        Button recommendationsButton = new Button("Recommendations");
        recommendationsButton.setOnAction(e -> {
        	// Not sure where this is supposed to navigate to
        });
        
        Button vitalsButton = new Button("Vitals");
        vitalsButton.setOnAction(e -> {
        	// NEEDS EDITING to open correct patient's vitals, not example
        	VitalsPage vitalsPage = new VitalsPage();
        	vitalsPage.start(new Stage());
        });
        
        Button contactPatientsButton = new Button("Contact Patients");
        contactPatientsButton.setOnAction(event -> {
        	ChatScreen chatScreen = new ChatScreen();
            chatScreen.start(new Stage());
        });
        
        Button prescriptionsButton = new Button("Prescriptions");
        prescriptionsButton.setOnAction(event -> {
        	// Not sure where this is supposed to navigate to
        });
        
        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> window.close()); // Simple logout logic

        layout.getChildren().addAll(title, patientList, addPatientButton, patientSearchButton, recommendationsButton, vitalsButton, contactPatientsButton, prescriptionsButton, logoutButton);
        Scene scene = new Scene(layout, 300, 400);
        window.setScene(scene);
        window.setTitle("Doctor Dashboard");
        window.show();
    }
}
