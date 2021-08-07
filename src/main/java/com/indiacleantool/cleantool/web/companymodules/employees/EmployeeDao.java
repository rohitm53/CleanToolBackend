package com.indiacleantool.cleantool.web.companymodules.employees;

import com.indiacleantool.cleantool.dao.BaseDao;
import com.indiacleantool.cleantool.commonmodels.usersmodels.employee.Employee;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;
import java.util.StringJoiner;

@Service
public class EmployeeDao extends BaseDao {

    public List<Employee> getAllCompanyAvailableEmployee(String companyCode, String strDate){

        LocalDate date = LocalDate.parse(strDate);

        StringJoiner query = new StringJoiner(" ");

        query
                .add("select * from employee where company_code = :company_code ")
                .add(" and employee_code not in ")
                .add(" ( ")
                .add(" select emp_code from employee_assigned_service where ")
                .add(" company_code = :company_code ")
                .add(" and is_completed = 0 and ")
                .add(" date(scheduled_date) = :date")
                .add(" ) ");

        Query nativeQuery = getEntityManager().createNativeQuery(query.toString(),Employee.class);
        nativeQuery.setParameter("company_code",companyCode);
        nativeQuery.setParameter("date",date.toString());

        return nativeQuery.getResultList();

    }
}
