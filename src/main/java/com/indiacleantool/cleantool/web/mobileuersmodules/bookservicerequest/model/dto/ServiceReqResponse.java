package com.indiacleantool.cleantool.web.mobileuersmodules.bookservicerequest.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.indiacleantool.cleantool.common.Constants;
import com.indiacleantool.cleantool.commonmodels.errormodels.Error;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceReqResponse {

    private String serviceReqCode;
    private int statusCode;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.APP_LEVEL_DATE_FORMAT , locale = "en")
    private LocalDate scheduled;

    private Error error;

    public ServiceReqResponse(String serviceReqCode, int statusCode, LocalDate scheduled) {
        this.serviceReqCode = serviceReqCode;
        this.statusCode = statusCode;
        this.scheduled = scheduled;
    }

    public ServiceReqResponse(Error error) {
        this.error = error;
    }

    public String getServiceReqCode() {
        return serviceReqCode;
    }

    public void setServiceReqCode(String serviceReqCode) {
        this.serviceReqCode = serviceReqCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }


    public LocalDate getScheduled() {
        return scheduled;
    }

    public void setScheduled(LocalDate scheduled) {
        this.scheduled = scheduled;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
