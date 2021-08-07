package com.indiacleantool.cleantool.web.companymodules.timeslots;

import com.indiacleantool.cleantool.commonmodels.timeslots.entity.TimeSlot;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeSlotsRepository extends CrudRepository<TimeSlot,Long> {

    TimeSlot findBySlotCodeIgnoreCase(String slotCode);

    @Modifying
    @Query(value = "truncate table time_slot" , nativeQuery = true)
    void truncateTimeSlots();

}
