package com.indiacleantool.cleantool.web.companymodules.employees;

import com.indiacleantool.cleantool.commonmodels.usersmodels.employee.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Long> {

    @Procedure(name = "sp_generateEmployeeCode")
    String generateEmployeeCode(@Param("empId") Long empId);

    Employee findByEmployeeCode(String employeeCode);

    Iterable<Employee> findAllByCompanyCode(String companyCode);

    long countByCompanyCode(String companyCode);

    @Query(
            value = " select * from employee where employee_code not in ( " +
                    " select emp_code from employee_assigned_service where " +
                    " company_code = :companyCode and is_completed = 0 and scheduled_date=:date " +
                    " ) ",
            nativeQuery = true
    )
    List<Employee> getAllCompanyAvailableEmployee(@Param("companyCode") String companyCode, @Param("date") String date);


}
