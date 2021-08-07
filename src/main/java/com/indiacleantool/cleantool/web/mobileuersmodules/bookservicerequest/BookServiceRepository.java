package com.indiacleantool.cleantool.web.mobileuersmodules.bookservicerequest;

import com.indiacleantool.cleantool.web.mobileuersmodules.bookservicerequest.model.entity.ServiceRequestEntity;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public interface BookServiceRepository extends CrudRepository<ServiceRequestEntity,Long> {

    @Procedure(name = "sp_Generate_Service_RequestCode")
    String generateServiceRequestCode(@Param("service_req_id") Long id);


    List<ServiceRequestEntity> findByMobileUserCodeIgnoreCase(String mobileUserCode);

    List<ServiceRequestEntity> findByCompanyCodeIgnoreCase(String companyCode);

    Optional<ServiceRequestEntity> findByServiceReqCode(String serviceReqCode);

}
