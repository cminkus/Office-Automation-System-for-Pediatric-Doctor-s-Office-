package Phase3Implementation;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PatientOverview extends Application {

    
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Patient Overview");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Example patient data
        String[] patientInfo = {"John Doe", "01/01/1990", "Nuts", "Aspirin", "None", "General Checkup", "XYZ Insurance", "123-456-7890"};

        grid.add(new Label("Name:"), 0, 0);
        grid.add(new Label(patientInfo[0]), 1, 0);

        grid.add(new Label("Date of Birth:"), 0, 1);
        grid.add(new Label(patientInfo[1]), 1, 1);

        grid.add(new Label("Allergies:"), 0, 2);
        grid.add(new Label(patientInfo[2]), 1, 2);

        grid.add(new Label("Medication:"), 0, 3);
        grid.add(new Label(patientInfo[3]), 1, 3);

        grid.add(new Label("Immunizations:"), 0, 4);
        grid.add(new Label(patientInfo[4]), 1, 4);

        grid.add(new Label("Visit Summary:"), 0, 5);
        grid.add(new Label(patientInfo[5]), 1, 5);

        grid.add(new Label("Insurance:"), 0, 6);
        grid.add(new Label(patientInfo[6]), 1, 6);

        grid.add(new Label("Contact Number:"), 0, 7);
        grid.add(new Label(patientInfo[7]), 1, 7);

        Button editButton = new Button("Edit Information");
        grid.add(editButton, 1, 8);

       

        Scene scene = new Scene(grid, 450, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
