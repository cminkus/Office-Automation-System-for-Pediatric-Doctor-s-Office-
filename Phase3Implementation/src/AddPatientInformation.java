package Phase3Implementation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AddPatientInformation  {

	// This class represents the form for adding patient information
    public void start(Stage primaryStage, User user) {
        primaryStage.setTitle("Add Patient Information");

        GridPane grid = new GridPane();
        
        Color backColor = Color.web("#D9FED3");
        grid.setStyle("-fx-background-color: #" + backColor.toString().substring(2, 8) + ";");
        
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(11);
        grid.setVgap(11);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        
        // Add elements of the form 
        TextField userIDField = new TextField();
        TextField firstNameField = new TextField();
        TextField lastNameField = new TextField();
        TextField emailField = new TextField();
        TextField phoneField = new TextField();
        TextField allergiesField = new TextField();
        TextField medicationField = new TextField();
        TextField insuranceField = new TextField();
        TextField pharmacyField = new TextField();
        TextField healthHistoryField = new TextField();
        DatePicker DOB = new DatePicker();
        
        // Date Picker is customized to disable future dates
        DOB.setDayCellFactory(picker -> new DateCell() {
    		public void updateItem(LocalDate date, boolean empty) {
    			super.updateItem(date, empty);
    			LocalDate today = LocalDate.now();
    			setDisable(empty || date.compareTo(today) > 0);
    		}
    	});

        // Adding all the labels, variables, and buttons 
        grid.add(new Label("User ID:"), 0, 0);
        grid.add(userIDField, 1, 0);
        grid.add(new Label("First Name:"), 0, 1);
        grid.add(firstNameField, 1, 1);
        grid.add(new Label("Last Name:"), 0, 2);
        grid.add(lastNameField, 1, 2);
        grid.add(new Label("Date of Birth:"), 0, 3);
        grid.add(DOB, 1, 3);
        grid.add(new Label("Email:"), 0, 4);
        grid.add(emailField, 1, 4);
        grid.add(new Label("Phone Number:"), 0, 5);
        grid.add(phoneField, 1, 5);
        grid.add(new Label("Allergies:"), 0, 6);
        grid.add(allergiesField, 1, 6);
        grid.add(new Label("Medication:"), 0, 7);
        grid.add(medicationField, 1, 7);
        grid.add(new Label("Insurance Information:"), 0, 8);
        grid.add(insuranceField, 1, 8);
        grid.add(new Label("Pharmacy:"), 0, 9);
        grid.add(pharmacyField, 1, 9);
        grid.add(new Label("Health History:"), 0, 10);
        grid.add(healthHistoryField, 1, 10);
        
        Button backButton = new Button("Back");
        backButton.setTranslateX(125);
        backButton.setTranslateY(-10);
        backButton.setOnAction(event -> {
        	switch (user.getRole()) {
            case "Doctor":
                DoctorDashboard.display(primaryStage ,user);
                break;
            case "Nurse":
                NurseDashboard.display(primaryStage ,user);
                break;
        }
        });
        grid.add(backButton, 1, 12);
        
        Button submitButton = new Button("Submit");
        grid.add(submitButton, 1, 11);
        
        // Validation is performed to ensure that all required fields are filled out before submitting the form
    	// If any field is empty, an alert message is displayed indicating the missing field
    	submitButton.setOnAction(event -> {
        	 if(userIDField.getText().isEmpty()) {
             	Utility.alert("Missing Field","User ID is missing.");
             }else if(firstNameField.getText().isEmpty()) {
             	Utility.alert("Missing Field","First name is missing.");
             }else if(lastNameField.getText().isEmpty()) {
             	Utility.alert("Missing Field","Last name is missing.");
             }else if(DOB.getValue() == null) {
              	Utility.alert("Missing Field","Date of Birth is missing.");
              }else if(emailField.getText().isEmpty()) {
             	Utility.alert("Missing Field","Email is missing.");
             }else if(phoneField.getText().isEmpty()) {
             	Utility.alert("Missing Field","Phone number is missing.");
             }else if(allergiesField.getText().isEmpty()) {
             	Utility.alert("Missing Field","Allergies are missing.");
             }else if(medicationField.getText().isEmpty()) {
             	Utility.alert("Missing Field","Medications are missing.");
             }else if(insuranceField.getText().isEmpty()) {
             	Utility.alert("Missing Field","Insurance information is missing.");
             }else if(pharmacyField.getText().isEmpty()) {
             	Utility.alert("Missing Field","Pharmacy information is missing.");
             }else if(healthHistoryField.getText().isEmpty()) {
             	Utility.alert("Missing Field","Health history is missing.");
             }else {
	            
	            Utility.alert("Success", "Patient File successfuly created and saved!");
	            // Upon submission, the patient information is saved to a text file
	            String fileName =  "src/Phase3Implementation/" + userIDField.getText() + "_PatientFile.txt";
	            try {
	            		File file = new File(fileName);
	            		FileWriter writer = new FileWriter(file);
	            		writer.write("Patient ID: " + userIDField.getText() + "\n");
	            		writer.write("First Name: " + firstNameField.getText() + "\n");
	            		writer.write("Last Name: " + lastNameField.getText() + "\n");
	            		writer.write("Date of Birth: " + DOB.getValue() + "\n");
	            		writer.write("Email: " + emailField.getText() + "\n");
	            		writer.write("Phone number: " + phoneField.getText() + "\n");
	            		writer.write("Allergies: " + allergiesField.getText() + "\n");
	            		writer.write("Pharmacy: " + pharmacyField.getText() + "\n");
	            		writer.write("Insurance Information: " + insuranceField.getText() + "\n");
	            		writer.write("Health History: " + healthHistoryField.getText() + "\n");
	            		writer.close();
	            	}catch(IOException e){
	            		e.printStackTrace();
	            	}
             }
            	
        });

        Scene scene = new Scene(grid, 450, 500);
        primaryStage.setScene(scene);
        primaryStage.setWidth(450);
        primaryStage.setHeight(500);
        primaryStage.show();
    }

}
