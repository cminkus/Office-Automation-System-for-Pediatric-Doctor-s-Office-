import java.util.ArrayList;
import java.util.List;

public class Doctor extends User {
    private List<String> appointments;

    public Doctor(String username, String password) {
        super(username, password, "Doctor");
        this.appointments = new ArrayList<>();
    }

    @Override
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public void addAppointment(String appointmentDetails) {
        appointments.add(appointmentDetails);
    }

    public void cancelAppointment(String appointmentDetails) {
        appointments.remove(appointmentDetails);
    }

    public List<String> getAppointments() {
        return appointments;
    }
}
