package com.twd.SpringSecurityJWT.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "medicalfile")
public class Medicalfile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id_medicalfile;
    private Integer id_doctor;
    private Integer id_patient;
    private String docdescription;
    private String weight;
    private String height;
    private String Age;

    public long getId_medicalfile() {
        return id_medicalfile;
    }

    public void setId_medicalfile(long id_medicalfile) {
        this.id_medicalfile = id_medicalfile;
    }

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

    public String getDocdescription() {
        return docdescription;
    }

    public void setDocdescription(String docdescription) {
        this.docdescription = docdescription;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }
}
