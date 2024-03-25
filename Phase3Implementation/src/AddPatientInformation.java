import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AddPatientInformation extends Application {

    
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Add Patient Information");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        TextField firstNameField = new TextField();
        TextField lastNameField = new TextField();
        TextField phoneField = new TextField();
        TextField allergiesField = new TextField();
        TextField medicationField = new TextField();
        TextField insuranceField = new TextField();
        TextField pharmacyField = new TextField();
        TextField summaryField = new TextField();

        grid.add(new Label("First Name:"), 0, 0);
        grid.add(firstNameField, 1, 0);
        grid.add(new Label("Last Name:"), 0, 1);
        grid.add(lastNameField, 1, 1);
        grid.add(new Label("Phone Number:"), 0, 2);
        grid.add(phoneField, 1, 2);
        grid.add(new Label("Allergies:"), 0, 3);
        grid.add(allergiesField, 1, 3);
        grid.add(new Label("Medication:"), 0, 4);
        grid.add(medicationField, 1, 4);
        grid.add(new Label("Insurance:"), 0, 5);
        grid.add(insuranceField, 1, 5);
        grid.add(new Label("Pharmacy:"), 0, 6);
        grid.add(pharmacyField, 1, 6);
        grid.add(new Label("Summary of Appointment:"), 0, 7);
        grid.add(summaryField, 1, 7);

        Button submitButton = new Button("Submit");
        grid.add(submitButton, 1, 8);

        submitButton.setOnAction(event -> {
            //handle collecting data here...
            System.out.println("Submitting patient information...");
        });

        Scene scene = new Scene(grid, 450, 450);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
