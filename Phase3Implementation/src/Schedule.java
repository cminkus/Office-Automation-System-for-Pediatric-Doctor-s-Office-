package Phase3Implementation;

import java.time.LocalDate;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Schedule {
	// Handles the scheduling of appointments
	public static void schedule(Stage window, User user, String id) {
        VBox layout = new VBox(10);
        Color backColor = Color.web("#B0EADD");
        layout.setStyle("-fx-background-color: #" + backColor.toString().substring(2, 8) + ";");
        

        Label title = new Label("SCHEDULE APPOINTMENT");
        title.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 18px;-fx-underline: true;");
        title.setTranslateX(30);
        
        TextField StaffUserField = new TextField();
        StaffUserField.setPromptText("Enter Staff Username");
        StaffUserField.setMaxWidth(200);
        StaffUserField.setTranslateX(50);

        DatePicker dateField = new DatePicker(); 
        dateField.setPromptText("Select Date");
        dateField.setMaxWidth(200);
        dateField.setTranslateX(50);
        
        // Disables past dates from being scheduled
	dateField.setDayCellFactory(picker -> new DateCell() {
    		public void updateItem(LocalDate date, boolean empty) {
    			super.updateItem(date, empty);
    			LocalDate today = LocalDate.now();
    			setDisable(empty || date.compareTo(today) < 0);
    		}
    	});

        // Creating and setting properties of the time selection field
	ComboBox<String> timeField = new ComboBox<>(); 
        timeField.setPromptText("Select Appointment Time");
        timeField.getItems().addAll("9:00 AM", "10:00 AM", "11:00 AM", "1:00 PM", "2:00 PM", "3:00 PM", "4:00 PM","5:00 PM");
        timeField.setMaxWidth(200);
        timeField.setTranslateX(50);

        // Creating and setting properties of the purpose field
	TextField purposeField = new TextField();
        purposeField.setPromptText("Enter Purpose of Visit");
        purposeField.setPrefHeight(100);
        purposeField.setMaxWidth(200);
        purposeField.setTranslateX(50);

        Button scheduleButton = new Button("Schedule");
        scheduleButton.setOnAction(event -> {
            String StaffUser = StaffUserField.getText();
            String date = dateField.getValue().toString();
            String time = timeField.getValue().toString();
            String purpose = purposeField.getText();
            
            // Checking for time conflicts with existing appointments
	    boolean conflictFound = false;
            for (Appointment appointment1 : Appointment.getAppointments()) {
                if (appointment1.getDoctorUsername().equals(StaffUserField.getText()) &&
                    appointment1.getAppointmentDate().equals(date) &&
                    appointment1.getAppointmentTime().equals(time)) {
                    conflictFound = true;
                    break; 
                }
            }
            if (conflictFound) {
                Utility.alert("Time Conflict", "Appointment Time or Date Unavailable. Please choose another time or date.");
            } else {
                // Creating an appointment if no conflict is found
		Appointment appointment = new Appointment(StaffUser, id, date, time, purpose);
                Appointment.addAppointment(appointment);
                Utility.alert("Appointment scheduled!", "Appointment Summary:" + "\n" + appointment);
            }
        });
        scheduleButton.setTranslateX(50);
        
        Button backButton = new Button("Back");
        backButton.setOnAction(event -> {
            PatientSearch.start(window, user, null);

        });
        backButton.setTranslateX(50);

        layout.getChildren().addAll(title, StaffUserField, dateField, timeField, purposeField, scheduleButton, backButton);
        Scene scene = new Scene(layout, 300, 400);
        window.setScene(scene);
        window.setTitle("Schedule Appointment");
        window.show();
    }
	
}
