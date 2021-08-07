package com.indiacleantool.cleantool.web.common.allservicerequesthandler;

import com.indiacleantool.cleantool.web.companymodules.assignemployee.models.entity.EmployeeAssignedServiceEntity;

import org.springframework.data.repository.CrudRepository;

public interface EmployeeAssignedServiceRepository extends CrudRepository<EmployeeAssignedServiceEntity, Long> {
}
