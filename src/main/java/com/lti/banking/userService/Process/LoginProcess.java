package com.lti.banking.userService.Process;

import com.lti.banking.userService.JwtUtil.JwtUtil;
import com.lti.banking.userService.Pojo.LoginModule;
import com.lti.banking.userService.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class LoginProcess {
    @Autowired
    private UserService userService;

    @Autowired
   private JwtUtil jwtUtil;

 /*   @Autowired
    private AuthenticationManager authenticationManager;*/

    public String checkLogin(LoginModule loginModule){
    if(loginModule.getUserName()!=null && loginModule.getPassword()!=null) {
        String isThere=userService.checkLogin(loginModule);
        return isThere!=null && isThere.equals("y") ? "1234567" : null;
     }
    else {
        return null;
        }
    }

    public String getAll(LoginModule authRequest) throws Exception {
        String isThere="";
        try {
             isThere=userService.checkLogin(authRequest);
            if("y".equals(isThere)) {
               // System.out.println(jwtUtil.generateToken(authRequest.getUserName()));
               return null;
            }
            else
                return new String("inavalid username/password");
        } catch (Exception ex) {
            throw new Exception("inavalid username/password");
        }



    }
}
