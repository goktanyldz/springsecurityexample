package com.goktan.springsecurityexample.service;

import com.goktan.springsecurityexample.entity.User;
import com.goktan.springsecurityexample.repository.UserRepository;
import com.goktan.springsecurityexample.security.JwtUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    UserRepository userRepository;
    public UserDetailsServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        return new JwtUserDetails(user);

    }


    public UserDetails loadUserById(Long userId) throws Exception{
        User user = userRepository.findById(userId).get();
        return new JwtUserDetails(user);
    }
}
