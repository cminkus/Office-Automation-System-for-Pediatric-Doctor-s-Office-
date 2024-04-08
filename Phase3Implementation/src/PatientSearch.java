package Phase3Implementation;

import java.io.File;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PatientSearch {

    public static void start(Stage window, User user, Appointment appointment) {
        VBox layout = new VBox(10);
        
        Color backColor = Color.web("#D9FED3");
        layout.setStyle("-fx-background-color: #" + backColor.toString().substring(2, 8) + ";");
        
        Label title = new Label("PATIENT SEARCH");
        title.setTranslateX(80);
        title.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 18px;-fx-underline: true;");
        
        Label pID = new Label("Patient ID: ");
        pID.setTranslateX(10);
        pID.setTranslateY(20);
        pID.setStyle("-fx-font-size: 15px;");
        TextField enteredID = new TextField();
        enteredID.setPromptText("Enter Patient ID");
        enteredID.setMaxWidth(150);
        enteredID.setTranslateX(80);
        enteredID.setTranslateY(-10);
        
        Button search = new Button("Search");
        search.setTranslateX(125);
        search.setTranslateY(-10);
        
        Button vitalsButton = new Button("Vitals");
        vitalsButton.setTranslateX(20);
        vitalsButton.setOnAction(event -> {
        	VitalsPage vpage = new VitalsPage();
        	vpage.start(window, enteredID.getText(), user);
        });
        
        Button contactPatient = new Button("Contact Patient");
        contactPatient.setTranslateX(15);
        contactPatient.setOnAction(event -> {
        	ChatScreen chat = new ChatScreen();
        	chat.setRole(user.getRole());
        	chat.start(window, user);
        });
        
        Button scheduleAppointment = new Button("Schedule Appointment");
        scheduleAppointment.setTranslateX(20);
        scheduleAppointment.setOnAction(event -> {
        	Schedule.schedule(window,user,  enteredID.getText()); 
        });
        
        Button backButton = new Button("Back");
        backButton.setTranslateX(125);
        backButton.setTranslateY(-10);
        backButton.setOnAction(event -> {
        	switch (user.getRole()) {
            case "Doctor":
                DoctorDashboard.display(window ,user);
                break;
            case "Nurse":
                NurseDashboard.display(window ,user);
                break;
        }
        });
        Button Prescription = new Button("Prescriptions");
        Prescription.setTranslateX(20);
        Prescription.setOnAction(event ->{
        	PrescriptionPage prescriptionPage = new PrescriptionPage();
            prescriptionPage.setPatientID(enteredID.getText());
            
            
            prescriptionPage.start(window, user);
        });
        
        search.setOnAction(event -> {
            if (!enteredID.getText().isEmpty()) { 
                String fileName = enteredID.getText() + "_PatientFile.txt";
                File file = new File(fileName);
                if (!file.exists()) { // Check if the file doesn't exist
                    Utility.alert("No Patient File Found", "This patient does not have a file. Please create one to proceed.");
                } else {
                    try {
                        window.setWidth(300);
                        window.setHeight(600);
                        // Attempt to read the file
                        readFile reader = new readFile();
                        reader.mainRead(fileName);

                        // Display patient information
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
                        contactPatient.setTranslateX(20);
                        vitalsButton.setTranslateX(20);

                        layout.getChildren().addAll(nameLabel, dobLabel, emailLabel, phoneLabel, allergiesLabel, pharmacyLabel, insuranceLabel, HHLabel, contactPatient, scheduleAppointment, vitalsButton, Prescription);
                    } catch (Exception e) {
                        Utility.alert("Error", "An error occurred while reading the patient file.");
                    }
                }
            }
        });

        layout.getChildren().addAll(title, pID,enteredID,search,backButton);
        Scene scene = new Scene(layout, 300, 200);
        window.setScene(scene);
        window.setTitle("Patient Search");
        window.show();
    }
    
}
