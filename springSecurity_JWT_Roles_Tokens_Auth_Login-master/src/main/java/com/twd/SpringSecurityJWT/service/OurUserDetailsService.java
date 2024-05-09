package com.twd.SpringSecurityJWT.service;

import com.twd.SpringSecurityJWT.entity.OurUsers;
import com.twd.SpringSecurityJWT.repository.OurUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OurUserDetailsService implements UserDetailsService {

    @Autowired
    public static PasswordEncoder encoder;

    @Autowired
    private OurUserRepo ourUserRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return ourUserRepo.findByEmail(username).orElseThrow();
    }



    @Autowired
    public OurUserDetailsService(OurUserRepo ourUserRepo) {
        this.ourUserRepo = ourUserRepo; // Dependency injection
    }

    public List<OurUsers> getAllDoctors() {
        return ourUserRepo.findUsersWithRole("DOCTOR");
    }

    public List<OurUsers> getallpatients() {

        return ourUserRepo.findUsersWithRole("PATIENT");
    }

    public void deleteuser(Integer id) {

        ourUserRepo.deleteById(id);
    }



    public String updateuser(OurUsers ourUsers, int id) {
        Optional<OurUsers> currentuser = ourUserRepo.findById(id);
        if (currentuser.isPresent()) {
            OurUsers user = currentuser.get();

            if (!Objects.equals(ourUsers.getEmail(), user.getEmail())) {
                if (!ourUserRepo.existsByEmail(ourUsers.getEmail())) {
                    user.setEmail(ourUsers.getEmail());
                } else {
                    return "EMAIL ALREADY TAKEN"; // Properly handle this condition
                }
            }

            // We should save and return "UPDATED" if everything is successful
            ourUserRepo.saveAndFlush(user);
            return "UPDATED";
        } else {
            return "USER NOT FOUND"; // Handle when user is not found
        }
    }
    public boolean updatepassword (String password ,int id ) {
        OurUsers currentuser = ourUserRepo.findById(id ).get() ;
        //   System.out.println(currentuser.getPassword());
        if ( !encoder.matches(password,currentuser.getPassword())){
            currentuser.setPassword(encoder.encode(password));
            ourUserRepo.saveAndFlush(currentuser) ;
            return true ;


        }else {return false ;}


    }


}
