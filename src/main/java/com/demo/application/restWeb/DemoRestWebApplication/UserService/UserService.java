package com.demo.application.restWeb.DemoRestWebApplication.UserService;

import com.demo.application.restWeb.DemoRestWebApplication.Beans.Users;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserService {

    /**
     * Method to create new employees in the db using mongo-db repository.
     */
     Users createUsers(Users users);
    /**
     * Method to fetch all employees from the db using mongo-db repository.
     */
     List<Users> getAllUsers();

    /**
     * Method to fetch users by id using mongo-db repository.
     */
     Users findUsersById(int id);

    /**
     * Method to delete users by id using mongo-db repository.
     */
     void deleteUsersById(int id);

    /**
     * Method to update users by id using mongo-db repository.
     */

      Users updateUsers(Users users);

    /**
     * Method to delete all users using mongo-db repository.
     */

}
