package com.indiacleantool.cleantool.web.companymodules.employees;

import com.indiacleantool.cleantool.commonmodels.usersmodels.employee.Employee;
import com.indiacleantool.cleantool.exceptions.MapValidationExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/company/employee")
public class EmployeeController {

    @Autowired
    private EmployeeSprService service;

    @Autowired
    private MapValidationExceptionService mapValidationExceptionService;

    @PostMapping
    public ResponseEntity<?> createOrSaveEmployee(@Valid @RequestBody Employee employee ,
                                                  BindingResult result,
                                                  Principal principal){

        ResponseEntity<?> errorMap = mapValidationExceptionService.validateRESTRequest(result);
        if(errorMap!=null){
            return errorMap;
        }
        Employee employee1 = service.saveOrUpdateEmployee(employee,principal.getName());
        return new ResponseEntity<>(employee1, HttpStatus.OK);

    }

    @GetMapping("/{employeeCode}")
    public ResponseEntity<?> getEmployeeByEmployeeCode(@PathVariable String employeeCode){
        return new ResponseEntity<>(service.findByEmployeeCode(employeeCode),HttpStatus.OK);
    }

    @GetMapping("/company-employee")
    public ResponseEntity<?> getAllCompanyEmployee(Principal principal){
        return new ResponseEntity<>(service.findAllByCompanyCode(principal.getName()),HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllEmployee(){
        return new ResponseEntity<>(service.findAllEmployees(),HttpStatus.OK);
    }

    @DeleteMapping("/{employeeCode}")
    public ResponseEntity<?> deleteEmployeeByCode(@PathVariable String employeeCode , Principal principal){
        service.deleteEmployeeByCode(employeeCode , principal.getName());
        return new ResponseEntity<>("Employee with code : "+employeeCode+" has been deleted",HttpStatus.OK);
    }
}
