import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class VitalsPage extends Application {


    public void start(Stage primaryStage) {
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
            // Here you would collect the data and handle the submission
            System.out.println("Submitting vitals...");
        });

        Scene scene = new Scene(grid, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    
}
