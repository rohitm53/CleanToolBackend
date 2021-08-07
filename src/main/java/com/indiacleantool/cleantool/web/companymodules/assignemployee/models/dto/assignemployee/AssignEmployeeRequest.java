package com.indiacleantool.cleantool.web.companymodules.assignemployee.models.dto.assignemployee;

import javax.validation.constraints.NotBlank;

public class AssignEmployeeRequest {

    @NotBlank(message = "Service request code cannot be blank")
    private String serviceReqCode;

    @NotBlank(message = "Employee code cannot be blank")
    private String assignedEmployeeCode;

    public AssignEmployeeRequest() {
    }

    public AssignEmployeeRequest(@NotBlank(message = "Service request code cannot be blank") String serviceReqCode,
            @NotBlank(message = "Employee code cannot be blank") String assignedEmployeeCode) {
        this.serviceReqCode = serviceReqCode;
        this.assignedEmployeeCode = assignedEmployeeCode;
    }

    public String getServiceReqCode() {
        return serviceReqCode;
    }

    public void setServiceReqCode(String serviceReqCode) {
        this.serviceReqCode = serviceReqCode;
    }

    public String getAssignedEmployeeCode() {
        return assignedEmployeeCode;
    }

    public void setAssignedEmployeeCode(String assignedEmployeeCode) {
        this.assignedEmployeeCode = assignedEmployeeCode;
    }
}
