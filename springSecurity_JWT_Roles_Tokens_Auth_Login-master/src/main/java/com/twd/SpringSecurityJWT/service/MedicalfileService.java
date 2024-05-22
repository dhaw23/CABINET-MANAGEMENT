package com.twd.SpringSecurityJWT.service;

import com.twd.SpringSecurityJWT.entity.Appointment;
import com.twd.SpringSecurityJWT.entity.Medicalfile;
import com.twd.SpringSecurityJWT.repository.AppointmentRepo;
import com.twd.SpringSecurityJWT.repository.MedicalfileRepo;
import com.twd.SpringSecurityJWT.repository.OurUserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalfileService {


    private static final Logger log = LoggerFactory.getLogger(MedicalfileService.class);
    @Autowired
    private MedicalfileRepo medicalfileRepo;

    @Autowired
    private OurUserRepo ourUserRepo;

    public List<Medicalfile> getall() {
        return medicalfileRepo.findAll();
    }

    public Medicalfile getonemedicalfile(Long id_medicalfile) {
        return medicalfileRepo.findById(id_medicalfile).orElse(null);
    }

    public Medicalfile addmedicalfile(Medicalfile medicalfile) {
        try {
            Medicalfile newMedicalfile = new Medicalfile();
            newMedicalfile.setDocdescription(medicalfile.getDocdescription());
            newMedicalfile.setId_patient(medicalfile.getId_patient());
            newMedicalfile.setId_doctor(medicalfile.getId_doctor());
            newMedicalfile.setWeight(medicalfile.getWeight());
            newMedicalfile.setHeight(medicalfile.getHeight());
            newMedicalfile.setAge(medicalfile.getAge());


            // Log each field value
            log.info("Saving Medicalfile: " + newMedicalfile.toString());

            return medicalfileRepo.save(newMedicalfile);
        } catch (Exception e) {
            log.error("Error saving mediclafile: ", e);
            throw new RuntimeException("FAIL!");
        }
    }

    public List<Medicalfile> getAllMedicalfilebydocotrs(long id) {
        return medicalfileRepo.finddocotrWithid(id);
    }

    public List<Medicalfile> getAllMedicalfilebypatients(long id) {
        return medicalfileRepo.findpatientWithid(id);
    }

    public void deletemediclafile(Long id) {
        medicalfileRepo.deleteById(id);
    }
}

