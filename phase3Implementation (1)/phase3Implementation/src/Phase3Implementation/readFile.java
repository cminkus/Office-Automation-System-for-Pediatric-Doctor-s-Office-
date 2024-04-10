package Phase3Implementation;
import java.io.File; 
import java.io.FileNotFoundException;
import java.util.Scanner;



public class readFile{	
	// Class for reading patient information from a text file
	private static String filePath;
	private static String patientID;
	private static String patientFirst="";
	private static String patientLast="";
	private static String patientDOB="";
	private static String patientEmail="";
	private static String patientPhone="";
	private static String patientAllergies="";
	private static String patientPharmacy="";
	private static String patientInsurance="";
	private static String patientHH="";
	private static String pFullName = "";
	//Method to read patient information from a file with a given filename
	public void mainRead(String fileName) {
		String filePath = fileName;
		File file = new File(filePath);
		read(file);
	}
	// Method to read patient information from a File object
	public static void read(File file) {
		
		
		try(Scanner scan = new Scanner (file)){
	    		while(scan.hasNextLine()) {
	    			String line = scan.nextLine();
	    			String[] parts = line.split(": "); 	    			// Process each key-value pair if there's a valid format (key:value)
	    			if(parts.length == 2) {
	    				String key = parts[0];
	    				String value = parts[1];
	    				// Use a switch statement to assign values based on the key
	    				switch(key){
	    					case "First Name":
	    						patientFirst = value;
	    						break;
	    					case "Last Name":
	    						patientLast=value;
	    						break;
	    					case "Date of Birth":
	    						patientDOB=value;
	    						break;
	    					case "Email":
	    						patientEmail = value;
	    						break;
	    					case "Phone number":
	    						patientPhone = value;
	    						break;
	    					case "Allergies":
	    						patientAllergies = value;
	    						break;
	    					case "Pharmacy":
	    						patientPharmacy = value;
	    						break;
	    					case "Insurance Information":
	    						patientInsurance = value;
	    						break;
	    					case "Health History":
	    						patientHH = value;
	    						break;	
	    				}
	    	    		// Construct the full patient name after reading all lines 
	    			}
	    		}
	    		pFullName = patientFirst + " " + patientLast;
	    }catch(FileNotFoundException e) {	    		// Handle the case where the file is not found
	    		e.printStackTrace();
	    	}
	}
	
	public String getFullName() {
		return pFullName;
	}
	public String getFirstName() {
		return patientFirst;
	}
	public String getLastName() {
		return patientLast;
	}
	public String getDOB() {
		return patientDOB;
	}
	public String getEmail() {
		return patientEmail;
	}
	public String getPhone() {
		return patientPhone;
	}
	public String getAllergies() {
		return patientAllergies;
	}
	public String getPharmacy() {
		return patientPharmacy;
	}
	public String getPatientInsurance() {
		return patientInsurance;
	}
	public String getPatientHH() {
		return patientHH;
	}
	public void changePhone(String newPhone) {
		patientPhone = newPhone;
	}
	public void changeEmail(String newEmail) {
		patientEmail = newEmail;
	}
}