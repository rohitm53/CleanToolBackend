package com.indiacleantool.cleantool.web.mobileuersmodules.bookservicerequest;

import com.indiacleantool.cleantool.exceptions.MapValidationExceptionService;
import com.indiacleantool.cleantool.web.mobileuersmodules.bookservicerequest.model.entity.ServiceRequestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/mobile/service-request")
public class BookServiceController {

    @Autowired
    private BookServiceSprService service;

    @Autowired
    private MapValidationExceptionService mapValidationExceptionService;

    @PostMapping("/book")
    public ResponseEntity<?> saveServiceRequest(@Valid @RequestBody ServiceRequestEntity serviceRequestEntity, BindingResult result){

        ResponseEntity<?> errorMap = mapValidationExceptionService.validateRESTRequest(result);
        if(errorMap!=null){
            return errorMap;
        }
        return new ResponseEntity<>(service.saveServiceRequest(serviceRequestEntity), HttpStatus.OK);

    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllMobileUserRequest(Principal principal){
        return new ResponseEntity<>(service.getAllMobileServiceRequest(principal.getName()), HttpStatus.OK);
    }


}
