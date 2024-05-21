package com.twd.SpringSecurityJWT.repository;

import com.twd.SpringSecurityJWT.entity.Appointment;
import com.twd.SpringSecurityJWT.entity.OurUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepo  extends JpaRepository<Appointment,Long> {
    @Query("SELECT a FROM Appointment a WHERE a.id_patient = :idpatient%")
    List<Appointment> findpatientWithid(@Param("idpatient") Long idpatient);

    @Query("SELECT a FROM Appointment a WHERE a.id_doctor = :iddoctor%")
    List<Appointment> finddocotrWithid(@Param("iddoctor") Long iddoctor);
}



