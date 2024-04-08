package Phase3Implementation;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class VitalsPage {


    public void start(Stage primaryStage, User user, Appointment appointment) {
        primaryStage.setTitle("Enter Vitals");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        TextField bloodPressureField = new TextField();
        TextField temperatureField = new TextField();
        TextField weightField = new TextField();
        TextField heightField = new TextField();

        grid.add(new Label("Blood Pressure:"), 0, 0);
        grid.add(bloodPressureField, 1, 0);
        grid.add(new Label("Body Temperature:"), 0, 1);
        grid.add(temperatureField, 1, 1);
        grid.add(new Label("Weight:"), 0, 2);
        grid.add(weightField, 1, 2);
        grid.add(new Label("Height:"), 0, 3);
        grid.add(heightField, 1, 3);

        Button submitButton = new Button("Submit");
        grid.add(submitButton, 1, 4);

        submitButton.setOnAction(event -> {
        	if(bloodPressureField.getText().isEmpty()) {
             	Utility.alert("Missing Field","Blood pressure is missing.");
             }else if(temperatureField.getText().isEmpty()) {
             	Utility.alert("Missing Field","Temperature is missing.");
             }else if(weightField.getText().isEmpty()) {
             	Utility.alert("Missing Field","Weight is missing.");
             }else if(heightField.getText().isEmpty()) {
             	Utility.alert("Missing Field","Height is missing.");
             }else if(appointment.equals(null)) {
            	 Utility.alert("No Appointment","No Current Appointment");
             }else {
            	 Utility.alert("Success", "Vitals information successfuly created and saved!");
            	 String fileName = "src/Phase3Implementation/" + appointment.getAppointmentId() + "_" + user.getID() + "_Vitals.txt";
            	 try {
	            		File file = new File(fileName);
	            		FileWriter writer = new FileWriter(file);
	            		writer.write("Blood Pressure: " + bloodPressureField.getText() + "\n");
	            		writer.write("Temperature: " + temperatureField.getText() + "\n");
	            		writer.write("Weight: " + weightField.getText() + "\n");
	            		writer.write("Height: " + heightField.getText() + "\n");
	            		writer.close();
	            	}catch(IOException e){
	            		e.printStackTrace();
	            	}
             }
       });

        Scene scene = new Scene(grid, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    
}
