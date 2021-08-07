package com.indiacleantool.cleantool.web.companymodules.employeeservice;

import com.indiacleantool.cleantool.web.companymodules.employeeservice.model.dto.EmployeeServiceRelation;
import com.indiacleantool.cleantool.web.companymodules.employeeservice.model.entity.EmployeeService;
import com.indiacleantool.cleantool.web.companymodules.employeeservice.model.dto.EmployeeServiceRequest;
import com.indiacleantool.cleantool.web.companymodules.employeeservice.model.dto.EmployeeServiceRequestBody;
import com.indiacleantool.cleantool.exceptions.employeeservice.EmployeeServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

@Service
public class EmployeeServiceSprService {

    @Autowired
    private EmployeeServiceRepository repository;

    @Autowired
    private DataSource dataSource;

    public Iterable<EmployeeService> saveEmployeeService(EmployeeServiceRequest employeeServiceRequest) {

        if (employeeServiceRequest.getEmployeeServices() == null) {
            throw new EmployeeServiceException("No Service code list available");
        }
        repository.deleteEmployeeServicebyCompanyCode(employeeServiceRequest.getCompanyCode());
        List<EmployeeService> listEmployeeServices = new ArrayList<>();
        String companyCode = employeeServiceRequest.getCompanyCode();
        for (EmployeeServiceRequestBody employeeServiceRequestBody : employeeServiceRequest.getEmployeeServices()) {
            for (String serviceCode : employeeServiceRequestBody.getServiceCodes()) {
                EmployeeService employeeService = new EmployeeService();
                employeeService.setCompanyCode(companyCode);
                employeeService.setServiceCode(serviceCode);
                employeeService.setEmployeeCode(employeeServiceRequestBody.getEmployeeCode());
                listEmployeeServices.add(employeeService);
            }
        }
        return repository.saveAll(listEmployeeServices);
    }

    public List<EmployeeServiceRelation> getEmployeeServiceRelationByCompanyCode(String companyCode) {
        List<EmployeeServiceRelation> listEmployeeService = new ArrayList<>();
        Connection connection = null;
        try {

            connection = dataSource.getConnection();
            String query = " select ES.employee_code,ES.service_code,ES.company_code from employee_service AS ES "
                    + " left join employee as E on ES.employee_code=E.employee_code " + " where ES.company_code='"
                    + companyCode + "' ";
            ResultSet resultSet = connection.prepareStatement(query).executeQuery();

            HashMap<String, ArrayList<String>> hmEmployeeService = new HashMap<>();

            while (resultSet.next()) {
                String employeeCode = resultSet.getString(1);
                String serviceCode = resultSet.getString(2);
                ArrayList<String> arrServiceCode = hmEmployeeService.get(employeeCode);
                if (arrServiceCode == null) {
                    arrServiceCode = new ArrayList<>();
                    arrServiceCode.add(serviceCode);
                    hmEmployeeService.put(employeeCode, arrServiceCode);
                } else {
                    arrServiceCode.add(serviceCode);
                }
            }

            Set<String> keyEmployeeCode = hmEmployeeService.keySet();
            for (String employeeCode : keyEmployeeCode) {
                EmployeeServiceRelation employeeServiceRelation = new EmployeeServiceRelation();
                employeeServiceRelation.setEmployeeCode(employeeCode);
                employeeServiceRelation.setServiceCodes(hmEmployeeService.get(employeeCode));
                listEmployeeService.add(employeeServiceRelation);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listEmployeeService;
    }

    public long countByCompanyCode(String companyCode) {
        return repository.countByCompanyCode(companyCode);
    }

    public EmployeeService findByCompanyCodeAndServiceCodeAndEmployeeCode(String companyCode, String serviceCode,
            String employeeCode) {
        return repository.findByCompanyCodeAndServiceCodeAndEmployeeCode(companyCode, serviceCode, employeeCode);
    }

    public List<EmployeeService> findByCompanyCodeAndServiceCode(String companyCode, String serviceCode) {

        Optional<List<EmployeeService>> listEmployeeService = repository.findByCompanyCodeAndServiceCode(companyCode,
                serviceCode);

        if (listEmployeeService.isPresent()) {
            return listEmployeeService.get();
        } else {
            throw new EmployeeServiceException("Employee are not available to assigne this service");
        }
    }

}
