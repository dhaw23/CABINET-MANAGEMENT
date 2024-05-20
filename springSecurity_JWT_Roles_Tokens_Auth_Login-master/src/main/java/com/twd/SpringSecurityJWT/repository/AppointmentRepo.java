package com.twd.SpringSecurityJWT.repository;

import com.twd.SpringSecurityJWT.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepo  extends JpaRepository<Long,Appointment> {

    @Query("select u from  Appointment  u  where u.user.id= :id_appointment")
    List<Appointment> findByappointmentAndPatient(@Param("id_appointment") Long id) ;

}
