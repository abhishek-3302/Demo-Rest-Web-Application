package com.demo.application.restWeb.DemoRestWebApplication.UserService;

import com.demo.application.restWeb.DemoRestWebApplication.Beans.Users;

import java.util.List;

public interface UserService {


     Users createUsers(Users users);

     List<Users> getAllUsers();

     Users findUsersById(String id);

     void deleteUsersById(String id);

     Users updateUsers(Users users);

     List<Users> findByFirstName(String firstname);

}
