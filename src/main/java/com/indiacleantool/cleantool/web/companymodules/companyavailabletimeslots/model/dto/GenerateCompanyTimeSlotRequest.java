package com.indiacleantool.cleantool.web.companymodules.companyavailabletimeslots.model.dto;

import javax.validation.constraints.NotBlank;

public class GenerateCompanyTimeSlotRequest {

    @NotBlank(message = "Current date cannot be blank")
    private String currentDate;

    @NotBlank(message = "Start time cannot be blank")
    private String startTime;

    @NotBlank(message = "End time cannot be blank")
    private String endTime;

    public GenerateCompanyTimeSlotRequest(String currentDate, String startTime, String endTime) {
        this.currentDate = currentDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
