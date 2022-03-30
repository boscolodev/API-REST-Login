package com.boscolo.dev.Controllers;

import com.boscolo.dev.Entities.Status;
import com.boscolo.dev.Entities.User;
import com.boscolo.dev.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    //Endpoint for User Registration
    @PostMapping("/register")
    public Status registerNewUser(@Validated @RequestBody User newUser) {
        //Create a User list searching Username and Password in Respository
        List<User> users = userRepository.findAll();
        System.out.println("New User: " + newUser.toString());

        //Check in list if user exist, otherwise insert a new User
        for(User user : users) {
            System.out.println("Registered User: " + newUser.toString());

            if (user.equals(newUser)) {
                System.out.println("User Already Exist !");
                return Status.User_Already_Exist;
            }
        }

        userRepository.save(newUser);
        return Status.Success;

     }

    //Endpoint for User Login
    @PostMapping("/login")
    public Status loginUser(@Validated @RequestBody User user){
        //Create a User list searching Username and Password in Respository
        List<User> users = userRepository.findAll();

        //Check in list if user exist, if true set LoggedIn True
        for(User other : users){
            if(other.equals(user)){
                user.setLoginStatus(true);
                userRepository.save(user);
                return Status.Success;
            }
        }
        //Return false in case wrong login
        return Status.Failure;
    }

    //Endpoint for Logout
    @PostMapping("/logout")
    public Status logUserOut(@Validated @RequestBody User user) {
        //Create a User list searching Username and Password in Respository
        List<User> users = userRepository.findAll();
        //Check in list if user exist, if true set LoggedIn False
        for (User other : users) {
            if (other.equals(user)) {
                user.setLoginStatus(false);
                userRepository.save(user);
                return Status.Success;
            }
        }
        return Status.Failure;
    }

    //Endpoint for Test, to truncate a user table
    @DeleteMapping(value = "/delete-all")
    public Status deleteUsers() {
        userRepository.deleteAll();
        return Status.Success;
    }

}