package Phase3Implementation;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import Phase3Implementation.Utility;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RegistrationScreen extends Application {

	private Map<String, User> users = Database.getuser();
	
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Registration");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Color backColor = Color.web("#B0EADD");
        grid.setStyle("-fx-background-color: #" + backColor.toString().substring(2, 8) + ";");
        

        // User input fields
        TextField usernameField = new TextField();
        usernameField.setPromptText("Choose username");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Choose password");
        ComboBox<String> roleComboBox = new ComboBox<>();
        roleComboBox.getItems().addAll("Doctor", "Nurse", "Patient");
        roleComboBox.setPromptText("Choose role");
        // Labels
        Label usernameLabel = new Label("Username:");
        Label passwordLabel = new Label("Password:");
        Label roleLabel = new Label("Role:");

        // Registration button
        Button registerButton = new Button("Register");
        registerButton.setOnAction(new RegisterButtonHandler(usernameField, passwordField, roleComboBox));

        grid.add(usernameLabel, 0, 1);
        grid.add(usernameField, 1, 1);
        grid.add(passwordLabel, 0, 2);
        grid.add(passwordField, 1, 2);
        grid.add(roleLabel, 0, 3);
        grid.add(roleComboBox, 1, 3);
        grid.add(registerButton, 1, 4);
        

        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);
        primaryStage.setWidth(300);
        primaryStage.setHeight(275);
        primaryStage.show();
        
      
    }

    private class RegisterButtonHandler implements EventHandler<ActionEvent> {
        private final TextField usernameField;
        private final PasswordField passwordField;
        private final ComboBox<String> roleComboBox;

        public RegisterButtonHandler(TextField usernameField, PasswordField passwordField, ComboBox<String> roleComboBox) {
            this.usernameField = usernameField;
            this.passwordField = passwordField;
            this.roleComboBox = roleComboBox;
        }

        public void handle(ActionEvent event) {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String role = roleComboBox.getValue();

            if(users.containsKey(username)) {
            	Utility.alert("Failed", "User already registered. Please login instead.");
            }else if(usernameField.getText().isEmpty()){
            	Utility.alert("Missing Field", "Username missing. Please choose a username.");
            }else if(passwordField.getText().isEmpty()){
            	Utility.alert("Missing Field", "Password missing. Please choose a password.");
            }else if(roleComboBox.getValue() == null){
            	Utility.alert("Missing Field", "Role missing. Please choose a role.");
            }else{
            	User newUser = new User(username, password, role);
            	users.put(username,newUser);
            	
            	usernameField.clear();
            	passwordField.clear();
            	
            	if(!users.containsKey(username)){
                	Utility.alert("Failed Registration", "Registration Failed, Conect with a customer service representative for assistance at www.customerService.com .");
                }else {
                	Utility.alert("Sucess!", "User successfully Registered!");
                }
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

