import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DoctorDashboard {

    private static TableView<Appointment> appointmentsTable = new TableView<>();

    public static void display(Stage window, User user) {
        VBox layout = new VBox(10);
        layout.getChildren().clear();
        Color backColor = Color.web("#CFD9F7");
        layout.setStyle("-fx-background-color: #" + backColor.toString().substring(2, 8) + ";");
        
        Label title = new Label("TODAY’S APPOINTMENT");
        title.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 18px;-fx-underline: true;");
        title.setTranslateX(10);
        
        appointmentsTable = new TableView<>();
        ObservableList<Appointment> appointmentsData = FXCollections.observableArrayList();

        // ListView<String> patientList = new ListView<>();
        // patientList.setMaxHeight(200);
        // patientList.setMaxWidth(300);
        // patientList.getItems().addAll("Patient A", "Patient B", "Patient C");

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
        prescriptionsButton.setOnAction(e -> {
            showPrescriptionForm(window, user); // Pass the current 'user' which is a Doctor
        });
        
        Button bookAppointmentButton = new Button("Book Appointment");
        bookAppointmentButton.setTranslateX(20); // Adjust positioning as needed

        bookAppointmentButton.setOnAction(e -> {
                displayAppointmentForm(window, user);
        });

        if (user instanceof Doctor) {
            Doctor doctor = (Doctor) user;
            appointmentsData = getDoctorAppointments(doctor);
            appointmentsTable.setItems(appointmentsData);
        }
        

        TableColumn<Appointment, String> patientColumn = new TableColumn<>("Patient");
        patientColumn.setCellValueFactory(new PropertyValueFactory<>("patientUsername"));

        TableColumn<Appointment, String> timeColumn = new TableColumn<>("Time");
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentTime"));

        TableColumn<Appointment, String> purposeColumn = new TableColumn<>("Purpose");
        purposeColumn.setCellValueFactory(new PropertyValueFactory<>("purpose"));

        appointmentsTable.getColumns().addAll(patientColumn, timeColumn, purposeColumn);
        appointmentsTable.setItems(appointmentsData);

        layout.getChildren().addAll(appointmentsTable);

        Button logoutButton = new Button("Logout");
        logoutButton.setTranslateX(220);
        logoutButton.setTranslateY(-10);
        logoutButton.setOnAction(e -> window.close()); 
        
        layout.getChildren().addAll(title, addPatientButton, patientSearchButton, recommendationsButton, vitalsButton, prescriptionsButton, bookAppointmentButton, logoutButton);
        Scene scene = new Scene(layout, 300, 400);
        window.setScene(scene);
        window.setTitle("Doctor Dashboard");
        window.show();
    }

    private static ObservableList<Appointment> getDoctorAppointments(Doctor doctor) {
        LocalDate today = LocalDate.now();
        List<Appointment> todaysAppointments = doctor.getAppointments().stream()
                .filter(a -> a.getAppointmentTime().equals(today))
                .collect(Collectors.toList());
        return FXCollections.observableArrayList(todaysAppointments);
    }

    private static void showPrescriptionForm(Stage window, User user) { // Assume doctorUsername is passed to identify the doctor
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
    
        TextField patientUsernameField = new TextField();
        patientUsernameField.setPromptText("Patient Username");
        TextField medicationNameField = new TextField();
        medicationNameField.setPromptText("Medication Name");
        TextField dosageField = new TextField();
        dosageField.setPromptText("Dosage");
        TextField frequencyField = new TextField();
        frequencyField.setPromptText("Frequency");
        TextField issueDateField = new TextField();
        issueDateField.setPromptText("Issue Date");
        TextField expiryDateField = new TextField();
        expiryDateField.setPromptText("Expiry Date");
        TextField refillsField = new TextField();
        refillsField.setPromptText("Refills");
        TextField notesField = new TextField();
        notesField.setPromptText("Notes");
    
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            //creating and saving a Prescription
            Prescription prescription = new Prescription(
                    String.valueOf(System.currentTimeMillis()), //generate a unique ID, may be unnecessary
                    user.toString(),
                    patientUsernameField.getText(),
                    medicationNameField.getText(),
                    dosageField.getText(),
                    frequencyField.getText(),
                    issueDateField.getText(),
                    expiryDateField.getText(),
                    Integer.parseInt(refillsField.getText()), // Basic parsing
                    notesField.getText());
    
            savePrescription(prescription);
            display(window, user); // Switch back to the main dashboard
        });
    
        layout.getChildren().addAll(patientUsernameField, medicationNameField, dosageField, frequencyField,
                issueDateField, expiryDateField, refillsField, notesField, submitButton);
    
        Scene scene = new Scene(layout, 400, 600);
        window.setScene(scene);
    }
    
    private static void savePrescription(Prescription prescription) {
        try {
            FileWriter writer = new FileWriter("prescriptions.txt", true); // Append mode
            writer.write(prescription.toString() + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void displayAppointmentForm(Stage window, User user) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        // Patient Username
        Label patientUsernameLabel = new Label("Patient Username:");
        grid.add(patientUsernameLabel, 0, 0);
        TextField patientUsernameField = new TextField();
        grid.add(patientUsernameField, 1, 0);
        
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
            String patientUsername = patientUsernameField.getText();
            String date = (datePicker.getValue() != null) ? datePicker.getValue().toString() : "";
            String time = timeField.getText();
            String purpose = purposeField.getText();
        
            // Basic validation
            if (patientUsername.isEmpty() || date.isEmpty() || time.isEmpty() || purpose.isEmpty()) {
                System.out.println("All fields must be filled out.");
                return; // Stop processing the event here
            }
        
            // Time validation (basic format check)
            if (!time.matches("\\d{2}:\\d{2}")) {
                System.out.println("Time must be in the format HH:MM.");
                return; // Stop processing the event here
            }

            if(patientUsernameField.getText().isEmpty() || datePicker.getValue() == null) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText("Missing Information");
                errorAlert.setContentText("Please fill out all fields.");
                errorAlert.showAndWait();
            } else {
                // Create the Appointment object
                // Add this appointment to the doctor's and patient's appointment lists and save to the database
                Appointment appointment = new Appointment(user.toString(), patientUsername, date, time, purpose);
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Success");
                successAlert.setHeaderText(null);
                successAlert.setContentText("Appointment successfully scheduled.");
                successAlert.showAndWait();

                patientUsernameField.clear();
                datePicker.setValue(null);
                timeField.clear();
                purposeField.clear();

                if (user instanceof Doctor) {
                    Doctor doctor = (Doctor) user;
                    appointmentsTable.setItems(getDoctorAppointments(doctor));
                    appointmentsTable.refresh();
                } 
                
                display(window, user); // Switch back to the main dashboard

                // Fetch doctor and patient from database
                Map<String, User> users = Database.getuser();

                // Assuming 'user' is the username of the doctor
                User possibleDoctor = users.get(user.toString());
                Doctor doctor = null;
                if (possibleDoctor instanceof Doctor) {
                    doctor = (Doctor) possibleDoctor;
                }

                Patient patient = null;
                if (users.get(patientUsername) instanceof Patient) {
                    patient = (Patient) users.get(patientUsername);
                }

                if (doctor != null && patient != null) {
                    doctor.addAppointment(appointment);
                    // Assuming patients also need to track their appointments
                    // patient.addAppointment(appointment); //might want to add a similar method to Patient class if necessary

                    // Save changes back to the database
                    Database.saveData();
                    System.out.println("Appointment successfully added.");
                } else {
                    System.out.println("Doctor or patient not found.");
                    // Show an error message to the user in the GUI
                }
            }
        });       
    }
}
