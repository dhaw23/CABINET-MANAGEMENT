package com.twd.SpringSecurityJWT.repository;

import com.twd.SpringSecurityJWT.entity.OurUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OurUserRepo extends JpaRepository<OurUsers, Integer> {
    Optional<OurUsers> findByEmail(String email);
    Boolean existsByEmail (String email) ;



    @Query("SELECT u FROM OurUsers u WHERE u.role LIKE %:role%")
    List<OurUsers> findUsersWithRole(@Param("role") String role);
    @Query(value = "SELECT * FROM ourusers o JOIN appointment a ON o.id = a.id_patient WHERE a.id_doctor = :doctorId", nativeQuery = true)
    List<OurUsers> findpatientByDoctorId(@Param("doctorId") Long doctorId);

}




