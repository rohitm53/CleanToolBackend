package com.indiacleantool.cleantool.web.companymodules.employeeservice;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import com.indiacleantool.cleantool.web.companymodules.employeeservice.model.entity.EmployeeService;

@Repository
public interface EmployeeServiceRepository extends CrudRepository<EmployeeService, Long> {

    @Procedure(name = "sp_deleteEmployeeServiceByCompanyCode")
    void deleteEmployeeServicebyCompanyCode(@Param("the_companyCode") String companyCode);

    long countByCompanyCode(String companyCode);

    Optional<List<EmployeeService>> findByCompanyCodeAndServiceCode(String companyCode, String serviceCode);

    EmployeeService findByCompanyCodeAndServiceCodeAndEmployeeCode(String companyCode, String serviceCode,
            String employeeCode);

}
