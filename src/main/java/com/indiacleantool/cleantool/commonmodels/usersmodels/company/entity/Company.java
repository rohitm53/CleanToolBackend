package com.indiacleantool.cleantool.commonmodels.usersmodels.company.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.indiacleantool.cleantool.commonmodels.usersmodels.login.entity.UserCredentials;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "company_user")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Company code cannot be blank")
    @Size(min = 2 , max = 6 , message = "Please use 2 to 6 charaters for company code")
    @Column(updatable = false,unique = true)
    private String companyCode;

    @NotBlank(message = "Company name cannot be blank")
    private String companyName;

    @NotBlank(message = "Company address cannot be blank")
    private String address;

    @NotBlank(message = "Company email cannot be blank")
    private String email;

    private Double latitude;

    private Double longitude;


    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "company")
    @JsonIgnore
    private UserCredentials userCredentials;

    public Company() {
    }

    public Company(long id, String companyCode, String companyName, String address,
                   String email,Double latitude,Double longitude) {
        this.id = id;
        this.companyCode = companyCode;
        this.companyName = companyName;
        this.address = address;
        this.email = email;
        this.latitude=latitude;
        this.longitude=longitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public UserCredentials getUserCredentials() {
        return userCredentials;
    }

    public void setUserCredentials(UserCredentials userCredentials) {
        this.userCredentials = userCredentials;
    }
}
