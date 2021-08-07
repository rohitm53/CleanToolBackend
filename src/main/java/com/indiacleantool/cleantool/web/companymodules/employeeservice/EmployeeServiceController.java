package com.indiacleantool.cleantool.web.companymodules.employeeservice;

import com.indiacleantool.cleantool.web.companymodules.employeeservice.model.dto.EmployeeServiceRequest;
import com.indiacleantool.cleantool.exceptions.MapValidationExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/company/employeeservice")
public class EmployeeServiceController {

    @Autowired
    private EmployeeServiceSprService service;

    @Autowired
    private MapValidationExceptionService mapValidationExceptionService;

    @PostMapping
    public ResponseEntity<?> saveEmployeeService(@Valid @RequestBody EmployeeServiceRequest request,
            BindingResult result) {
        ResponseEntity<?> errorMap = mapValidationExceptionService.validateRESTRequest(result);
        if (errorMap != null) {
            return errorMap;
        }
        return new ResponseEntity<>(service.saveEmployeeService(request), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getCompanyEmployeeService(Principal principal) {
        return new ResponseEntity<>(service.getEmployeeServiceRelationByCompanyCode(principal.getName()),
                HttpStatus.OK);
    }
}
