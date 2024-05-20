package com.twd.SpringSecurityJWT.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "appointment")
public class Appointment {

    @Id
    private int id_appointment;
    private String dateofChecking;
    private String dateofAppointment;
    private String description;
    private String typeofIllness;
    private boolean notification;




    public Appointment() {

    }

    public int getId_appointment() {

        return id_appointment;
    }

    public void setId_appointment(int id_appointment) {

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

    @ManyToOne()
    private OurUsers ourUsers ;

    public OurUsers getUser() {
        return ourUsers;
    }

    public void setUser(OurUsers ourUsers) {
        this.ourUsers = ourUsers;
    }

}

