package com.indiacleantool.cleantool.usermanagment;

import com.indiacleantool.cleantool.commonmodels.usersmodels.login.dto.LoginRequest;
import com.indiacleantool.cleantool.commonmodels.usersmodels.login.dto.LoginResponse;
import com.indiacleantool.cleantool.exceptions.MapValidationExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.indiacleantool.cleantool.security.SecurityConstants.TOKEN_PREFIX;

@RestController
@RequestMapping("/api/users")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AppUserDetailService userDetailService;

    @Autowired
    private MapValidationExceptionService validationExceptionService;

    @RequestMapping(value = "/authenticate",method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody LoginRequest loginRequest, BindingResult result) throws Exception {

        ResponseEntity<?> errorMap = validationExceptionService.validateRESTRequest(result);
        if(errorMap!=null){
            return errorMap;
        }
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword())
            );
        }catch (Exception e){
            throw new Exception("Incorrect username or password",e);
        }

        final UserDetails userDetails = userDetailService.loadUserByUsername(loginRequest.getUsername());

        String jwtToken = TOKEN_PREFIX+jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new LoginResponse(jwtToken));
    }

}
