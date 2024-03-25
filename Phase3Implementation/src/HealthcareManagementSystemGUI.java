import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HealthcareManagementSystemGUI extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Healthcare Management System");

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);

        Label welcomeLabel = new Label("Welcome to the Healthcare Management System");

        // Login form
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Label loginStatusLabel = new Label();

        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            User user = authenticateUser(username, password);
            if (user != null) {
                loginStatusLabel.setText("Login Successful as " + user.getRole());
                redirectToDashboard(user);
            } else {
                loginStatusLabel.setText("Login Failed. Please try again.");
            }
        });

        root.getChildren().addAll(welcomeLabel, usernameField, passwordField, loginButton, loginStatusLabel);

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private User authenticateUser(String username, String password) {
        // Assuming Doctor, Nurse, and Patient classes are properly defined
        if (username.equals("doctor") && password.equals("docpass")) {
            return new Doctor(username, password);
        } else if (username.equals("nurse") && password.equals("nursepass")) {
            return new Nurse(username, password);
        } else if (username.equals("patient") && password.equals("patientpass")) {
            return new Patient(username, password);
        }
        return null;
    }

    private void redirectToDashboard(User user) {
        if (user instanceof Doctor) {
            DoctorDashboard.display(primaryStage);
        } else if (user instanceof Nurse) {
            NurseDashboard.display(primaryStage);
        } else if (user instanceof Patient) {
            PatientDashboard.display(primaryStage);
        }
    }
}
