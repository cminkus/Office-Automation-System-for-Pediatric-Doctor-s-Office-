import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Button loginButton = new Button("Login");
        loginButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            User user = authenticateUser(username, password);
            if (user != null) {
                redirectToDashboard(user);
            } else {
                System.out.println("Login Failed. Please try again.");
            }
        });

        Button registerButton = new Button("Register");
        registerButton.setOnAction(event -> {
            RegistrationScreen registrationScreen = new RegistrationScreen();
            registrationScreen.start(new Stage());  // Open the registration screen in a new window
        });

        root.getChildren().addAll(welcomeLabel, usernameField, passwordField, loginButton, registerButton);

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private User authenticateUser(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("credentials.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] credentials = line.split(",");
                if (credentials[0].equals(username) && credentials[1].equals(password)) {
                    return new User(credentials[0], credentials[1], credentials[2]); // assuming User constructor exists
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void redirectToDashboard(User user) {
        switch (user.getRole()) {
            case "Doctor":
                DoctorDashboard.display(primaryStage);
                break;
            case "Nurse":
                NurseDashboard.display(primaryStage);
                break;
            case "Patient":
                PatientDashboard.display(primaryStage);
                break;
            default:
                System.out.println("Role not recognized");
                break;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
