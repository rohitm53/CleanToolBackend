package com.indiacleantool.cleantool.web.companymodules.employeeservice.model.dto;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;

public class EmployeeServiceRequest {

    @NotBlank(message = "Company code cannot be blank")
    private String companyCode;

    private ArrayList<EmployeeServiceRequestBody> employeeServices;

    public ArrayList<EmployeeServiceRequestBody> getEmployeeServices() {
        return employeeServices;
    }

    public void setEmployeeServices(ArrayList<EmployeeServiceRequestBody> employeeServices) {
        this.employeeServices = employeeServices;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
}
