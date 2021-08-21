package com.indiacleantool.cleantool.web.employeemodules.employeedutymanagment;

import com.indiacleantool.cleantool.web.employeemodules.employeedutymanagment.entity.EmployeeDutyStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDutyStatusRepository extends CrudRepository<EmployeeDutyStatus, Long> {
}
