package com.demo.application.restWeb.DemoRestWebApplication.UserService;

import com.demo.application.restWeb.DemoRestWebApplication.Beans.Users;
import com.demo.application.restWeb.DemoRestWebApplication.ErrorHandling.UserNotFoundException;
import com.demo.application.restWeb.DemoRestWebApplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ServiceUserImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public Users createUsers(Users users) {
         return repository.save(users);
    }

    @Override
    public List<Users> getAllUsers() {
        return this.repository.findAll();
    }

    @Override
    public Users findUsersById(int id) {
        Optional<Users> FoundUser = this.repository.findById(id);
        if(FoundUser.isPresent())
        {
            return FoundUser.get();
        }
        else{
            throw new UserNotFoundException("User not found with id : " + id);
        }
    }

    @Override
    public void deleteUsersById(int id) {
//        repository.deleteById(id);
        Optional<Users> deletedUser = this.repository.findById(id);
        if(deletedUser.isPresent())
        {
            this.repository.delete(deletedUser.get());
        }
        else{
            throw new UserNotFoundException("User not found with id : " + id);
        }
    }
    @Override
    public Users updateUsers(Users UpdatedUser) {
        Optional < Users > userDb = this.repository.findById(UpdatedUser.getId());

        if (userDb.isPresent()) {
            Users UserUpdate = userDb.get();
            UserUpdate.setId(UpdatedUser.getId());
            UserUpdate.setFirstname(UpdatedUser.getFirstname());
            UserUpdate.setLastname(UpdatedUser.getLastname());
            UserUpdate.setEmail(UpdatedUser.getEmail());
            UserUpdate.setPassword(UpdatedUser.getPassword());
            repository.save(UserUpdate);
            return UserUpdate;
        } else {
            throw new UserNotFoundException("User not found with id : " + UpdatedUser.getId());
        }
    }
}
