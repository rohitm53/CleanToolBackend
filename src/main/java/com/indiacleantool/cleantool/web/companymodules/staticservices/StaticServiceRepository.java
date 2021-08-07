package com.indiacleantool.cleantool.web.companymodules.staticservices;

import com.indiacleantool.cleantool.web.companymodules.staticservices.model.entity.Services;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StaticServiceRepository extends CrudRepository<Services, Long> {

    Services findByServiceCode(String serviceCode);

    @Query(value = "select * from services as S join company_service as CS on "
            + "S.service_code=CS.service_code where CS.company_code= :company_code", nativeQuery = true)
    Iterable<Services> getServicesForCompanyByCompanyCode(@Param("company_code") String company_code);
}
