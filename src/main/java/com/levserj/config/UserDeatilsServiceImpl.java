package com.levserj.config;


import com.levserj.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Serhii Levchynskyi on 11.05.2016.
 */
@Component
public class UserDeatilsServiceImpl implements UserDetailsService {

    private static final Logger LOG = LoggerFactory.getLogger(UserDeatilsServiceImpl.class);

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        LOG.debug("In the loadUserByUsername method in Custom UserService");
        com.levserj.domain.User user = userService.readUserByEmail(s);
        if (user != null) {
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(user.getAuthorities()));
            return new User(user.getEmail(), user.getPassword(), authorities);
        }
        throw new UsernameNotFoundException("User with E-mail " + s + " not found");
    }
}
