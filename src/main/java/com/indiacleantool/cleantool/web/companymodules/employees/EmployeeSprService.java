package com.indiacleantool.cleantool.web.companymodules.employees;

import com.indiacleantool.cleantool.common.Constants;
import com.indiacleantool.cleantool.exceptions.common.CommonGenericException;
import com.indiacleantool.cleantool.security.SecurityConstants;
import com.indiacleantool.cleantool.usermanagment.UserCredentialsRepository;
import com.indiacleantool.cleantool.commonmodels.usersmodels.employee.Employee;
import com.indiacleantool.cleantool.commonmodels.usersmodels.login.entity.Role;
import com.indiacleantool.cleantool.commonmodels.usersmodels.login.entity.UserCredentials;
import com.indiacleantool.cleantool.exceptions.userexception.employees.EmployeeCodeException;
import com.indiacleantool.cleantool.web.companymodules.companyavailabletimeslots.CompanyTimeSlotsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class EmployeeSprService {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private UserCredentialsRepository userCredentialsRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private CompanyTimeSlotsService companyTimeSlotsService;

    @Autowired
    private EmployeeDao employeeDao;


    @Transactional
    public Employee saveOrUpdateEmployee(Employee employee, String companyCode){

        try{
            Long id = employee.getId();
            Employee savedEmployee = repository.save(employee);
            if(id==null){
                String empCode= repository.generateEmployeeCode(savedEmployee.getId());
                savedEmployee.setEmployeeCode(empCode);

                UserCredentials userCredentials = new UserCredentials(empCode,Constants.InitialPassword);
                userCredentials.setPassword(bCryptPasswordEncoder.encode(userCredentials.getPassword()));
                List<Role> roles = new ArrayList<>();
                Role role = new Role(SecurityConstants.ROLE_EMPLOYEE);
                roles.add(role);
                userCredentials.setRoles(roles);
                userCredentials.setEmployee(savedEmployee);
                userCredentialsRepository.save(userCredentials);

                ///Incrementing Available Employee Count in CompanyAvailableTimeSlots table
                companyTimeSlotsService.updateEmployeeCountInCompanyTimeSlots(companyCode , true);

            }
            return savedEmployee;
        }catch (DataIntegrityViolationException e){
            throw new CommonGenericException("EmailId/Phone number already registered");
        }
    }

    public Employee findByEmployeeCode(String employeeCode){

        Employee employee = repository.findByEmployeeCode(employeeCode);
        if(employee==null){
            throw new EmployeeCodeException("No employee available with code : "+employeeCode+".");
        }
        return employee;
    }

    public Iterable<Employee> findAllEmployees(){
        return repository.findAll();
    }

    public Iterable<Employee> findAllByCompanyCode(String companyCode){
        return repository.findAllByCompanyCode(companyCode);
    }

    @Transactional
    public void deleteEmployeeByCode(String employeeCode, String companyCode ){
        Employee employee = findByEmployeeCode(employeeCode);
        if(employee!=null){
            repository.delete(employee);
            ///Decrement Available Employee Count in CompanyAvailableTimeSlots table
            companyTimeSlotsService.updateEmployeeCountInCompanyTimeSlots(companyCode , false);
        }
    }

    public Long getCountByCompanyCode(String companyCode){
        return repository.countByCompanyCode(companyCode);
    }

    public Employee findEmployeeById(Long id){

        Optional<Employee> employee = repository.findById(id);
        if(employee.isPresent()){
            return employee.get();
        }else{
            throw new EmployeeCodeException("No employee available with Id : "+id+".");
        }
    }

    public List<Employee> getAllCompanyAvailableEmployee(String companyCode, String strDate){
        try  {
            return employeeDao.getAllCompanyAvailableEmployee(companyCode,strDate);
        } catch (DateTimeParseException e) {
            throw new CommonGenericException("Invalid date/time");
        } catch (Exception e) {
            throw new CommonGenericException(e.getMessage());
        }
    }
}
