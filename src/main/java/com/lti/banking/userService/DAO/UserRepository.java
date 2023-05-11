
package com.lti.banking.userService.DAO;

import com.lti.banking.userService.Pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;


@Repository
public interface UserRepository extends JpaRepository<UserEntity,String>{

    @Query(value="SELECT u.approved_status from users u WHERE u.user_name=:username AND u.pass_word=:password", nativeQuery = true)
    String findByUserNameAndPassword(@Param("username") String username, @Param("password") String password);

    @Query(value="SELECT u.approved_status from users u WHERE u.user_name=:username AND u.pass_word=:password", nativeQuery = true)
    String getApproved(@Param("username") String username, @Param("password") String password);

}

