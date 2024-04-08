package Phase3Implementation;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class Database {
	
	
	private static final String FILENAME = "src/Phase3Implementation/dataBase.ser";
	private static Map<String, User> users = new HashMap();
	
	static {
        retrieveData(); 
        Runtime.getRuntime().addShutdownHook(new Thread(Database::saveData));
    }
	
	public static Map<String, User> getuser(){
		return users;
	}
	public static void saveData( ) {
		try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILENAME))){
			out.writeObject(users);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void retrieveData( ) {
		try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILENAME))){
			users = (Map<String, User>) in.readObject();
		}catch(IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}

