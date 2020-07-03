package com.demo.application.restWeb.DemoRestWebApplication;

import com.demo.application.restWeb.DemoRestWebApplication.Beans.Users;
import com.demo.application.restWeb.DemoRestWebApplication.Repository.UserRepository;
import com.demo.application.restWeb.DemoRestWebApplication.UserService.UserService;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.Generated;
import java.net.URI;
import java.util.List;


@RestController
public class UserController {

    @Autowired
    UserService service;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/Users")
    public ResponseEntity<Users> createUsers(@RequestBody Users users) {
        Users createdUser = service.createUsers(users);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/Users")
    public List<Users> getAllUsers() throws Exception {
        List<Users> userList = service.getAllUsers();
        if (userList == null) {
            throw new Exception("No Users found");
        }
        return userList;
    }

    @GetMapping("/Users/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable String id) {
        return ResponseEntity.ok().body(service.findUsersById(id));
    }

    @PutMapping("/Users/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable String id, @RequestBody Users users) {
        users.setId(id);
        return ResponseEntity.ok().body(this.service.updateUsers(users));
    }

    @DeleteMapping("/Users/{id}")
    public void deleteUser(@PathVariable String id) {
        this.service.deleteUsersById(id);

    }
}
