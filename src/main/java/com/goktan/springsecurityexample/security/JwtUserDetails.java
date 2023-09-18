package com.goktan.springsecurityexample.security;

import com.goktan.springsecurityexample.entity.User;
import com.goktan.springsecurityexample.enums.Role;
import com.goktan.springsecurityexample.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data

public class JwtUserDetails  implements UserDetails {

    Long id;
    String username;
    String password;
    Role role;

    public JwtUserDetails(User user){
        this.id = user.getId();
        this.username = user.getUserName();
        this.password = user.getPassword();
        this.role = user.getRole();
    }



    @Override

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return role.name();
            }
        });
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
