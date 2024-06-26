package com.example.springBoot.repository;

import com.example.springBoot.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query("SELECT COUNT(distinct u) FROM User u " +
            "JOIN u.group g " +
            "JOIN g.courses c " +
            "JOIN c.company comp " +
            "WHERE comp.id = :companyId")
    Long countStudentsByCompanyId(@Param("companyId") Long companyId);

}
