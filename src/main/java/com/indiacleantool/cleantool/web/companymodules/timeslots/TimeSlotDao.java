package com.indiacleantool.cleantool.web.companymodules.timeslots;

import com.indiacleantool.cleantool.dao.BaseDao;
import com.indiacleantool.cleantool.commonmodels.timeslots.entity.TimeSlot;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import java.util.List;
import java.util.StringJoiner;

@Service
public class TimeSlotDao extends BaseDao {



    public List<TimeSlot> getTimeSlotByStartNEndTime(String startTime , String endTime ){

        StringJoiner query = new StringJoiner(" ");
        query
                .add(" select id,slot_code,slot_time from time_slot ")
                .add(" where slot_time between ")
                .add(" time(:startTime) ")
                .add(" and time(:endTime) ");

        Query nativeQuery = getEntityManager().createNativeQuery(query.toString(),TimeSlot.class);
        nativeQuery.setParameter("startTime",startTime);
        nativeQuery.setParameter("endTime",endTime);

        return nativeQuery.getResultList();
    }

}
