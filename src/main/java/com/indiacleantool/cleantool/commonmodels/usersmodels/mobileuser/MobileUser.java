package com.indiacleantool.cleantool.commonmodels.usersmodels.mobileuser;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "mobile_user")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name="sp_generateMobileUserCode",
                procedureName = "sp_generateMobileUserCode",
                parameters = {
                    @StoredProcedureParameter(mode = ParameterMode.IN,name = "mobile_user_id",type = Long.class),
                    @StoredProcedureParameter(mode = ParameterMode.OUT,name = "out_mobile_user_code",type = String.class)
                }
        )
})
public class MobileUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First Name cannot be blank")
    private String firstName;

    @NotBlank(message = "Last Name cannot be blank")
    private String lastName;

    @NotNull(message = "Date of birth cannot be empty")
    @JsonFormat(pattern = "yyyy-MM-dd",shape=JsonFormat.Shape.STRING)
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDate dateOfBirth;

    @NotBlank(message = "Mobile number cannot be blank")
    @Size(min = 10,max = 10,message = "Mobile number should be 10 digits")
    @Column(unique = true)
    private String mobile;

    @NotBlank(message = "Email cannot be blank")
    @Size(min = 5,max = 50,message = "Email cannot be blank")
    @Column(unique = true)
    private String email;


    private String mobileUserCode;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date created_at;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updated_at;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileUserCode() {
        return mobileUserCode;
    }

    public void setMobileUserCode(String mobileUserCode) {
        this.mobileUserCode = mobileUserCode;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    @PrePersist
    protected void onCreate(){
        this.created_at = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        this.updated_at=new Date();
    }


}
