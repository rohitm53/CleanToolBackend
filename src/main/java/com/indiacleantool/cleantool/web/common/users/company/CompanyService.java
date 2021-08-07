package com.indiacleantool.cleantool.web.common.users.company;

import com.indiacleantool.cleantool.common.Constants;
import com.indiacleantool.cleantool.security.SecurityConstants;
import com.indiacleantool.cleantool.usermanagment.UserCredentialsRepository;
import com.indiacleantool.cleantool.commonmodels.usersmodels.company.entity.Company;
import com.indiacleantool.cleantool.commonmodels.usersmodels.login.entity.Role;
import com.indiacleantool.cleantool.commonmodels.usersmodels.login.entity.UserCredentials;
import com.indiacleantool.cleantool.exceptions.userexception.company.CompanyCodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserCredentialsRepository userCredentialsRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Company saveOrUpdateCompany(Company company){

        Company updatedCompany=null;
        try{
            if(company.getId()==null){
                updatedCompany=companyRepository.save(company);

                UserCredentials userCredentials = new UserCredentials(company.getCompanyCode(), Constants.InitialPassword);
                userCredentials.setPassword(bCryptPasswordEncoder.encode(userCredentials.getPassword()));//encrypting password
                List<Role> roles = new ArrayList<>();
                roles.add(new Role(SecurityConstants.ROLE_COMPANY));
                userCredentials.setRoles(roles);
                userCredentials.setCompany(updatedCompany);
                userCredentialsRepository.save(userCredentials);

            }else{
                updatedCompany=companyRepository.save(company);
            }
            return updatedCompany;
        }catch (Exception e){
            throw new CompanyCodeException("Company code '"+company.getCompanyCode()+"' already existed ");
        }
    }

    public Company findByCompanyCode(String company_code){

        Company company = companyRepository.findByCompanyCode(company_code);

        if(company==null){
            throw new CompanyCodeException("No company available with code : "+company_code);
        }

        return company;
    }

}
