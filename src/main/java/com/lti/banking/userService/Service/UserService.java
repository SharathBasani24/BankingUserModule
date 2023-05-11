package com.lti.banking.userService.Service;

import com.lti.banking.userService.DAO.UserEntity;
import com.lti.banking.userService.DAO.UserRepository;
import com.lti.banking.userService.JwtUtil.JwtUtil;
import com.lti.banking.userService.Pojo.LoginModule;
import com.lti.banking.userService.Pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private JwtUtil jwtUtil;

    public String checkLogin(LoginModule loginDetails) {
        String user= repo.findByUserNameAndPassword(loginDetails.getUserName(), loginDetails.getPassword());
      if(user!=null)
          return user;
      else
          return null;
    }

    public User getAll() {
     List<UserEntity> user=  repo.findAll();
     User obj= new User();
        for (UserEntity u:
             user) {
          obj.setApproved(u.getApproved());
        }
        return obj;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
