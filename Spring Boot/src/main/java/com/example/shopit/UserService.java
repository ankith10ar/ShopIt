package com.example.shopit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    List<User> listAll(){
        return repo.findAll();
    }

    public void save(User user) {
        repo.save(user);
    }

    public User get(long id){
        if(repo.findById(id).isPresent())
            return repo.findById(id).get();
        return null;
    }

    public void delete(Long id){
        repo.deleteById(id);
    }

    public boolean validate(Login login) {
        List<User> list = listAll();
        return list.stream().anyMatch(user -> (user.getUsername().equals(login.getUsername()) && user.getPassword().equals(login.getPassword())));
    }

    public boolean checkUsername(String username) {
        List<User> list = listAll();
        return list.stream().anyMatch(user -> user.getUsername().equals(username));
    }

    public boolean checkPhone(String phone) {
        List<User> list = listAll();
        return list.stream().anyMatch(user -> user.getPhone().equals(phone));
    }

    public User loginAs(Login login) {
        List<User> list = listAll();
        return list.stream().filter(user -> {
            return user.getUsername().equals(login.getUsername()) || user.getPassword().equals(login.getPassword());
        }).findFirst().get();
    }
}
