import java.io.File;
import java.io.Serializable;
import java.util.UUID;

public class User implements Serializable {
	protected String ID;
    protected String username;
    protected String password;
    protected String role;
   

    public User(String username, String password, String role) {
    	this.ID = IDgen();
    	this.username = username;
        this.password = password;
        this.role = role;
    }

    // Basic login functionality; can be overridden in subclasses if needed
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    // Basic getters and setters
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
    private String IDgen() {
    	
    	return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 7);
    }
    
    public String getID() {
    	return ID;
    }
    
    public String getFileName(String role) {
    	String fileName = ID + "_patientFile.txt";
    	File file = new File(fileName);
    	if(role.equals("patient") && file.exists()) {
	    	return fileName;
    	}
    	return null;
    }

    public String toString() {
        return  username;
    }
    
}
