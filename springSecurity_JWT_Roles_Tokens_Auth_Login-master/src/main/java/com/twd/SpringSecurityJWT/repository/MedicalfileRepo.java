package com.twd.SpringSecurityJWT.repository;


import com.twd.SpringSecurityJWT.entity.Appointment;
import com.twd.SpringSecurityJWT.entity.Medicalfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedicalfileRepo extends JpaRepository<Medicalfile,Long> {

    @Query("SELECT a FROM Medicalfile a WHERE a.id_patient = :idpatient%")
    List<Medicalfile> findpatientWithid(@Param("idpatient") Long idpatient);

    @Query("SELECT a FROM Medicalfile a WHERE a.id_doctor = :iddoctor%")
    List<Medicalfile> finddocotrWithid(@Param("iddoctor") Long iddoctor);


}
