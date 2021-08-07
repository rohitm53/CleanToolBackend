package com.indiacleantool.cleantool.web.companymodules.assignemployee.models.dto.assignemployee;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.indiacleantool.cleantool.commonmodels.errormodels.Error;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AssignEmployeeResponse {

    private String serviceReqCode;

    private String assignedEmployeeCode;

    private int statusCode;

    private Error error;

    public AssignEmployeeResponse() {
    }

    public AssignEmployeeResponse(String serviceReqCode, String assignedEmployeeCode, int statusCode) {
        this.serviceReqCode = serviceReqCode;
        this.assignedEmployeeCode = assignedEmployeeCode;
        this.statusCode = statusCode;
    }

    public AssignEmployeeResponse(Error error) {
        this.error = error;
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

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
