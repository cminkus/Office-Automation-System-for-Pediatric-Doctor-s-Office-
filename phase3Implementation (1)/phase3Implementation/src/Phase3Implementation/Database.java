package Phase3Implementation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class Database {
	
	// Manages the serialization and deserialization of user data to and from a file named "dataBase.ser"
	// Utilizes a HashMap to store user data, with the username as the key and the corresponding User object as the value
	private static final String FILENAME = "src/Phase3Implementation/dataBase.ser";
	private static Map<String, User> users = new HashMap();
	// A static initialization block ensures that data is retrieved from the file when the class is loaded into memory
	static {
        retrieveData(); 
        Runtime.getRuntime().addShutdownHook(new Thread(Database::saveData));	// A shutdown hook is registered to automatically save data to the file when the program exits, ensuring data persistence

    }
	
	public static Map<String, User> getuser(){
		return users;
	}
	public static void saveData( ) {
		// Serializes the user data and writes it to the file
		// Serialization is performed using an ObjectOutputStream, which writes the object data to the file in a binary format
		File file = new File(FILENAME);
		try {
	        if (!file.exists()) {
	            file.createNewFile(); 
	        }
	        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
	            out.writeObject(users);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void retrieveData( ) {
		// Deserializes the user data from the file and populates the users HashMap
		// Deserialization is performed using an ObjectInputStream, which reads the object data from the file and converts it back into Java objects
		try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILENAME))){
			users = (Map<String, User>) in.readObject();
			
		}catch(IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}

