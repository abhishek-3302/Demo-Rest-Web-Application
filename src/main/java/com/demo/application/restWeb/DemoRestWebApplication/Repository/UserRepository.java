package com.demo.application.restWeb.DemoRestWebApplication.Repository;

import com.demo.application.restWeb.DemoRestWebApplication.Beans.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<Users, Integer> {

}
