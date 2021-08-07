package com.indiacleantool.cleantool.commonmodels.timeslots.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalTime;


@Entity
public class TimeSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String slotCode;

    private LocalTime slotTime;

    public TimeSlot() {
    }

    public TimeSlot(Long id, String slotCode, LocalTime slotTime) {
        this.id = id;
        this.slotCode = slotCode;
        this.slotTime = slotTime;
    }

    public TimeSlot(String slotCode, LocalTime slotTime) {
        this.slotCode = slotCode;
        this.slotTime = slotTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSlotCode() {
        return slotCode;
    }

    public void setSlotCode(String slotCode) {
        this.slotCode = slotCode;
    }

    public LocalTime getTime() {
        return slotTime;
    }

    public void setTime(LocalTime slotTime) {
        this.slotTime = slotTime;
    }
}