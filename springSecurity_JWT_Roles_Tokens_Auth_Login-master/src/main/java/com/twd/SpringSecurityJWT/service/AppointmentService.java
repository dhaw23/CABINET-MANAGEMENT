package com.twd.SpringSecurityJWT.service;

import com.twd.SpringSecurityJWT.entity.Appointment;
import com.twd.SpringSecurityJWT.entity.OurUsers;
import com.twd.SpringSecurityJWT.repository.AppointmentRepo;
import com.twd.SpringSecurityJWT.repository.OurUserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {


    private static final Logger log = LoggerFactory.getLogger(AppointmentService.class);
    @Autowired
    private AppointmentRepo appointmentRepo;

    @Autowired
    private OurUserRepo ourUserRepo;

    public List<Appointment> getall() {
        return appointmentRepo.findAll();
    }

    public Appointment getoneappointment(Long id_appointment) {
        return appointmentRepo.findById(id_appointment).orElse(null);
    }

    public Appointment addappointment(Appointment appointment) {
        try {
            Appointment newAppointment = new Appointment();
            newAppointment.setDateofAppointment(appointment.getDateofAppointment());
            newAppointment.setId_patient(appointment.getId_patient());
            newAppointment.setId_doctor(appointment.getId_doctor());
            newAppointment.setTypeofIllness(appointment.getTypeofIllness());
            newAppointment.setDescription(appointment.getDescription());
            newAppointment.setNotification(appointment.isNotification());

            // Log each field value
            log.info("Saving Appointment: " + newAppointment.toString());

            return appointmentRepo.save(newAppointment);
        } catch (Exception e) {
            log.error("Error saving appointment: ", e);
            throw new RuntimeException("FAIL!");
        }
    }

    public List<Appointment> getAllAppointmentbydocotrs(long id) {
        return appointmentRepo.finddocotrWithid(id);
    }

    public List<Appointment> getAllAppointmentbypatients(long id) {
        return appointmentRepo.findpatientWithid(id);
    }

    public void deleteappointment(Long id) {
        appointmentRepo.deleteById(id);
    }
}
