package com.indiacleantool.cleantool.web.mobileuersmodules.servicedetails;

import com.indiacleantool.cleantool.exceptions.common.CommonGenericException;
import com.indiacleantool.cleantool.commonmodels.timeslots.entity.TimeSlot;
import com.indiacleantool.cleantool.commonmodels.mobileusermodels.serviceprovidercompany.dto.ServiceProviderCompanyDetails;
import com.indiacleantool.cleantool.commonmodels.mobileusermodels.serviceprovidercompany.dto.ServiceProviderDetailResponse;
import com.indiacleantool.cleantool.commonmodels.usersmodels.company.entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceDetailsSprService {

    @Autowired
    private ServiceDetailsDao serviceDetailsDao;



    public ServiceProviderDetailResponse getServiceProviderCompanyList(String serviceCode , String date){

        ServiceProviderDetailResponse response =null;

        try{
            List<Company> companyList = serviceDetailsDao.getAvailableCompanyByServiceCode(serviceCode);
            List<ServiceProviderCompanyDetails> listServiceProviderCompanyDetails = new ArrayList<>();
            for(Company company : companyList){

                List<TimeSlot> timeSlotList = serviceDetailsDao.getAvailableTimeSlots(company.getCompanyCode(),date);
                    ServiceProviderCompanyDetails serviceProviderCompanyDetails = new ServiceProviderCompanyDetails(
                            company,
                            !(timeSlotList==null || timeSlotList.size()==0) ? timeSlotList : new ArrayList<>()
                    );
                    listServiceProviderCompanyDetails.add(serviceProviderCompanyDetails);
            }
            response = new ServiceProviderDetailResponse(listServiceProviderCompanyDetails);

        }catch (Exception e){
            e.printStackTrace();
            throw new CommonGenericException("No Data Available");
        }

        return response;
    }

}
