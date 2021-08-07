package com.indiacleantool.cleantool.web.employeemodules.employeedutymanagment.entity;

import com.indiacleantool.cleantool.commonmodels.usersmodels.employee.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Generated;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "employee_duty_status")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDutyStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String employeeCode;

    private LocalDate date;

    private LocalTime startTime;

    private LocalTime endTime;

    private boolean isStarted;

    private boolean isEnded;

    @OneToOne
    private Employee employeeId;


}
