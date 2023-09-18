package com.goktan.springsecurityexample.service;

import com.goktan.springsecurityexample.entity.User;
import com.goktan.springsecurityexample.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService( UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return  userRepository.findAll();
    }

    public User getOneUserById(Long userId){
        return userRepository.findById(userId).orElse(null);
    }

    public User createOneUser(User newUser){

        return  userRepository.save(newUser);
    }


    public void deleteOneUserById(Long userId){
        userRepository.deleteById(userId);
    }

    public User updateOneUser(Long userId, User userUpdate){
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()){
            User updateToUser = user.get();
            updateToUser.setUserName(userUpdate.getUserName());
            updateToUser.setPassword(userUpdate.getPassword());
            userRepository.save(updateToUser);
            return updateToUser;
        }else return null;
    }

    public User getOneUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}

