package com.indiacleantool.cleantool.web.companymodules.companyservice;

import com.indiacleantool.cleantool.web.companymodules.companyservice.models.entity.CompanyServiceEntity;
import com.indiacleantool.cleantool.commonmodels.usersmodels.company.entity.CompanyCodeView;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyServiceRepository extends CrudRepository<CompanyServiceEntity, Long> {

    long countByCompanyCode(String companyCode);

    List<CompanyCodeView> findByServiceCode(String serviceCode);
}