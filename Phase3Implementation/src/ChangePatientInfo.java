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
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ChangePatientInfo {
	//This class represents the funcitonality to change patient information
	public void start(Stage primaryStage, User user) {
		primaryStage.setTitle("Change Patient Information");
        GridPane grid = new GridPane();
        Color backColor = Color.web("#B0EADD");
        grid.setStyle("-fx-background-color: #" + backColor.toString().substring(2, 8) + ";");
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(11);
        grid.setVgap(11);
        grid.setPadding(new Insets(25, 25, 25, 25));
        // Text fields are provided for users to input the new email and phone number
        TextField newEmailField = new TextField();
        TextField newPhoneField = new TextField();
        grid.add(new Label("Enter new or current Email:"), 0, 0);
        grid.add(newEmailField, 1, 0);
        grid.add(new Label("Enter new or current Phone Number:"), 0, 1);
        grid.add(newPhoneField, 1, 1);
        Button submitButton = new Button("Submit");
        grid.add(submitButton, 1, 2);
        Button backButton = new Button("Back");
        grid.add(backButton, 2, 2);
        backButton.setOnAction(event -> {
        	PatientDashboard patientDash = new PatientDashboard();
        	patientDash.display(primaryStage, user);
        });
    	// Submit action handler checks if both email and phone number fields are filled. If not, it displays an alert
        submitButton.setOnAction(event -> {
        	if (newEmailField.getText().isEmpty() || newPhoneField.getText().isEmpty()) {
        		Utility.alert("Missing Field","Please fill out both fields.");
        	}
        	else {
            	// If both fields are filled, it reads the existing patient information from the file, deletes the old file, and creates a new file with updated information
        		String oldFileName = "src/Phase3Implementation/" + user.getID() + "_PatientFile.txt";
                readFile reader = new readFile();
                reader.mainRead(oldFileName);
                
                File oldFile = new File(oldFileName);
                oldFile.delete();
                
                String newFileName =  "src/Phase3Implementation/" + user.getID() + "_PatientFile.txt";
	            try {
	            		
	            		File newFile = new File(newFileName);
	            		
	            		FileWriter writer = new FileWriter(newFile);
	            		writer.write("Patient ID: " + user.getID() + "\n");
	            		writer.write("First Name: " + reader.getFirstName() + "\n");
	            		writer.write("Last Name: " + reader.getLastName() + "\n");
	            		writer.write("Date of Birth: " + reader.getDOB() + "\n");
	            		writer.write("Email: " + newEmailField.getText() + "\n");
	            		writer.write("Phone number: " + newPhoneField.getText() + "\n");
	            		writer.write("Allergies: " + reader.getAllergies() + "\n");
	            		writer.write("Pharmacy: " + reader.getPharmacy() + "\n");
	            		writer.write("Insurance Information: " + reader.getPatientInsurance() + "\n");
	            		writer.write("Health History: " + reader.getPatientHH() + "\n");
	            		writer.close();
			    	Utility.alert("Success", "Email/Phone number successfully changed");
	            	}catch(IOException e){
	            		e.printStackTrace();
	            	} 
        	}
        	
        });
        
        Scene scene = new Scene(grid, 500, 200);
        primaryStage.setWidth(500);
        primaryStage.setHeight(200);
        primaryStage.setScene(scene);
        primaryStage.show();
	}
}
