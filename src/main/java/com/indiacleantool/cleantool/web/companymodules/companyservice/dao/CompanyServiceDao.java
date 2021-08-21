package com.indiacleantool.cleantool.web.companymodules.companyservice.dao;

import com.indiacleantool.cleantool.web.companymodules.companyservice.models.entity.CompanyServiceEntity;

import java.util.List;

public interface CompanyServiceDao {

    List<CompanyServiceEntity> findAllByCompanyCode(String companyCode);

    void deleteCompanyService(List<Long> ids);

}
