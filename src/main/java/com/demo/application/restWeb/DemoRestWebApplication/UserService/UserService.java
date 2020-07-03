package com.demo.application.restWeb.DemoRestWebApplication.UserService;

import com.demo.application.restWeb.DemoRestWebApplication.Beans.Users;
import org.bson.types.ObjectId;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserService {


     Users createUsers(Users users);

     List<Users> getAllUsers();

//     Users findUsersById(int id);
//
//     void deleteUsersById(int id);

     Users findUsersById(String id);

     void deleteUsersById(String id);

     Users updateUsers(Users users);

}
