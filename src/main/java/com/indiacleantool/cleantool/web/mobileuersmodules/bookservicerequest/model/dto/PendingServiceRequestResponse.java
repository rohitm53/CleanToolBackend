package com.indiacleantool.cleantool.web.mobileuersmodules.bookservicerequest.model.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.indiacleantool.cleantool.commonmodels.errormodels.Error;
import com.indiacleantool.cleantool.web.mobileuersmodules.bookservicerequest.model.entity.ServiceRequestEntity;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE, setterVisibility = JsonAutoDetect.Visibility.NONE)
public class PendingServiceRequestResponse {

    @JsonProperty("service_requests")
    private List<ServiceRequestEntity> listServiceRequestEntity;
    private Error error;


    public PendingServiceRequestResponse() {
    }

    public PendingServiceRequestResponse(List<ServiceRequestEntity> listServiceRequestEntity) {
        this.listServiceRequestEntity = listServiceRequestEntity;
    }

    public PendingServiceRequestResponse(Error error) {
        this.error = error;
    }

    public List<ServiceRequestEntity> getListServiceRequest() {
        return listServiceRequestEntity;
    }

    public void setListServiceRequest(List<ServiceRequestEntity> listServiceRequestEntity) {
        this.listServiceRequestEntity = listServiceRequestEntity;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
