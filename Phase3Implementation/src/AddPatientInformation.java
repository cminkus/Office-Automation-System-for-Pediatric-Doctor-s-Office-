package Phase3Implementation;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AddPatientInformation extends Application {

    
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Add Patient Information");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(11);
        grid.setVgap(11);
        grid.setPadding(new Insets(25, 25, 25, 25));

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
        

        Button submitButton = new Button("Submit");
        grid.add(submitButton, 1, 11);
        Button backButton = new Button("Back");
        grid.add(backButton, 2, 11);
        
        backButton.setOnAction(event -> {
        	Stage currentStage = (Stage) backButton.getScene().getWindow();
            currentStage.close();
        });

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
	            //handle collecting data here...
	            Utility.alert("Success", "Patient File successfuly created and saved!");
	            
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

        Scene scene = new Scene(grid, 450, 450);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}

