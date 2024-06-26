package com.example.springBoot.controller;

import com.example.springBoot.model.Company;
import com.example.springBoot.model.dto.request.CompanyRequest;
import com.example.springBoot.model.dto.response.CompanyResponse;
import com.example.springBoot.service.CompanyService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/companies")
public class CompanyController {
    CompanyService companyService;

    @GetMapping("/{companyId}/students")
    public Long getStudentsByCompanyId(@PathVariable Long companyId) {
        return companyService.getStudentCountCompany(companyId);
    }

    @PostMapping
    public CompanyResponse save(@RequestBody CompanyRequest request) {
        return companyService.save(request);
    }

    @PutMapping("/{id}")
    public Company update(@PathVariable("id") Long id, @RequestBody CompanyRequest request) {
        return companyService.update(id,request);
    }

    @GetMapping("/{id}")
    public CompanyResponse findById(@PathVariable("id") Long id) {
        return companyService.findById(id);
    }

    @GetMapping
    public List<CompanyResponse> findAll() {
        return companyService.findAll();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        return companyService.delete(id);
    }
}
