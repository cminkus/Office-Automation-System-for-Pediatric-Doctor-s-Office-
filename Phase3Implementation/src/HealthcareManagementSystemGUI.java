package Phase3Implementation;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class HealthcareManagementSystemGUI extends Application {
	// Represents the graphical user interface (GUI) of the healthcare management system
	// Consists of a login screen where users can enter their username and password to access the system
    private Stage primaryStage;

    private Map<String, User> users = Database.getuser();
    
    
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Healthcare Management System");
        // Retrieves user data from the database using the Database class, allowing users to authenticate
        Database.retrieveData();
        users = Database.getuser();
        
        VBox root = new VBox();
        Color backColor = Color.web("#B0EADD");
        root.setStyle("-fx-background-color: #" + backColor.toString().substring(2, 8) + ";");
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);

        Label welcomeLabel = new Label("Welcome to the Healthcare Management System");
        welcomeLabel.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 18px;-fx-underline: true;");
       

        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter username");
        usernameField.setMaxWidth(200);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter password");
        passwordField.setMaxWidth(200);
    	// Users can either log in using existing credentials or register for a new account by clicking the "Register" button
        Button loginButton = new Button("Login");
        loginButton.setOnAction(event -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            User user = authenticateUser(username, password);
            // If the authentication is successful, the user is redirected to their respective dashboard based on their role (Doctor, Nurse, or Patient)
    	    // If authentication fails, an alert message is displayed, indicating incorrect credentials or the need for registration
            if (user != null) {
            	Utility.alert("Success", "Login successful!");
                redirectToDashboard(user);
            } else {
            		Utility.alert("Login Failed", "Username or Password incorrect. If you've already registered, check your user credentials. If you have not registered, visit the registration page.");
            }
        });

        Button registerButton = new Button("Register");
        registerButton.setOnAction(event -> {
            RegistrationScreen registrationScreen = new RegistrationScreen();
            registrationScreen.start(new Stage());  // Open the registration screen in a new window
        });

        root.getChildren().addAll(welcomeLabel, usernameField, passwordField, loginButton, registerButton);

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setWidth(400);
        primaryStage.setHeight(300);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        primaryStage.setOnCloseRequest(event -> {
            // Save data before closing the application
            Database.saveData();
        });
    }

    private User authenticateUser(String username, String password) {
        if(users.containsKey(username)) {
        	User user = users.get(username);
        	if(user.getPassword().equals(password)) {
        		return user;
        	}
        }
        return null;
    }

    private void redirectToDashboard(User user) {
        switch (user.getRole()) {
            case "Doctor":
                DoctorDashboard.display(primaryStage ,user);
                break;
            case "Nurse":
                NurseDashboard.display(primaryStage ,user);
                break;
            case "Patient":
                PatientDashboard.display(primaryStage ,user);
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
