package com.example.shopit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserTests {

    public UserRepository repository;
    public UserService serviceUnderTest;
    public PropertyServiceForJasyptStarter jasyptStarter;

    @Before
    public void setup() {
        repository = Mockito.mock(UserRepository.class);
        serviceUnderTest = new UserService(repository,jasyptStarter);
    }

    @Test
    public void shouldFindUserById() {
        User user = new User();
        user.setUserid(1L);
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(user));

        User result = serviceUnderTest.get(1L);

        Assert.assertEquals(result.getUserid(), user.getUserid());
    }

    @Test
    public void shouldGetAllItems() {
        User user = new User();
        user.setUserid(1L);
        ArrayList<User> users = new ArrayList<User>(Collections.singletonList(user));
        Mockito.when(repository.findAll()).thenReturn(users);

        List<User> items = serviceUnderTest.listAll();
        Assert.assertEquals(items.get(0).getUserid(),user.getUserid());

    }

    @Test
    public void shouldValidateUser() {
        User user = new User();
        user.setUserid(1L);
        user.setUsername("ankith@gmail.com");
        user.setPassword("3point@42");
        ArrayList<User> users = new ArrayList<User>(Collections.singletonList(user));
        Mockito.when(repository.findAll()).thenReturn(users);

        Login login = new Login();
        login.setUsername("ankith@gmail.com");
        login.setPassword("3point@42");

        Assert.assertTrue(serviceUnderTest.validate(login));
    }

    @Test
    public void shouldCheckUsername() {
        User user = new User();
        user.setUserid(1L);
        user.setUsername("ankith@gmail.com");
        ArrayList<User> users = new ArrayList<User>(Collections.singletonList(user));
        Mockito.when(repository.findAll()).thenReturn(users);

        Assert.assertTrue(serviceUnderTest.checkUsername("ankith@gmail.com"));
    }

    @Test
    public void shouldCheckPhone() {
        User user = new User();
        user.setUserid(1L);
        user.setPhone("8906728381");
        ArrayList<User> users = new ArrayList<User>(Collections.singletonList(user));
        Mockito.when(repository.findAll()).thenReturn(users);

        Assert.assertTrue(serviceUnderTest.checkPhone("8906728381"));
    }

    @Test
    public void shouldLoginUser()
    {
        User user = new User();
        user.setUserid(1L);
        user.setUsername("ankith@gmail.com");
        user.setPassword("3point@42");
        ArrayList<User> users = new ArrayList<User>(Collections.singletonList(user));
        Mockito.when(repository.findAll()).thenReturn(users);

        Login login = new Login();
        login.setUsername("ankith@gmail.com");
        login.setPassword("3point@42");

        User user2 = serviceUnderTest.loginAs(login);
        Assert.assertEquals(user2.getUserid(),user.getUserid());
    }

}
