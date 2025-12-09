package com.hms.model;

public class Appointment {
    private int id;
    private String patientName;
    private String time;
    private String reason;
    private String status;

    public Appointment(int id, String patientName, String time, String reason, String status) {
        this.id = id;
        this.patientName = patientName;
        this.time = time;
        this.reason = reason;
        this.status = status;
    }

    public int getId() { return id; }
    public String getPatientName() { return patientName; }
    public String getTime() { return time; }
    public String getReason() { return reason; }
    public String getStatus() { return status; }
}
