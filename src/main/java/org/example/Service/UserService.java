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
public class UserService {
    private final UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User save(User user){
        return userRepository.save(user);
    }
    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
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


}
