package com.indiacleantool.cleantool.web.mobileuersmodules.servicedetails.dto;

import com.indiacleantool.cleantool.commonmodels.timeslots.entity.TimeSlot;
import com.indiacleantool.cleantool.commonmodels.usersmodels.company.entity.Company;

import java.util.List;

public class ServiceProviderCompanyDetails {
    private Company company;
    private List<TimeSlot> timeSlots;

    public ServiceProviderCompanyDetails(Company company, List<TimeSlot> timeSlots) {
        this.company = company;
        this.timeSlots = timeSlots;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<TimeSlot> getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(List<TimeSlot> timeSlots) {
        this.timeSlots = timeSlots;
    }
}
