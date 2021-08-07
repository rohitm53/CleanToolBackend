package com.indiacleantool.cleantool.web.common.users.mobileuser;

import com.indiacleantool.cleantool.common.Constants;
import com.indiacleantool.cleantool.commonmodels.usersmodels.login.entity.Role;
import com.indiacleantool.cleantool.commonmodels.usersmodels.login.entity.UserCredentials;
import com.indiacleantool.cleantool.commonmodels.usersmodels.mobileuser.MobileUser;
import com.indiacleantool.cleantool.exceptions.common.CommonGenericException;
import com.indiacleantool.cleantool.exceptions.userexception.mobile.MobileUserCodeException;
import com.indiacleantool.cleantool.security.SecurityConstants;
import com.indiacleantool.cleantool.usermanagment.UserCredentialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MobileUserService {

    @Autowired
    private MobileUserRepository mobileUserRepository;

    @Autowired
    private UserCredentialsRepository userCredentialsRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public MobileUser saveOrUpdateMobileUser(MobileUser mobileUser) {

        try {
            Long id = mobileUser.getId();
            MobileUser saveMobileUser = mobileUserRepository.save(mobileUser);

            if (id == null) {

                String mobileUserCode = mobileUserRepository.generateMobileUserCode(saveMobileUser.getId());
                saveMobileUser.setMobileUserCode(mobileUserCode);

                UserCredentials userCredentials = new UserCredentials(mobileUserCode, Constants.InitialPassword);
                userCredentials.setPassword(bCryptPasswordEncoder.encode(userCredentials.getPassword()));
                List<Role> roles = new ArrayList<>();
                Role role = new Role(SecurityConstants.ROLE_MOBILE_USER);
                roles.add(role);
                userCredentials.setRoles(roles);
                userCredentials.setMobileUser(mobileUser);
                userCredentialsRepository.save(userCredentials);

            }
            return saveMobileUser;
        } catch (DataIntegrityViolationException e) {
            throw new CommonGenericException("Email and Mobile number are already registered");
        } catch (Exception e) {
            throw new CommonGenericException("Error while saving data");
        }
    }

    public MobileUser findMobileUserByCode(String mobile_user_code) {

        MobileUser mobileUser = mobileUserRepository.findByMobileUserCode(mobile_user_code);
        if (mobileUser == null) {
            throw new MobileUserCodeException("No mobile user available with code : " + mobile_user_code);
        }
        return mobileUser;
    }

}
