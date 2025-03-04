package org.example.Service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.Entity.User;
import org.example.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements CommandLineRunner {
    private final UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }


    public User findUserByName(String name){
        return userRepository.findUserByName(name);

    }
    public void createUser(String name){
       userRepository.createUser(name);
    }
    public void deleteUserByName(String name){
        userRepository.deleteUserByName(name);
}
    public void deleteAllUsers(){
        userRepository.deleteAllUsers();
}

    @Override
    public void run(String... args){
        findAll().forEach(System.out::println);
        createUser("user_6");
        findAll().forEach(System.out::println);
        System.out.println(findUserByName("user_1"));
        //deleteUserByName("user_2");
        findAll().forEach((System.out::println));
        //deleteAllUsers();
        findAll().forEach((System.out::println));

    }
}
