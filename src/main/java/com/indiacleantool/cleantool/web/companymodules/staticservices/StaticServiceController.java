package com.indiacleantool.cleantool.web.companymodules.staticservices;

import com.indiacleantool.cleantool.web.companymodules.staticservices.model.entity.Services;
import com.indiacleantool.cleantool.exceptions.MapValidationExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/services")
public class StaticServiceController {

    @Autowired
    private StaticServicesService service;

    @Autowired
    private MapValidationExceptionService mapValidationExceptionService;

    @PostMapping
    public ResponseEntity<?> createNewService(@Valid @RequestBody Services services, BindingResult result) {
        ResponseEntity<?> errorMap = mapValidationExceptionService.validateRESTRequest(result);
        if (errorMap != null) {
            return errorMap;
        }
        Services services1 = service.saveOrUpdateService(services);
        return new ResponseEntity<>(services1, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Services> getAllService() {
        return service.findAllServices();
    }

    @GetMapping("/{serviceCode}")
    public ResponseEntity<?> getServiceByCode(@PathVariable String serviceCode) {
        return new ResponseEntity<>(service.findByServiceCode(serviceCode), HttpStatus.OK);
    }

    @DeleteMapping("/{serviceCode}")
    public ResponseEntity<?> deleteServiceByCode(@PathVariable String serviceCode) {
        service.deleteService(serviceCode);
        return new ResponseEntity<>("Service with Code : " + serviceCode.toUpperCase() + " has been deleted",
                HttpStatus.OK);
    }
}
