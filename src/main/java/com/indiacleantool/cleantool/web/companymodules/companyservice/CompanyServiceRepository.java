package com.indiacleantool.cleantool.web.companymodules.companyservice;

import com.indiacleantool.cleantool.web.companymodules.companyservice.models.entity.CompanyService;
import com.indiacleantool.cleantool.commonmodels.usersmodels.company.entity.CompanyCodeView;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyServiceRepository extends CrudRepository<CompanyService, Long> {

    @Procedure(name = "sp_deleteCompanyServiceByCompanyCode")
    void deleteCompanyServiceByCompanyCode(@Param("the_company_code") String companyCode);

    long countByCompanyCode(String companyCode);

    List<CompanyCodeView> findByServiceCode(String serviceCode);
}