package com.indiacleantool.cleantool.web.employeemodules.employeedutymanagment;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDutyStatusRepository extends CrudRepository<EmployeeDutyStatusService, Long> {
}
