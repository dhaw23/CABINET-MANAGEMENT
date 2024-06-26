package com.twd.SpringSecurityJWT.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_appointment;
    private String dateofChecking;
    private String dateofAppointment;
    private String description;
    private String typeofIllness;
    private boolean notification;
    private Integer id_doctor;
    private Integer id_patient;

    public Integer getId_doctor() {
        return id_doctor;
    }

    public void setId_doctor(Integer id_doctor) {
        this.id_doctor = id_doctor;
    }

    public Integer getId_patient() {
        return id_patient;
    }

    public void setId_patient(Integer id_patient) {
        this.id_patient = id_patient;
    }

    public Appointment() {

    }

    public long getId_appointment() {

        return id_appointment;
    }



    public void setId_appointment(long id_appointment) {

        this.id_appointment = id_appointment;
    }

    public String getDateofChecking() {

        return dateofChecking;
    }

    public void setDateofChecking(String dateofChecking) {

        this.dateofChecking = dateofChecking;
    }

    public String getDateofAppointment() {

        return dateofAppointment;
    }

    public void setDateofAppointment(String dateofAppointment) {

        this.dateofAppointment = dateofAppointment;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypeofIllness() {

        return typeofIllness;
    }

    public void setTypeofIllness(String typeofIllness) {
        this.typeofIllness = typeofIllness;
    }

    public boolean isNotification() {

        return notification;
    }

    public void setNotification(boolean notification) {

        this.notification = notification;
    }


    private OurUsers ourUsers ;

    public OurUsers getUser() {
        return ourUsers;
    }

    public void setUser(OurUsers ourUsers) {
        this.ourUsers = ourUsers;
    }

}

