package Phase3Implementation;

import java.util.ArrayList;

public class Appointment {
    // This class represents an appointment in the management system
    private String doctorUsername;
    private String patientId; 
    private String appointmentDate; 
    private String appointmentTime; 
    private String purpose;
    
    // an ArrayList of appointments is maintained statically to keep track of all appointments
    private static ArrayList<Appointment> appointments = new ArrayList<>();

    public Appointment(String doctorUsername, String patientId, String appointmentDate, String appointmentTime, String purpose) {
        
        this.doctorUsername = doctorUsername;
        this.patientId = patientId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.purpose = purpose;
    }
    
    public static ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    public static void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }
    
    public String getDoctorUsername() {
        return doctorUsername;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public String getPurpose() {
        return purpose;
    }
   
    public String toString() {
        return 
                "Doctor Username = " + doctorUsername + 
                ", Patient ID = " + patientId + 
                ", Appointment Date = " + appointmentDate + 
                ", Appointment Time = " + appointmentTime + 
                ", Purpose of Visit = " + purpose   +
                " }";
    }
}
