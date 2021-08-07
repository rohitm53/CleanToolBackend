package com.indiacleantool.cleantool.web.common.users.company;

import com.indiacleantool.cleantool.commonmodels.usersmodels.company.entity.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends CrudRepository<Company,Long> {

    Company findByCompanyCode(String company_code);

}
