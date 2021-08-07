package com.indiacleantool.cleantool.web.companymodules.companyavailabletimeslots.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity(name = "CompanyTimeSlots")
public class CompanyTimeSlotsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String companyCode;

    private String timeSlotCode;

    private LocalDate date;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime time;

    private int availableEmployeeCount;

    public CompanyTimeSlotsEntity() {
    }

    public CompanyTimeSlotsEntity(String companyCode, String timeSlotCode, LocalDate date, LocalTime time,
            int availableEmployeeCount) {
        this.companyCode = companyCode;
        this.timeSlotCode = timeSlotCode;
        this.date = date;
        this.time = time;
        this.availableEmployeeCount = availableEmployeeCount;
    }

    public CompanyTimeSlotsEntity(long id, String companyCode, String timeSlotCode, LocalTime time,
            int availableEmployeeCount) {
        this.id = id;
        this.companyCode = companyCode;
        this.timeSlotCode = timeSlotCode;
        this.time = time;
        this.availableEmployeeCount = availableEmployeeCount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getTimeSlotCode() {
        return timeSlotCode;
    }

    public void setTimeSlotCode(String timeSlotCode) {
        this.timeSlotCode = timeSlotCode;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public int getAvailableEmployeeCount() {
        return availableEmployeeCount;
    }

    public void setAvailableEmployeeCount(int availableEmployeeCount) {
        this.availableEmployeeCount = availableEmployeeCount;
    }
}
