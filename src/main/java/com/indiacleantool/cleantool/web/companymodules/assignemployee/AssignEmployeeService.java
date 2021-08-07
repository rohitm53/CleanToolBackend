package com.indiacleantool.cleantool.web.companymodules.assignemployee;

import com.indiacleantool.cleantool.web.companymodules.assignemployee.models.dto.assignemployee.AssignEmployeeRequest;
import com.indiacleantool.cleantool.web.companymodules.assignemployee.models.dto.assignemployee.AssignEmployeeResponse;
import com.indiacleantool.cleantool.web.companymodules.assignemployee.models.dto.availableemployee.AvailableEmployeeRequest;
import com.indiacleantool.cleantool.web.companymodules.assignemployee.models.dto.availableemployee.AvailableEmployeeResponse;
import com.indiacleantool.cleantool.web.mobileuersmodules.bookservicerequest.model.dto.PendingServiceRequestResponse;
import com.indiacleantool.cleantool.commonmodels.usersmodels.employee.Employee;
import com.indiacleantool.cleantool.web.common.allservicerequesthandler.SingleServiceRequestHandlerService;
import com.indiacleantool.cleantool.web.companymodules.employees.EmployeeSprService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignEmployeeService {

    @Autowired
    private SingleServiceRequestHandlerService singleServiceRequestHandlerService;

    @Autowired
    private EmployeeSprService employeeSprService;

    public PendingServiceRequestResponse getAllCompanyServiceRequest(String companyCode) {
        return singleServiceRequestHandlerService.getAllCompanyServiceRequest(companyCode);
    }

    public AssignEmployeeResponse performAssignEmployeeToServiceReq(AssignEmployeeRequest request, String companyCode) {
        return singleServiceRequestHandlerService.performAssignEmployeeToServiceReq(request, companyCode);
    }

    public AvailableEmployeeResponse getAllCompanyAvailableEmployee(AvailableEmployeeRequest request) {

        List<Employee> availableEmp = employeeSprService.getAllCompanyAvailableEmployee(request.getCompanyCode(),
                request.getDate());

        AvailableEmployeeResponse response = new AvailableEmployeeResponse(availableEmp);

        return response;
    }

}
