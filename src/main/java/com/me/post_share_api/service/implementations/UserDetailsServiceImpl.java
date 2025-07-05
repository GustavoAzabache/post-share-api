package com.me.post_share_api.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.me.post_share_api.entity.UserEntity;
import com.me.post_share_api.exception.UserNotFoundException;
import com.me.post_share_api.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("El usuario " + username + "no ha sido encontrado."));

        return User.builder()
                .username(username)
                .password(userEntity.getPassword())
                .authorities(new SimpleGrantedAuthority(userEntity.getRole().getName().name()))
                .build();
    }

}
