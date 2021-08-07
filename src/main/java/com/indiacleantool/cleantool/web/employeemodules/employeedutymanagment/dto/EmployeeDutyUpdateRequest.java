package com.indiacleantool.cleantool.web.employeemodules.employeedutymanagment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDutyUpdateRequest {

    @NotBlank(message = "Employee code cannot be blank")
    private String employeeCode;

    @NotNull(message = "Company code cannot be blank")
    private String companyCode;

    @NotNull(message = "Date cannot be blank")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]", shape = JsonFormat.Shape.STRING)
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDate date;

    private LocalTime startTime;

    private LocalTime endTime;


}
