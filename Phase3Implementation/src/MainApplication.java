package Phase3Implementation;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApplication extends Application {

    private Stage window;
    private Scene doctorScene, nurseScene, patientScene;

   
    public void start(Stage primaryStage) {
        window = primaryStage;

        // Doctor's dashboard
        VBox doctorLayout = new VBox(20);
        Label doctorLabel = new Label("Welcome, Doctor!");
        doctorLayout.getChildren().add(doctorLabel);
        doctorScene = new Scene(doctorLayout, 300, 200);

        // Nurse's dashboard
        VBox nurseLayout = new VBox(20);
        Label nurseLabel = new Label("Welcome, Nurse!");
        nurseLayout.getChildren().add(nurseLabel);
        nurseScene = new Scene(nurseLayout, 300, 200);

        // Patient's dashboard
        VBox patientLayout = new VBox(20);
        Label patientLabel = new Label("Welcome, Patient!");
        patientLayout.getChildren().add(patientLabel);
        patientScene = new Scene(patientLayout, 300, 200);

        // Login simulation (replace with actual login logic)
        String role = "Doctor"; // Simulate the user role here

        // Direct users to the respective dashboard based on their role
        switch (role) {
            case "Doctor":
                window.setScene(doctorScene);
                break;
            case "Nurse":
                window.setScene(nurseScene);
                break;
            case "Patient":
                window.setScene(patientScene);
                break;
            default:
                System.out.println("Role not recognized");
                break;
        }

        window.setTitle("Healthcare Management System");
        window.show();
    }

}
