package com.twd.SpringSecurityJWT.service;

import com.twd.SpringSecurityJWT.entity.Appointment;
import com.twd.SpringSecurityJWT.entity.OurUsers;
import com.twd.SpringSecurityJWT.repository.AppointmentRepo;
import com.twd.SpringSecurityJWT.repository.OurUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class AppointmentService {


    @Autowired
    private   AppointmentRepo appointmentRepo;
    private   OurUserRepo ourUserRepo ;


    public List<Appointment> getall () {

        return  appointmentRepo.findAll();

    }
    public Appointment getoneappointment (Long id_appointment) {

        return  appointmentRepo.findById(id_appointment).orElse(null);

    }
    public Appointment addappointment ( Appointment appointment, int id_user ) {

        OurUsers ourUsers = ourUserRepo.findById(id_user).orElse(null);
        appointment.setUser(ourUsers);

        try {
            Appointment newAppointment = new Appointment();
            newAppointment.setDateofAppointment(appointment.getDateofAppointment());
            newAppointment.setUser(appointment.getUser());
            newAppointment.setTypeofIllness(appointment.getTypeofIllness());
            newAppointment.setDescription(appointment.getDescription());
            newAppointment.setNotification(appointment.isNotification());

            AppointmentRepo.save(newAppointment);

        } catch (Exception e) {
            throw new RuntimeException("FAIL!");
    }

       public List<Appointment> getByappointmentAndPatient(Long id) {
            List<Appointment> appointmentsResult = AppointmentRepo.findByappointmentAndPatient(id);
            System.out.println("==============");
            for (Appointment appointment : appointmentsResult) {
                // Log course details
                System.out.println("Course Description: " + appointment.getDescription());
                System.out.println("User ID: " + appointment.getUser().getId());
            }
            return appointmentRepo.findByappointmentAndPatient(id);
        }
    }

    public void deletecours (Long id ) {
       AppointmentRepo.deleteById(id);
    }

}

