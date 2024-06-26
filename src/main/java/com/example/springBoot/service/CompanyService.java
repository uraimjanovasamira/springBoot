package com.example.springBoot.service;

import com.example.springBoot.mapper.CompanyMapper;
import com.example.springBoot.model.Company;
import com.example.springBoot.model.dto.request.CompanyRequest;
import com.example.springBoot.model.dto.response.CompanyResponse;
import com.example.springBoot.repository.CompanyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    public Long getStudentCountCompany(Long companyId) {
        return companyRepository.countStudentsByCompanyId(companyId);
    }

    public CompanyResponse save(CompanyRequest request) {
        Company company = companyMapper.companyMapper(request);
        companyRepository.save(company);
        return companyMapper.mapToResponse(company);
    }

    public List<CompanyResponse> findAll() {
        return companyRepository.findAll()
                .stream()
                .map(companyMapper::mapToResponse).toList();
    }

    public CompanyResponse findById(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found Company by id" + id));
        return companyMapper.mapToResponse(company);
    }

    public Company update(Long id, CompanyRequest request) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found Company by id" + id));
        company.setCompanyName(request.getCompanyName());
        company.setLocatedCountry(request.getLocatedCountry());
        log.info("Successfully updated Company with id:{}", id);
        return companyRepository.save(company);
    }


    public String delete(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found Company by id" + id));
        companyRepository.delete(company);
        return "Successfully deleted company by id" + id;
    }
}
