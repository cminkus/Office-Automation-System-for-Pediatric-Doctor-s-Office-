public class Prescription {
    private String prescriptionId;
    private String doctorUsername;
    private String patientUsername;
    private String medicationName;
    private String dosage;
    private String frequency;
    private String issueDate;
    private String expiryDate;
    private int refills;
    private String notes;

    // Constructor
    public Prescription(String prescriptionId, String doctorUsername, String patientUsername,
                        String medicationName, String dosage, String frequency, 
                        String issueDate, String expiryDate, int refills, String notes) {
        this.prescriptionId = prescriptionId;
        this.doctorUsername = doctorUsername;
        this.patientUsername = patientUsername;
        this.medicationName = medicationName;
        this.dosage = dosage;
        this.frequency = frequency;
        this.issueDate = issueDate;
        this.expiryDate = expiryDate;
        this.refills = refills;
        this.notes = notes;
    }

    // Getters
    public String getPrescriptionId() {
        return prescriptionId;
    }

    public String getDoctorUsername() {
        return doctorUsername;
    }

    public String getPatientUsername() {
        return patientUsername;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public String getDosage() {
        return dosage;
    }

    public String getFrequency() {
        return frequency;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public int getRefills() {
        return refills;
    }

    public String getNotes() {
        return notes;
    }

    // Setters
    public void setPrescriptionId(String prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public void setDoctorUsername(String doctorUsername) {
        this.doctorUsername = doctorUsername;
    }

    public void setPatientUsername(String patientUsername) {
        this.patientUsername = patientUsername;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setRefills(int refills) {
        this.refills = refills;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "prescriptionId='" + prescriptionId + '\'' +
                ", doctorUsername='" + doctorUsername + '\'' +
                ", patientUsername='" + patientUsername + '\'' +
                ", medicationName='" + medicationName + '\'' +
                ", dosage='" + dosage + '\'' +
                ", frequency='" + frequency + '\'' +
                ", issueDate='" + issueDate + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", refills=" + refills +
                ", notes='" + notes + '\'' +
                '}';
    }
}
