package Phase3Implementation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionPage {
    private List<Prescription> prescriptions = new ArrayList<>();
    private String patientID;
    private ListView<String> prescriptionListView;

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public void start(Stage primaryStage, User user) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);
        Color backColor = Color.web("#B0EADD");
        grid.setStyle("-fx-background-color: #" + backColor.toString().substring(2, 8) + ";");
        
        prescriptions = loadPrescriptions(patientID);
        
        
        prescriptionListView = new ListView<>();
        VBox.setMargin(prescriptionListView, new Insets(10, 0, 0, 0));
        grid.add(prescriptionListView, 0, 0, 2, 1);
        
        updatePrescriptionListView();
        
        TextField doctorUsernameField = new TextField();
        doctorUsernameField.setPromptText("Doctor Username");
        grid.add(new Label("Doctor Username*:"), 0, 1);
        grid.add(doctorUsernameField, 1, 1);

        TextField medicationNameField = new TextField();
        medicationNameField.setPromptText("Medication Name");
        grid.add(new Label("Medication Name*:"), 0, 2);
        grid.add(medicationNameField, 1, 2);

        TextField dosageField = new TextField();
        dosageField.setPromptText("Dosage");
        grid.add(new Label("Dosage*:"), 0, 3);
        grid.add(dosageField, 1, 3);

        TextField frequencyField = new TextField();
        frequencyField.setPromptText("Frequency");
        grid.add(new Label("Frequency*:"), 0, 4);
        grid.add(frequencyField, 1, 4);

        DatePicker issueDatePicker = new DatePicker();
        grid.add(new Label("Issue Date*:"), 0, 5);
        grid.add(issueDatePicker, 1, 5);

        DatePicker expiryDatePicker = new DatePicker();
        grid.add(new Label("Expiry Date*:"), 0, 6);
        grid.add(expiryDatePicker, 1, 6);

        Spinner<Integer> refillsSpinner = new Spinner<>(0, Integer.MAX_VALUE, 0);
        grid.add(new Label("Refills*:"), 0, 7);
        grid.add(refillsSpinner, 1, 7);

        TextArea notesArea = new TextArea();
        notesArea.setPromptText("Notes");
        grid.add(new Label("Notes:"), 0, 8);
        grid.add(notesArea, 1, 8);

        Button submitButton = new Button("Submit");
        grid.add(submitButton, 0, 9);

        submitButton.setOnAction(event -> {
        	if(doctorUsernameField.getText().isEmpty()) {
        		Utility.alert("Missing Fields", "Please check that all required fields (*) are filled.");
        	}else if(medicationNameField.getText().isEmpty()) {
        		Utility.alert("Missing Fields", "Please check that all required fields (*) are filled.");
        	}else if(dosageField.getText().isEmpty()) {
        		Utility.alert("Missing Fields", "Please check that all required fields (*) are filled.");
        	}else if(frequencyField.getText().isEmpty()) {
        		Utility.alert("Missing Fields", "Please check that all required fields (*) are filled.");
        	}else if(issueDatePicker.getValue() == null) {
        		Utility.alert("Missing Fields", "Please check that all required fields (*) are filled.");
        	}else if(expiryDatePicker.getValue() == null) {
        		Utility.alert("Missing Fields", "Please check that all required fields (*) are filled.");
        	}else if(refillsSpinner.getValue() == null) {
        		Utility.alert("Missing Fields", "Please check that all required fields (*) are filled.");
        	}else {
	            String doctorUsername = doctorUsernameField.getText();
	            String medicationName = medicationNameField.getText();
	            String dosage = dosageField.getText();
	            String frequency = frequencyField.getText();
	            String issueDate = issueDatePicker.getValue().toString();
	            String expiryDate = expiryDatePicker.getValue().toString();
	            int refills = refillsSpinner.getValue();
	            String notes = notesArea.getText();
	
	            Prescription prescription = new Prescription(doctorUsername, patientID, medicationName,
	                    dosage, frequency, issueDate, expiryDate, refills, notes);
	            prescriptions.add(prescription);
	            
	            savePrescriptions();
	
	            updatePrescriptionListView();
	
	            doctorUsernameField.clear();
	            medicationNameField.clear();
	            dosageField.clear();
	            frequencyField.clear();
	            issueDatePicker.setValue(null);
	            expiryDatePicker.setValue(null);
	            refillsSpinner.getValueFactory().setValue(0);
	            notesArea.clear();
	            
        	}
        });

        Button backButton = new Button("Back");
        grid.add(backButton,3,9);
        backButton.setOnAction(event -> {
            PatientSearch.start(primaryStage, user, null);

        });

        Scene scene = new Scene(grid, 750, 600);
        primaryStage.setScene(scene);
        primaryStage.setWidth(750); 
        primaryStage.setHeight(600); 
        primaryStage.setTitle("Prescription Form");
        primaryStage.show();
    }

    private void updatePrescriptionListView() {
        ObservableList<String> prescriptionItems = FXCollections.observableArrayList();
        for (Prescription prescription : prescriptions) {
            if (prescription.getPatientID().equals(patientID)) {
                prescriptionItems.add(prescription.toString());
            }
        }
        prescriptionListView.setItems(prescriptionItems);
    }
    private void savePrescriptions() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("prescriptions.dat"))) {
            oos.writeObject(prescriptions);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private List<Prescription> loadPrescriptions(String patientID) {
        List<Prescription> loadedPrescriptions = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("prescriptions.dat"))) {
            Object obj = ois.readObject();
            if (obj instanceof List) {
                List<Prescription> allPrescriptions = (List<Prescription>) obj;
                for (Prescription prescription : allPrescriptions) {
                    if (prescription.getPatientID().equals(patientID)) {
                        loadedPrescriptions.add(prescription);
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return loadedPrescriptions;
    }
    
}
