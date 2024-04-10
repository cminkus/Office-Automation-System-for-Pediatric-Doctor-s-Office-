package Phase3Implementation;

import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DoctorDashboard {
	// Provides the user interface for the doctor's dashboard in the healthcare management system
    // Displays an upcoming appointment list for the doctor along with options to add a new patient, search for patients, and logout
    public static void display(Stage window, User user) {
        VBox layout = new VBox(10);
        Color backColor = Color.web("#CFD9F7");
        layout.setStyle("-fx-background-color: #" + backColor.toString().substring(2, 8) + ";");
        
        Label title = new Label("UPCOMING APPOINTMENT");
        title.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 18px;-fx-underline: true;");
        title.setTranslateX(10);
        // The appointment details are added to a ListView to display them in a scrollable list format
        ListView<String> patientAptList = new ListView<>();
    	// An ArrayList of appointments is retrieved using the getAppointments() method from the Appointment class
        ArrayList<Appointment> appointments = Appointment.getAppointments();
        for (Appointment appointment : appointments) {
        	patientAptList.getItems().add(appointment.toString());
        }

        Button addPatientButton = new Button("Add New Patient");
        addPatientButton.setTranslateX(20);
        addPatientButton.setOnAction(e -> {
            
        	Stage addPatientStage = new Stage();
        	AddPatientInformation addPatientInformation = new AddPatientInformation();
        	addPatientInformation.start(addPatientStage, user);
        	window.close();
        });

        Button patientSearchButton = new Button("Patient Search");
        patientSearchButton.setTranslateX(20);
        patientSearchButton.setOnAction(event -> {
        	
        	PatientSearch.start(window, user, null);

        });
        
        Button logoutButton = new Button("Logout");
        logoutButton.setTranslateX(220);
        logoutButton.setTranslateY(-10);
        logoutButton.setOnAction(e -> {
            HealthcareManagementSystemGUI loginpage = new HealthcareManagementSystemGUI();
            loginpage.start(window);
        });
        
        layout.getChildren().addAll(title, patientAptList, addPatientButton, patientSearchButton, logoutButton);
        Scene scene = new Scene(layout, 300, 400);
        window.setWidth(300);
        window.setHeight(400);
        window.setScene(scene);
        window.setTitle("Doctor Dashboard");
        window.show();
    }
    
}