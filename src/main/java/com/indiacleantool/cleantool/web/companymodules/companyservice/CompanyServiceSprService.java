package com.indiacleantool.cleantool.web.companymodules.companyservice;

import com.indiacleantool.cleantool.web.companymodules.companyservice.models.entity.CompanyService;
import com.indiacleantool.cleantool.web.companymodules.staticservices.model.entity.Services;
import com.indiacleantool.cleantool.commonmodels.usersmodels.company.entity.CompanyCodeView;
import com.indiacleantool.cleantool.exceptions.companyservice.CompanyServiceException;
import com.indiacleantool.cleantool.web.companymodules.staticservices.StaticServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyServiceSprService {

    @Autowired
    private CompanyServiceRepository repository;

    @Autowired
    private StaticServiceRepository staticServiceRepository;

    public Iterable<CompanyService> saveCompanyService(String companyCode, List<String> listServiceCode) {

        if (listServiceCode == null) {
            throw new CompanyServiceException("No Service code list available");
        }
        repository.deleteCompanyServiceByCompanyCode(companyCode);
        List<CompanyService> listCompanyService = new ArrayList<>();
        for (String serviceCode : listServiceCode) {
            CompanyService companyService = new CompanyService();
            companyService.setCompanyCode(companyCode);
            companyService.setServiceCode(serviceCode);
            listCompanyService.add(companyService);
        }
        return repository.saveAll(listCompanyService);
    }

    public Iterable<Services> getServicesForCompanyByCompanyCode(String companyCode) {
        return staticServiceRepository.getServicesForCompanyByCompanyCode(companyCode);
    }

    public Long getCountByCompanyCode(String companyCode) {
        return repository.countByCompanyCode(companyCode);
    }

    public List<CompanyCodeView> getCompanyCodeByServiceCode(String serviceCode) {
        return repository.findByServiceCode(serviceCode);
    }
}
