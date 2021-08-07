package com.indiacleantool.cleantool.web.companymodules.assignemployee.models.dto.availableemployee;

import com.indiacleantool.cleantool.commonmodels.usersmodels.employee.Employee;

import java.util.List;

public class AvailableEmployeeResponse {

    private List<Employee> availableEmployees;

    public AvailableEmployeeResponse(List<Employee> availableEmployees) {
        this.availableEmployees = availableEmployees;
    }

    public List<Employee> getAvailableEmployees() {
        return availableEmployees;
    }

    public void setAvailableEmployees(List<Employee> availableEmployees) {
        this.availableEmployees = availableEmployees;
    }
}
