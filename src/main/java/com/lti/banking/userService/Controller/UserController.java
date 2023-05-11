package com.lti.banking.userService.Controller;

import com.lti.banking.userService.JwtUtil.AuthToken;
import com.lti.banking.userService.JwtUtil.JwtUtil;
import com.lti.banking.userService.Pojo.LoginModule;
import com.lti.banking.userService.Pojo.User;
import com.lti.banking.userService.Process.LoginProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


import java.sql.SQLException;

@RestController
public class UserController {

    @Autowired
    LoginProcess loginProcess;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<String> validateLogin(@RequestBody LoginModule obj) throws SQLException {
        String accountNumber = loginProcess.checkLogin(obj);
        if (!StringUtils.isEmpty(accountNumber))
            return new ResponseEntity("Login Success user account number is " + accountNumber, HttpStatus.OK);
        else
            return new ResponseEntity("Login Failed user Details not Found, Please Register. ", HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<AuthToken> generateToken(@RequestBody LoginModule loginAdmin) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginAdmin.getUserName(),
                        loginAdmin.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtUtil.generateToken(authentication);
        return ResponseEntity.ok(new AuthToken(token));
    }
}
