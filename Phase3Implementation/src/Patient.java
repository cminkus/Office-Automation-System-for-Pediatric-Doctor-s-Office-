package Phase3Implementation;
import java.util.ArrayList;
import java.util.List;

public class Patient extends User {
    private List<String> medicalRecords;

    public Patient(String username, String password) {
        super(username, password, "Patient");
        this.medicalRecords = new ArrayList<>();
    }

    @Override
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public void addMedicalRecord(String record) {
        medicalRecords.add(record);
    }

    public List<String> getMedicalRecords() {
        return medicalRecords;
    }
}
