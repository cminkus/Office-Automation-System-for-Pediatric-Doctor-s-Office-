package Phase3Implementation;

import java.io.File;
import java.io.Serializable;
import java.util.UUID;

public class User implements Serializable {
    // Class representing a User in the system
    protected String ID;
    protected String username;
    protected String password;
    protected String role;
   
    // Constructor to create a User object
    public User(String username, String password, String role) {
    	this.ID = IDgen();
    	this.username = username;
        this.password = password;
        this.role = role;
    }

    // Method to check if the User login credentials match
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    // Getters and Setters for username, password, and role
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    // Method to generate a random, alphanumeric User ID
    private String IDgen() {
    	return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 7);
    }
    
    public String getID() {
    	return ID;
    }
    
    // Method to get the filename associated with the User (if applicable)
	public String getFileName(String role) {
    	String fileName = ID + "_patientFile.txt";
    	File file = new File(fileName);
    	if(role.equals("patient") && file.exists()) {
	    	return fileName;
    	}
    	return null;
    }
    
}
