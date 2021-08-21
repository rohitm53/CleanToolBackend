package com.indiacleantool.cleantool.web.companymodules.companyservice;

import com.indiacleantool.cleantool.web.companymodules.companyservice.dao.CompanyServiceDao;
import com.indiacleantool.cleantool.web.companymodules.companyservice.models.entity.CompanyServiceEntity;
import com.indiacleantool.cleantool.web.companymodules.staticservices.model.entity.Services;
import com.indiacleantool.cleantool.commonmodels.usersmodels.company.entity.CompanyCodeView;
import com.indiacleantool.cleantool.exceptions.companyservice.CompanyServiceException;
import com.indiacleantool.cleantool.web.companymodules.staticservices.StaticServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CompanyServiceSprService {

    @Autowired
    private CompanyServiceRepository repository;

    @Autowired
    private CompanyServiceDao companyServiceDao;

    @Autowired
    private StaticServiceRepository staticServiceRepository;

    @Transactional
    public Iterable<CompanyServiceEntity> saveCompanyService(String companyCode, List<String> listServiceCode) {

        if (listServiceCode == null) {
            throw new CompanyServiceException("No Service code list available");
        }

        List<CompanyServiceEntity> listAvailServices = companyServiceDao.findAllByCompanyCode(companyCode);

        Map<String, CompanyServiceEntity> hmAvailableService = listAvailServices.stream()
                .collect(Collectors.toMap(CompanyServiceEntity::getServiceCode,Function.identity()));

        HashSet<Long> deletedServiceIds = new HashSet<>();
        HashSet<String> newServiceCodes = new HashSet<>();

        //Loop to find new service codes
        for(String serviceCode : listServiceCode){
            if(!hmAvailableService.containsKey(serviceCode)){
                newServiceCodes.add(serviceCode);
            }
        }

        // Loop to find deleted services
        for(CompanyServiceEntity entity : listAvailServices){
            if(!listServiceCode.contains(entity.getServiceCode())){
                deletedServiceIds.add(entity.getId());
            }
        }
        List<CompanyServiceEntity> newCompanyServiceList = new ArrayList<>();
        for(String serviceCode : newServiceCodes){
            CompanyServiceEntity entity = new CompanyServiceEntity();
            entity.setCompanyCode(companyCode);
            entity.setServiceCode(serviceCode);
            newCompanyServiceList.add(entity);
        }

        companyServiceDao.deleteCompanyService(new ArrayList<>(deletedServiceIds));

        return repository.saveAll(newCompanyServiceList);

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
