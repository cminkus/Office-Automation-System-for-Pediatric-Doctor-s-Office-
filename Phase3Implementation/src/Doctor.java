package Phase3Implementation;
import java.util.ArrayList;
import java.util.List;

public class Doctor extends User {
    public List<Appointment> appointments; // Changed to Appointment objects

    public Doctor(String username, String password, String role) {
        super(username, password, role);
        this.appointments = new ArrayList<>();
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public void cancelAppointment(String appointmentId) {
        appointments.removeIf(appointment -> appointment.getAppointmentId().equals(appointmentId));
    }

    public List<Appointment> getAppointments() {
        return new ArrayList<>(appointments);
    }
    
    public Appointment getCurrentAppointment() {
    	return appointments.getFirst();
    }

    @Override
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
}