package com.goktan.springsecurityexample.controller;


import com.goktan.springsecurityexample.entity.User;
import com.goktan.springsecurityexample.enums.Role;
import com.goktan.springsecurityexample.request.UserRequest;
import com.goktan.springsecurityexample.security.JwtTokenProvider;
import com.goktan.springsecurityexample.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")

public class AuthController {

    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private UserService userService;
    private PasswordEncoder passwordEncoder;


    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public String login(@RequestBody UserRequest userRequest){
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userRequest.getUserName(),userRequest.getPassword());
        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtTokenProvider.generateJwtToken(auth);
        return "Bearer "+ jwtToken;
    }


    @PostMapping("/register")
    public ResponseEntity<String> register (@RequestBody UserRequest userRequest){
        if (userService.getOneUserByUserName(userRequest.getUserName())!= null){
            new ResponseEntity<>("Username already in use", HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setUserName(userRequest.getUserName());
        user.setRole(Role.USER);
        userService.createOneUser(user);
        return  new ResponseEntity<>("Successfully",HttpStatus.CREATED);
    }
}
