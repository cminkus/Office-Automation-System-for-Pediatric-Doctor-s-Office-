import java.io.File; 
import java.io.FileNotFoundException;
import java.util.Scanner;



public class readFile{	
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
	
	public void mainRead(String fileName) {
		String filePath = "src/Phase3Implementation/" + fileName;
		File file = new File(filePath);
		read(file);
	}
	
	public static void read(File file) {
		
		
		try(Scanner scan = new Scanner (file)){
	    		while(scan.hasNextLine()) {
	    			String line = scan.nextLine();
	    			String[] parts = line.split(": ");
	    			if(parts.length == 2) {
	    				String key = parts[0];
	    				String value = parts[1];
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
	    		
	    			}
	    		}
	    		pFullName = patientFirst + " " + patientLast;
	    }catch(FileNotFoundException e) {
	    		e.printStackTrace();
	    	}
	}
	
	public String getFullName() {
		return pFullName;
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
}