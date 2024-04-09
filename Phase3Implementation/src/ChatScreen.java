package Phase3Implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ChatScreen  {

	
    private TextArea chatHistory;
    private TextField messageInput;
    private String role;
    
    public void setRole(String role) {
    	this.role=role;
    }

    
    public void start(Stage primaryStage, User user) {
    	
     	automaticResponsePatient.add("Don't trust everything Google says; let's focus on your symptoms instead.");
     	automaticResponsePatient.add("Consider taking a break and resting.");
     	automaticResponsePatient.add("Rest and hydrate, it might help.");
     	automaticResponsePatient.add("Let's schedule an appointment to discuss your concerns.");
     	automaticResponsePatient.add("How can I assist you today?");
     	
     	automaticResponseNurse.add("Hello, I would like to book an appointment.");

     	automaticResponseNurse.add("I'm having trouble managing my symptoms, is there anything I can do to feel better in the meantime?.");
     	automaticResponseNurse.add("I need some medical advice. Can you assist?");
     	automaticResponseNurse.add("I'm concerned about my recent test results. Can we discuss them?");
     	

     	automaticResponseDoctor.add("What's up Doc?");
     	automaticResponseDoctor.add("I've been experiencing these symptoms for a while now, and they seem to be getting worse.");
     	automaticResponseDoctor.add("I'm concerned about the potential causes of my symptoms, could we discuss them in more detail?");
     	automaticResponseDoctor.add("I've tried natural at home remedies, but they haven't helped much. What do you suggest I do next?");
     	automaticResponseDoctor.add("I've been feeling really anxious about my health lately, and I'm not sure what steps to take. Can you provide some guidance?");
     	
     	if(role .equals( "Patient")) {
     		primaryStage.setTitle("Chat with Doctor/Nurse");
     	}else {
     		primaryStage.setTitle("Chat with Patient");
     	}
        BorderPane layout = new BorderPane();
        layout.setPadding(new Insets(10));
        Color backColor = Color.web("#B0EADD");
        layout.setStyle("-fx-background-color: #" + backColor.toString().substring(2, 8) + ";");
        

        chatHistory = new TextArea();
        chatHistory.setEditable(false);
        chatHistory.setWrapText(true);

        messageInput = new TextField();
        messageInput.setPromptText("Type your message here...");

        Button sendButton = new Button("Send");
        
		sendButton.setOnAction(event -> 
		handleSendMessage()
		);
		
		Button backButton = new Button("Back");
        backButton.setOnAction(event -> {
        	switch (role) {
            case "Doctor":
            	PatientSearch.start(primaryStage, user, null);
                break;
            case "Nurse":
            	PatientSearch.start(primaryStage, user, null);
                break;
            case "Patient":
            	PatientDashboard.display(primaryStage, user);
        	}
        });
        backButton.setTranslateX(50);

        HBox bottomLayout = new HBox(10);
        bottomLayout.setAlignment(Pos.CENTER);
        bottomLayout.getChildren().addAll(messageInput, sendButton,backButton);

        layout.setCenter(chatHistory);
        layout.setBottom(bottomLayout);

        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setWidth(400);
        primaryStage.setHeight(300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleSendMessage() {
    	
        String message = messageInput.getText();
        if (!message.isEmpty()) {
            chatHistory.appendText("You: " + message + "\n");
            messageInput.clear();
            String response = simulateResponse(role);
            if(role.equals( "Patient")) {
            	chatHistory.appendText("Doctor/Nurse: " + response+"\n");
            }else {
            	chatHistory.appendText("Patient: " + response+"\n");
            }
        }
    }
   
    private List<String> automaticResponsePatient = new ArrayList<>();
    private List<String> automaticResponseNurse = new ArrayList<>();
    private List<String> automaticResponseDoctor = new ArrayList<>();
    private Random random = new Random();
    
    private String simulateResponse(String role) {
        List<String> automaticResponseList = null;     
        switch (role) {
            case "Patient":
                automaticResponseList = automaticResponsePatient;
                break;
            case "Nurse":
                automaticResponseList = automaticResponseNurse;
                break;
            case "Doctor":
                automaticResponseList = automaticResponseDoctor;
                break;
            default:
                automaticResponseList = automaticResponsePatient;
                break;
        }
        String response = automaticResponseList.get(random.nextInt(automaticResponseList.size()));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return response;
    }
}
