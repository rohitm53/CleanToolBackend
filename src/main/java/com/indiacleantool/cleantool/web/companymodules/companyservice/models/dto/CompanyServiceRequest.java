package com.indiacleantool.cleantool.web.companymodules.companyservice.models.dto;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class CompanyServiceRequest {

    @NotBlank(message = "Company Code cannot be blank")
    private String companyCode;

    private List<String> serviceCodes;

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public List<String> getServiceCodes() {
        return serviceCodes;
    }

    public void setServiceCodes(List<String> serviceCodes) {
        this.serviceCodes = serviceCodes;
    }
}
