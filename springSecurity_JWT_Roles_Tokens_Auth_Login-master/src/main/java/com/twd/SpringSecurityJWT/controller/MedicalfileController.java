package com.twd.SpringSecurityJWT.controller;


import com.twd.SpringSecurityJWT.dto.AppointmentDTO;
import com.twd.SpringSecurityJWT.dto.MedicalfileDTO;
import com.twd.SpringSecurityJWT.entity.Appointment;
import com.twd.SpringSecurityJWT.entity.Medicalfile;
import com.twd.SpringSecurityJWT.entity.OurUsers;
import com.twd.SpringSecurityJWT.repository.AppointmentRepo;
import com.twd.SpringSecurityJWT.repository.MedicalfileRepo;
import com.twd.SpringSecurityJWT.repository.OurUserRepo;
import com.twd.SpringSecurityJWT.service.AppointmentService;
import com.twd.SpringSecurityJWT.service.MedicalfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/medicalfile")
@CrossOrigin("*")
@RestController
public class MedicalfileController {

    @Autowired
    private final MedicalfileService medicalfileService;
    @Autowired
    private final OurUserRepo ourUserRepo;
    @Autowired
    private final MedicalfileRepo medicalfileRepo;

    public MedicalfileController(MedicalfileService medicalfileService, OurUserRepo ourUserRepo, MedicalfileRepo medicalfileRepo) {
        this.medicalfileService = medicalfileService;
        this.ourUserRepo = ourUserRepo;
        this.medicalfileRepo = medicalfileRepo;
    }

    @GetMapping("/getAllmedicalfiles")
    public ResponseEntity<List<Medicalfile>> getAllMedicalfiles(){
        List<Medicalfile> medicalfiles = medicalfileService.getall();
        return ResponseEntity.ok(medicalfiles);
    }

    @GetMapping("/getMedicalfile/{id}")
    public ResponseEntity<Medicalfile> getMedicalfile(@PathVariable("id") Long id){
        Medicalfile medicalfile = medicalfileService.getonemedicalfile(id);
        if(medicalfile != null) {
            return ResponseEntity.ok(medicalfile);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/addMediclafile")
    public ResponseEntity<?> addMedicalfile(@RequestBody MedicalfileDTO medicalfile) {
        Medicalfile newMedicalfile = new Medicalfile() ;
        OurUsers idPatient = ourUserRepo.findById(medicalfile.getId_patient()).orElseThrow(()-> new RuntimeException("no patient found"));
        OurUsers idDocteur = ourUserRepo.findById(medicalfile.getId_doctor()).orElseThrow(()-> new RuntimeException("no docteur found"));
        newMedicalfile.setId_patient(idPatient.getId());
        newMedicalfile.setId_doctor(idDocteur.getId());
        newMedicalfile.setAge(medicalfile.getAge());
        newMedicalfile.setHeight(medicalfile.getHeight());
        newMedicalfile.setWeight(medicalfile.getWeight());
        newMedicalfile.setDocdescription(medicalfile.getDocdescription());
       medicalfileRepo.save(newMedicalfile);
        System.out.println("newMedicalfile :"+newMedicalfile.getId_medicalfile());
        return ResponseEntity.ok(newMedicalfile);
    }

    @GetMapping("/getMedicalfilesByDoctor")
    public ResponseEntity<List<Medicalfile>> getMediclafilesByDoctor(@PathVariable("id") long id){
        List<Medicalfile> medicalfiles = medicalfileService.getAllMedicalfilebydocotrs(id);
        return ResponseEntity.ok(medicalfiles);
    }

    @GetMapping("/getMedicalfilesByPatient")
    public ResponseEntity<List<Medicalfile>> getMediclafilesByPatient(@PathVariable("id") long id){
        List<Medicalfile> medicalfiles = medicalfileService.getAllMedicalfilebypatients(id);
        return ResponseEntity.ok(medicalfiles);
    }

    @DeleteMapping("/deleteMedicalfile/{id}")
    public ResponseEntity<Void> deleteMediclafile(@PathVariable("id") Long id){
        medicalfileService.deletemediclafile(id);
        return ResponseEntity.noContent().build();
    }

}
