package com.indiacleantool.cleantool.web.companymodules.assignemployee;

import com.indiacleantool.cleantool.web.companymodules.assignemployee.models.dto.assignemployee.AssignEmployeeRequest;
import com.indiacleantool.cleantool.web.companymodules.assignemployee.models.dto.availableemployee.AvailableEmployeeRequest;
import com.indiacleantool.cleantool.exceptions.MapValidationExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/company/service-request")
public class AssignEmployeeController {

    @Autowired
    private AssignEmployeeService assignEmployeeService;

    @Autowired
    private MapValidationExceptionService mapValidationExceptionService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllCompanyServiceRequest(Principal principal) {
        return new ResponseEntity<>(assignEmployeeService.getAllCompanyServiceRequest(principal.getName()),
                HttpStatus.OK);
    }

    @PostMapping("/available-employee")
    public ResponseEntity<?> getAllCompanyAvailableEmployee(@Valid @RequestBody AvailableEmployeeRequest request,
            BindingResult result, Principal principal) {
        ResponseEntity<?> errorMap = mapValidationExceptionService.validateRESTRequest(result);
        if (errorMap != null) {
            return errorMap;
        }
        request.setCompanyCode(principal.getName());
        return new ResponseEntity<>(assignEmployeeService.getAllCompanyAvailableEmployee(request), HttpStatus.OK);
    }

    @PostMapping("/assignee-employee")
    public ResponseEntity<?> assigneeEmployeeToServiceRequest(
            @Valid @RequestBody AssignEmployeeRequest assignEmployeeRequest, BindingResult result,
            Principal principal) {
        ResponseEntity<?> errorMap = mapValidationExceptionService.validateRESTRequest(result);
        if (errorMap != null) {
            return errorMap;
        }
        return new ResponseEntity<>(
                assignEmployeeService.performAssignEmployeeToServiceReq(assignEmployeeRequest, principal.getName()),
                HttpStatus.OK);
    }

}
