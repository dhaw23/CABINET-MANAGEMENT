package com.twd.SpringSecurityJWT.controller;

import com.twd.SpringSecurityJWT.dto.AppointmentDTO;
import com.twd.SpringSecurityJWT.entity.Appointment;
import com.twd.SpringSecurityJWT.entity.OurUsers;
import com.twd.SpringSecurityJWT.repository.AppointmentRepo;
import com.twd.SpringSecurityJWT.repository.OurUserRepo;
import com.twd.SpringSecurityJWT.service.AppointmentService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RequestMapping("/appointment")
@CrossOrigin("*")
@RestController
public class AppointmentController {
    @Autowired
    private final AppointmentService appointmentService;
    @Autowired
    private final OurUserRepo ourUserRepo;
    @Autowired
    private final AppointmentRepo appointmentRepo;

    public AppointmentController(AppointmentService appointmentService, OurUserRepo ourUserRepo, AppointmentRepo appointmentRepo) {
        this.appointmentService = appointmentService;
        this.ourUserRepo = ourUserRepo;
        this.appointmentRepo = appointmentRepo;
    }

    @GetMapping("/getAllappointments")
    public ResponseEntity<List<Appointment>> getAllAppointments(){
        List<Appointment> appointments = appointmentService.getall();
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/getAppointment/{id}")
    public ResponseEntity<Appointment> getAppointment(@PathVariable("id") Long id){
        Appointment appointment = appointmentService.getoneappointment(id);
        if(appointment != null) {
            return ResponseEntity.ok(appointment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/addAppointment")
    public ResponseEntity<?> addAppointment(@RequestBody AppointmentDTO appointment) {
       Appointment newAppointment = new Appointment() ;
       OurUsers idPasient = ourUserRepo.findById(appointment.getId_patient()).orElseThrow(()-> new RuntimeException("no patient found"));
        OurUsers idDocteur = ourUserRepo.findById(appointment.getId_doctor()).orElseThrow(()-> new RuntimeException("no docteur found"));
        newAppointment.setId_patient(idPasient.getId());
        newAppointment.setId_doctor(idDocteur.getId());
        newAppointment.setDateofChecking(appointment.getDateofChecking());
        newAppointment.setNotification(appointment.isNotification());
        newAppointment.setDescription(appointment.getDescription());
        newAppointment.setTypeofIllness(appointment.getTypeofIllness());
        appointmentRepo.save(newAppointment);
        System.out.println("newAppointment :"+newAppointment.getId_appointment());
        return ResponseEntity.ok(newAppointment);
    }

    @GetMapping("/getAppointmentsByDoctor")
    public ResponseEntity<List<Appointment>> getAppointmentsByDoctor(@PathVariable("id") long id){
        List<Appointment> appointments = appointmentService.getAllAppointmentbydocotrs(id);
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/getAppointmentsByPatient")
    public ResponseEntity<List<Appointment>> getAppointmentsByPatient(@PathVariable("id") long id){
        List<Appointment> appointments = appointmentService.getAllAppointmentbypatients(id);
        return ResponseEntity.ok(appointments);
    }

    @DeleteMapping("/deleteAppointment/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable("id") Long id){
        appointmentService.deleteappointment(id);
        return ResponseEntity.noContent().build();
    }
}
