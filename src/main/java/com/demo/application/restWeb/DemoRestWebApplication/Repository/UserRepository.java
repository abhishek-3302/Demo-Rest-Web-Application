package com.demo.application.restWeb.DemoRestWebApplication.Repository;

import com.demo.application.restWeb.DemoRestWebApplication.Beans.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<Users, String> {

}
