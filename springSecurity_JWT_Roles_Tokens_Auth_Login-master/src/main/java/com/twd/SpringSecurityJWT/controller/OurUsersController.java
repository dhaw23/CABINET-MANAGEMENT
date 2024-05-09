package com.twd.SpringSecurityJWT.controller;

import com.twd.SpringSecurityJWT.entity.OurUsers;
import com.twd.SpringSecurityJWT.service.OurUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class OurUsersController {
    @Autowired
    private  final OurUserDetailsService ourUserDetailsService ;

    public OurUsersController(OurUserDetailsService ourUserDetailsService) {
        this.ourUserDetailsService = ourUserDetailsService;
    }

    @GetMapping("/getAlldoctors")
    public ResponseEntity<List<OurUsers>> getAlldoctors(){

        return ResponseEntity.ok( ourUserDetailsService.getAllDoctors());
    }
    @GetMapping("/getAllpatients")
    public ResponseEntity<List<OurUsers>> getAllpatients(){
        return ResponseEntity.ok( ourUserDetailsService.getallpatients());

    }


    @PostMapping("/updatepassword/{id}")
    public ResponseEntity <?> updatepassword (@PathVariable int id ,@RequestBody String password ){

        if (ourUserDetailsService.updatepassword(password , id ) ){
            return new ResponseEntity<>("DONE", HttpStatus.OK );
        }
        else return new ResponseEntity<>("SAME PASSWORD", HttpStatus.NOT_ACCEPTABLE);


    }}