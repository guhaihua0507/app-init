package com.ghh.sample.security;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!"haihua".equals(username)) {
            throw new UsernameNotFoundException("user not exist");
        }
        //TODO load user from db
        return new User("haihua", new BCryptPasswordEncoder().encode("123"), CollectionUtils.emptyCollection());
    }
}
