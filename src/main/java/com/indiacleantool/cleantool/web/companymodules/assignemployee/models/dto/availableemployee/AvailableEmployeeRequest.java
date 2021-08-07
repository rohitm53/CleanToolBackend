package com.indiacleantool.cleantool.web.companymodules.assignemployee.models.dto.availableemployee;

import javax.validation.constraints.NotBlank;

public class AvailableEmployeeRequest {

    @NotBlank(message = "Date cannot be empty")
    private String date;

    private String companyCode;

    public AvailableEmployeeRequest(String date, String companyCode) {
        this.date = date;
        this.companyCode = companyCode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
}
