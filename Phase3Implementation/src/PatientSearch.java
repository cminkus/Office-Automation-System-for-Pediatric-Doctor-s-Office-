import java.time.LocalDate;
import java.util.Map;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PatientSearch {

    public static void start(Stage window, User user) {
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
        
        Button contactPatient = new Button("Contact Patient");
        contactPatient.setTranslateX(20);

        Button bookAppointmentButton = new Button("Book Appointment");
        bookAppointmentButton.setTranslateX(20); // Adjust positioning as needed

        bookAppointmentButton.setOnAction(e -> {
                displayAppointmentForm(window, enteredID.getText(), user);
        });

        search.setOnAction(event ->{
        	if(!enteredID.getText().isEmpty()) { 
        		String fileName = enteredID.getText() + "_PatientFile.txt";
                System.out.println(fileName);
    	    	readFile reader = new readFile();
    	        reader.mainRead(fileName);
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
    	        layout.getChildren().addAll(nameLabel, dobLabel,emailLabel,phoneLabel, allergiesLabel,pharmacyLabel,insuranceLabel,HHLabel,contactPatient, bookAppointmentButton);
    	    }
        });
      
        layout.getChildren().addAll(title, pID,enteredID,search);
        Scene scene = new Scene(layout, 300, 400);
        window.setScene(scene);
        window.setTitle("Patient Dashboard");
        window.show();
    }
    
    public static void displayAppointmentForm(Stage window, String patientID, User user) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        
        Label doctorNameLabel = new Label("Doctor's Name:");
        grid.add(doctorNameLabel, 0, 0); // Note: Ensure the row index here is correctly set
        TextField doctorNameField = new TextField();
        doctorNameField.setPromptText("Doctor's Name");
        grid.add(doctorNameField, 1, 0);

        if (user instanceof Doctor) { //autofill the doctor's name if the user is a doctor
            doctorNameField.setText(user.getUsername()); 
            doctorNameField.setDisable(true); // Optional: Disable the field to prevent editing.
        }
        else {
            System.out.print("user is not doctor");
        }

        
        // Appointment Date
        Label dateLabel = new Label("Date:");
        grid.add(dateLabel, 0, 1);
        DatePicker datePicker = new DatePicker();
        grid.add(datePicker, 1, 1);
        
        // Appointment Time
        Label timeLabel = new Label("Time (HH:MM):");
        grid.add(timeLabel, 0, 2);
        TextField timeField = new TextField();
        grid.add(timeField, 1, 2);
        
        // Purpose
        Label purposeLabel = new Label("Purpose:");
        grid.add(purposeLabel, 0, 3);
        TextField purposeField = new TextField();
        grid.add(purposeField, 1, 3);

        Scene scene = new Scene(grid, 400, 275);
        window.setScene(scene);
        window.setTitle("Add New Appointment");
        
        // Submit Button
        Button submitButton = new Button("Submit");
        grid.add(submitButton, 1, 4);
        submitButton.setOnAction(e -> {
            String date = (datePicker.getValue() != null) ? datePicker.getValue().toString() : "";
            String time = timeField.getText();
            String purpose = purposeField.getText();
            String doctorName = doctorNameField.getText();
        
            // Basic validation
            if (doctorNameField.getText().isEmpty() || date.isEmpty() || time.isEmpty() || purpose.isEmpty()) {
                System.out.println("All fields must be filled out.");
                return; // Stop processing the event here
            }
        
            // Time validation (basic format check)
            if (!time.matches("\\d{2}:\\d{2}")) {
                System.out.println("Time must be in the format HH:MM.");
                return; // Stop processing the event here
            }

            if(doctorNameField.getText().isEmpty() || datePicker.getValue() == null) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText("Missing Information");
                errorAlert.setContentText("Please fill out all fields.");
                errorAlert.showAndWait();
            } else {
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Success");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Appointment successfully scheduled.");
                successAlert.showAndWait();

                doctorNameField.clear();
                datePicker.setValue(null);
                timeField.clear();
                purposeField.clear();
                
                bookAppointment(doctorName, patientID, datePicker.getValue(), time, purpose);
                start(window, user); // Switch back to the main dashboard
            }
        });       
    }
    public static void bookAppointment(String doctorUsername, String patientID, LocalDate date, String time, String purpose) {
        Map<String, User> users = Database.getuser();
    
        Doctor doctor = null;
        if (users.get(doctorUsername) instanceof Doctor) {
            doctor = (Doctor) users.get(doctorUsername);
        } else {
            System.out.println("Doctor not found.");
            // Handle case where doctor is not found
            return;
        }
    
        Patient patient = null;
        if (users.get(patientID) instanceof Patient) {
            patient = (Patient) users.get(patientID);
        } else {
            System.out.println("Patient not found.");
            // Handle case where patient is not found
            return;
        }
    
        // Assuming you have a constructor for Appointment that takes these parameters
        Appointment newAppointment = new Appointment(doctorUsername, patientID, date.toString(), time, purpose);
    
        // Add the appointment to the doctor and potentially to the patient
        doctor.addAppointment(newAppointment);
        // Assuming you also want to track appointments on the patient side
        patient.addAppointment(newAppointment);
    
        // Save changes back to the database
        Database.saveData();
        System.out.println("Appointment successfully added.");
    }
}
