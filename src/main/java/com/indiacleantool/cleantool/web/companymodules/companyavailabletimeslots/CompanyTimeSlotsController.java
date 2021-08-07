package com.indiacleantool.cleantool.web.companymodules.companyavailabletimeslots;

import com.indiacleantool.cleantool.web.companymodules.companyavailabletimeslots.model.dto.GenerateCompanyTimeSlotRequest;
import com.indiacleantool.cleantool.exceptions.MapValidationExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/company/time-slots")
public class CompanyTimeSlotsController {

    @Autowired
    private CompanyTimeSlotsService service;

    @Autowired
    private MapValidationExceptionService mapValidationExceptionService;

    @PostMapping
    public ResponseEntity<?> generateCompanyTimeSlots(@Valid @RequestBody GenerateCompanyTimeSlotRequest request,
            BindingResult result, Principal principal) {
        ResponseEntity<?> errorMap = mapValidationExceptionService.validateRESTRequest(result);
        if (errorMap != null) {
            return errorMap;
        }

        return new ResponseEntity<>(service.generateTimeSlots(request, principal.getName()), HttpStatus.OK);

    }

    @GetMapping("/{date}")
    public ResponseEntity<?> getAvailableCompanyTimeSlotsByDate(@PathVariable String date, Principal principal) {
        return new ResponseEntity<>(service.getAllCompanyAvailableTimeSlotsByDate(principal.getName(), date),
                HttpStatus.OK);
    }

}
