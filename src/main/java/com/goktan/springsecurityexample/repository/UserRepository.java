package com.goktan.springsecurityexample.repository;

import com.goktan.springsecurityexample.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
      User findByUserName(String username);

}
