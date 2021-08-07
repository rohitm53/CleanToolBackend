package com.indiacleantool.cleantool.web.employeemodules.employeedutymanagment;

import com.indiacleantool.cleantool.exceptions.MapValidationExceptionService;
import com.indiacleantool.cleantool.web.employeemodules.employeedutymanagment.dto.EmployeeDutyUpdateRequest;
import com.indiacleantool.cleantool.web.employeemodules.employeedutymanagment.dto.EmployeeDutyUpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/employee/duty")
public class EmployeeDutyStatusController {

    @Autowired
    MapValidationExceptionService mapValidationExceptionService;

    @Autowired
    EmployeeDutyStatusService employeeDutyStatusService;

    public ResponseEntity<?> employeeDutyUpdate(@RequestBody @Valid EmployeeDutyUpdateRequest request ,
                                                BindingResult result,
                                                Principal principal){

        ResponseEntity<?> errorMap = mapValidationExceptionService.validateRESTRequest(result);

        if(errorMap!=null) return errorMap;

        employeeDutyStatusService.updateEmployeeDuty(request , principal.getName());

        return ResponseEntity.ok(new EmployeeDutyUpdateResponse(
                "Duty updated for employee code : "+principal.getName() + " for date : "+request.getDate()
        ));

    }

}
