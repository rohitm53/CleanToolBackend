package com.indiacleantool.cleantool.web.companymodules.timeslots;

import com.indiacleantool.cleantool.exceptions.timeslots.TimeSlotCodeException;
import com.indiacleantool.cleantool.commonmodels.timeslots.entity.TimeSlot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TimeSlotsService {

    @Autowired
    private TimeSlotsRepository repository;

    @Autowired
    private TimeSlotDao timeSlotDao;

    public List<TimeSlot> findAll(){
        List<TimeSlot> timeSlotList = new ArrayList<>();
        repository.findAll().forEach(timeSlotList::add);
        return timeSlotList;
    }

    public TimeSlot findBySlotCode(String slotCode){
        TimeSlot timeSlot = repository.findBySlotCodeIgnoreCase(slotCode);

        if(timeSlot ==null){
            throw new TimeSlotCodeException("No time slot available with code : "+slotCode);
        }
        return timeSlot;
    }

    @Transactional
    public void generateTimeSlots() {

        List<TimeSlot> timeSlotList = new ArrayList<>();

        LocalTime localTime  = LocalTime.of(8,0);
        for(int i=1; i<=11;i++){
            timeSlotList.add(new TimeSlot("T"+i,localTime));
            localTime  = localTime.plusHours(1);
        }
        repository.truncateTimeSlots();
        repository.saveAll(timeSlotList);
    }

    public List<TimeSlot> getTimeSlotByStartNEndTime(String startTime , String endTime ){
        return timeSlotDao.getTimeSlotByStartNEndTime(startTime,endTime);
    }

    public String getNextTimeSlot(String scheduleTimeSlotCode) {
        int currentValue = Integer.parseInt(String.valueOf(scheduleTimeSlotCode.charAt(1)));
        currentValue++;
        return "T" + currentValue;
    }
    public String getPreviousTimeSlot(String scheduleTimeSlotCode) {
        int currentValue = Integer.parseInt(String.valueOf(scheduleTimeSlotCode.charAt(1)));
        currentValue--;
        return "T" + currentValue;
    }
}
