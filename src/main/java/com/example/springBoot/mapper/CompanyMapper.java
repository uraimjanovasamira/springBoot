package com.example.springBoot.mapper;

import com.example.springBoot.model.Company;
import com.example.springBoot.model.dto.request.CompanyRequest;
import com.example.springBoot.model.dto.response.CompanyResponse;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    public Company companyMapper(CompanyRequest request) {
        Company company = new Company();
        company.setCompanyName(request.getCompanyName());
        company.setLocatedCountry(request.getLocatedCountry());
        return company;
    }

    public CompanyResponse mapToResponse(Company company) {
        return CompanyResponse.builder()
                .id(company.getId())
                .companyName((company.getCompanyName()))
                .locatedCountry(company.getLocatedCountry())
                .build();
    }
}
