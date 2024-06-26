package com.example.springBoot.repository;

import com.example.springBoot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select user from User user where user.email=:email")
    Optional<User> findByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.instructorId = :instructorId AND u.role = 'STUDENT'")
    List<User> findStudentsByInstructor(@Param("instructorId") Long instructorId);


    Optional<User> findByName(String name);

}
