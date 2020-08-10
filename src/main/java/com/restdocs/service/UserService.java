package com.restdocs.service;

import com.restdocs.domain.User;
import com.restdocs.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    final
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public User getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void deleteUserByUsername(String username){
        userRepository.deleteByUsername(username);
    }

    public User getUserById(Long id){
        return userRepository.findById(id).get();
    }
}
