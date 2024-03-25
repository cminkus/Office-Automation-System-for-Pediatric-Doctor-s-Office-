import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ChatScreen extends Application {

    private TextArea chatHistory;
    private TextField messageInput;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chat with Doctor/Nurse");

        BorderPane layout = new BorderPane();
        layout.setPadding(new Insets(10));

        chatHistory = new TextArea();
        chatHistory.setEditable(false);
        chatHistory.setWrapText(true);

        messageInput = new TextField();
        messageInput.setPromptText("Type your message here...");

        Button sendButton = new Button("Send");
        sendButton.setOnAction(event -> handleSendMessage());

        HBox bottomLayout = new HBox(10);
        bottomLayout.setAlignment(Pos.CENTER);
        bottomLayout.getChildren().addAll(messageInput, sendButton);

        layout.setCenter(chatHistory);
        layout.setBottom(bottomLayout);

        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleSendMessage() {
        String message = messageInput.getText();
        if (!message.isEmpty()) {
            chatHistory.appendText("You: " + message + "\n");
            messageInput.clear();
            // Here you would typically send the message to the server or the other chat participant
        }
    }

}
