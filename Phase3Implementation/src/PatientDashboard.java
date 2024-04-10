package Phase3Implementation;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PatientDashboard {
    // Represents the dashboard interface for patients in the healthcare management system
    public static void display(Stage window, User user) {
        VBox layout = new VBox(10);
        
        Color backColor = Color.web("#D0F7CF");
        layout.setStyle("-fx-background-color: #" + backColor.toString().substring(2, 8) + ";");
        
        Label title = new Label("PATIENT OVERVIEW");
        title.setTranslateX(60);
        title.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 18px;-fx-underline: true;");
        
        Label pID = new Label("Patient ID: " + user.getID());
        pID.setTranslateX(20);
        
        String fileName = "src/Phase3Implementation/" + user.getID() + "_PatientFile.txt";
        
        readFile reader = new readFile();
        reader.mainRead(fileName);

        // Patient information is retrieved from the patient file
        Label nameLabel = new Label("Name: " + reader.getFullName());
        nameLabel.setTranslateX(20);
        Label dobLabel = new Label("Date of Birth: " + reader.getDOB());
        dobLabel.setTranslateX(20);
        Label emailLabel = new Label("Email: " + reader.getEmail());
        emailLabel.setTranslateX(20);
        Label phoneLabel = new Label("Phone number: " + reader.getPhone());
        phoneLabel.setTranslateX(20);
        Label allergiesLabel = new Label("Allergies: " + reader.getAllergies());
        allergiesLabel.setTranslateX(20);
        Label pharmacyLabel = new Label("Pharmacy: " + reader.getPharmacy());
        pharmacyLabel.setTranslateX(20);
        Label insuranceLabel = new Label("Insurance: " + reader.getPatientInsurance());
        insuranceLabel.setTranslateX(20);
        Label HHLabel = new Label("Health History: " + reader.getPatientHH());
        HHLabel.setTranslateX(20);
        
        
        Button editButton = new Button("Edit Phone Number or Email");
        editButton.setTranslateX(15);
        editButton.setOnAction(event -> {
           // Allows patients to update their contact information
           ChangePatientInfo  change = new ChangePatientInfo();
           change.start(window, user);
        });
        
        Button chatWithDoctorButton = new Button("Chat with Doctor/Nurse");
        chatWithDoctorButton.setTranslateX(15);
        chatWithDoctorButton.setOnAction(event -> {
        	// Enables patients to communicate with healthcare providers
            ChatScreen chat = new ChatScreen();
        	chat.setRole(user.getRole());
        	chat.start(window, user);
        });
        
        Button logoutButton = new Button("Logout");
        logoutButton.setTranslateX(220);
        logoutButton.setOnAction(e -> {
            HealthcareManagementSystemGUI loginpage = new HealthcareManagementSystemGUI();
            loginpage.start(window);
        });
        
        layout.getChildren().addAll(title, pID,nameLabel, dobLabel,emailLabel,phoneLabel, allergiesLabel,pharmacyLabel,insuranceLabel,HHLabel, editButton, chatWithDoctorButton, logoutButton);
        Scene scene = new Scene(layout, 300, 400);
        window.setScene(scene);
        window.setWidth(300);
        window.setHeight(400);
        window.setTitle("Patient Dashboard");
        window.show();
    }
    
}
