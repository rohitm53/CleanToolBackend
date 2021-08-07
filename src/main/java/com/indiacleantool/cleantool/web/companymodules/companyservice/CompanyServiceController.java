package com.indiacleantool.cleantool.web.companymodules.companyservice;

import com.indiacleantool.cleantool.web.companymodules.companyservice.models.dto.CompanyServiceRequest;
import com.indiacleantool.cleantool.exceptions.MapValidationExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/company/company-service")
public class CompanyServiceController {

    @Autowired
    private CompanyServiceSprService service;

    @Autowired
    private MapValidationExceptionService mapValidationExceptionService;

    @PostMapping
    public ResponseEntity<?> saveCompanyService(@Valid @RequestBody CompanyServiceRequest request,
            BindingResult bindingResult) {

        ResponseEntity<?> errorMap = mapValidationExceptionService.validateRESTRequest(bindingResult);
        if (errorMap != null) {
            return errorMap;
        }
        return new ResponseEntity<>(service.saveCompanyService(request.getCompanyCode(), request.getServiceCodes()),
                HttpStatus.OK);
    }

    @GetMapping("/{companyCode}")
    public ResponseEntity<?> getServiceByCompanyCode(@PathVariable String companyCode) {
        return new ResponseEntity<>(service.getServicesForCompanyByCompanyCode(companyCode), HttpStatus.OK);
    }

}
