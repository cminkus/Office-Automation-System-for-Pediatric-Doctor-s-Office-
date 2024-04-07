import java.io.Serializable;

public class Appointment implements Serializable{
    private static final long serialVersionUID = 1L; // Ensure compatibility across different JVMs
    private String appointmentId;
    private String doctorUsername;
    private String patientUsername;
    private String appointmentTime;
    private String purpose;

    // Constructor
    public Appointment(String appointmentId, String doctorUsername, String patientUsername, String appointmentTime, String purpose) {
        this.appointmentId = appointmentId;
        this.doctorUsername = doctorUsername;
        this.patientUsername = patientUsername;
        this.appointmentTime = appointmentTime;
        this.purpose = purpose;
    }

    // Getters
    public String getAppointmentId() {
        return appointmentId;
    }

    public String getDoctorUsername() {
        return doctorUsername;
    }

    public String getPatientUsername() {
        return patientUsername;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public String getPurpose() {
        return purpose;
    }

    // Setters
    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public void setDoctorUsername(String doctorUsername) {
        this.doctorUsername = doctorUsername;
    }

    public void setPatientUsername(String patientUsername) {
        this.patientUsername = patientUsername;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId='" + appointmentId + '\'' +
                ", doctorUsername='" + doctorUsername + '\'' +
                ", patientUsername='" + patientUsername + '\'' +
                ", appointmentTime='" + appointmentTime + '\'' +
                ", purpose='" + purpose + '\'' +
                '}';
    }
}
