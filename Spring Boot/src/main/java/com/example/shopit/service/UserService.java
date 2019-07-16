package com.example.shopit.service;

import com.example.shopit.encryption.PropertyServiceForJasyptStarter;
import com.example.shopit.dao.UserRepository;
import com.example.shopit.model.Login;
import com.example.shopit.model.User;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repo;
    private final PropertyServiceForJasyptStarter jasyptStarter;

    public UserService(UserRepository repo, PropertyServiceForJasyptStarter jasyptStarter) {
        this.repo = repo;
        this.jasyptStarter = jasyptStarter;
    }

    public List<User> listAll(){
        return repo.findAll();
    }

    public void save(User user) {

        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(jasyptStarter.getProperty());
        user.setPassword(textEncryptor.encrypt(user.getPassword()));
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
        //System.setProperty("jasypt.encryptor.password", "password");
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(jasyptStarter.getProperty());
        return list.stream().anyMatch(user -> (user.getUsername().equals(login.getUsername()) && textEncryptor.decrypt(user.getPassword()).equals(login.getPassword())));
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

        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(jasyptStarter.getProperty());
        return list.stream().filter(user -> {
            return user.getUsername().equals(login.getUsername()) && textEncryptor.decrypt(user.getPassword()).equals(login.getPassword());
        }).findFirst().get();
    }
}
