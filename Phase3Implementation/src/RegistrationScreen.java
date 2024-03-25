import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class RegistrationScreen extends Application {

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Registration");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // User input fields
        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        ComboBox<String> roleComboBox = new ComboBox<>();
        roleComboBox.getItems().addAll("Doctor", "Nurse", "Patient");

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

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("credentials.txt", true))) {
                writer.write(username + "," + password + "," + role + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
