import java.util.ArrayList;
import java.util.List;

public class Nurse extends User {
    private List<String> patientNotes;

    public Nurse(String username, String password) {
        super(username, password, "Nurse");
        this.patientNotes = new ArrayList<>();
    }

    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public void addPatientNote(String note) {
        patientNotes.add(note);
    }

    public List<String> getPatientNotes() {
        return patientNotes;
    }
}
