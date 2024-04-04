import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PatientSearch {

    public void start(Stage window) {
        VBox layout = new VBox(10);
        
        Color backColor = Color.web("#D9FED3");
        layout.setStyle("-fx-background-color: #" + backColor.toString().substring(2, 8) + ";");
        
        Label title = new Label("PATIENT SEARCH");
        title.setTranslateX(80);
        title.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-size: 18px;-fx-underline: true;");
        
        Label pID = new Label("Patient ID: ");
        pID.setTranslateX(10);
        pID.setTranslateY(20);
        pID.setStyle("-fx-font-size: 15px;");
        TextField enteredID = new TextField();
        enteredID.setPromptText("Enter Patient ID");
        enteredID.setMaxWidth(150);
        enteredID.setTranslateX(80);
        enteredID.setTranslateY(-10);
        
        Button search = new Button("Search");
        search.setTranslateX(125);
        search.setTranslateY(-10);
        
        Button contactPatient = new Button("Contact Patient");
        
        
        search.setOnAction(event ->{
        	if(!enteredID.getText().isEmpty()) { 
        		String fileName = enteredID.getText() + "_PatientFile.txt";
                System.out.println(fileName);
    	    	readFile reader = new readFile();
    	        reader.mainRead(fileName);
    	        Label nameLabel = new Label("Name: " + reader.getFullName());
    	        nameLabel.setTranslateX(20);
    	        Label dobLabel = new Label("Date of Birth: " + reader.getDOB());
    	        dobLabel.setTranslateX(20);
    	        Label emailLabel = new Label("Email: " + reader.getEmail());
    	        emailLabel.setTranslateX(20);
    	        Label phoneLabel = new Label("Phone number: " + reader.getPhone());
    	        phoneLabel.setTranslateX(20);
    	        Label allergiesLabel = new Label("Allergies: " + reader.getAllergies());
    	        allergiesLabel.setTranslateX(20);
    	        Label pharmacyLabel = new Label("Pharmacy: " + reader.getPharmacy());
    	        pharmacyLabel.setTranslateX(20);
    	        Label insuranceLabel = new Label("Insurance: " + reader.getPatientInsurance());
    	        insuranceLabel.setTranslateX(20);
    	        Label HHLabel = new Label("Health History: " + reader.getPatientHH());
    	        HHLabel.setTranslateX(20);
    	        contactPatient.setTranslateX(20);
    	        layout.getChildren().addAll(nameLabel, dobLabel,emailLabel,phoneLabel, allergiesLabel,pharmacyLabel,insuranceLabel,HHLabel,contactPatient);
    	    }
        });
      
        layout.getChildren().addAll(title, pID,enteredID,search);
        Scene scene = new Scene(layout, 300, 400);
        window.setScene(scene);
        window.setTitle("Patient Dashboard");
        window.show();
    }
    
}
