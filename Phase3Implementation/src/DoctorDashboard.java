import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DoctorDashboard {

    public static void display(Stage window, User user) {
        VBox layout = new VBox(10);
        Color backColor = Color.web("#CFD9F7");
        layout.setStyle("-fx-background-color: #" + backColor.toString().substring(2, 8) + ";");
        
        Label title = new Label("TODAY’S APPOINTMENT");
        title.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 18px;-fx-underline: true;");
        title.setTranslateX(10);

        ListView<String> patientList = new ListView<>();
        patientList.setMaxHeight(200);
        patientList.setMaxWidth(300);
        patientList.getItems().addAll("Patient A", "Patient B", "Patient C");

        Button addPatientButton = new Button("Add New Patient");
        addPatientButton.setTranslateX(20);
        addPatientButton.setOnAction(e -> {
            // Logic to add new patient
        	Stage addPatientStage = new Stage();
        	AddPatientInformation addPatientInformation = new AddPatientInformation();
        	addPatientInformation.start(addPatientStage);
        });

        Button patientSearchButton = new Button("Patient Search and Contact");
        patientSearchButton.setTranslateX(20);
        patientSearchButton.setOnAction(event -> {
        	PatientSearch patser = new PatientSearch();
        	patser.start(window);
        });
        Button recommendationsButton = new Button("Recommendations");
        recommendationsButton.setTranslateX(20);
        Button vitalsButton = new Button("Vitals");
        vitalsButton.setTranslateX(20);
       
        Button prescriptionsButton = new Button("Prescriptions");
        prescriptionsButton.setTranslateX(20);

        Button logoutButton = new Button("Logout");
        logoutButton.setTranslateX(220);
        logoutButton.setTranslateY(-10);
        logoutButton.setOnAction(e -> window.close()); 
        
        layout.getChildren().addAll(title, patientList, addPatientButton, patientSearchButton, recommendationsButton, vitalsButton, prescriptionsButton, logoutButton);
        Scene scene = new Scene(layout, 300, 400);
        window.setScene(scene);
        window.setTitle("Doctor Dashboard");
        window.show();
    }
}
