package com.indiacleantool.cleantool.web.mobileuersmodules.servicedetails.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.indiacleantool.cleantool.commonmodels.errormodels.Error;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceProviderDetailResponse {

    @JsonProperty("company-details")
    private List<ServiceProviderCompanyDetails> listServiceProviderCompanyDetails;
    private Error error;

    public ServiceProviderDetailResponse(List<ServiceProviderCompanyDetails> listServiceProviderCompanyDetails) {
        this.listServiceProviderCompanyDetails = listServiceProviderCompanyDetails;
    }

    public ServiceProviderDetailResponse(Error error) {
        this.error = error;
    }

    public List<ServiceProviderCompanyDetails> getListServiceProviderCompanyDetails() {
        return listServiceProviderCompanyDetails;
    }

    public void setListServiceProviderCompanyDetails(List<ServiceProviderCompanyDetails> listServiceProviderCompanyDetails) {
        this.listServiceProviderCompanyDetails = listServiceProviderCompanyDetails;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}


