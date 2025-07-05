package com.me.post_share_api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.me.post_share_api.entity.RoleEntity;
import com.me.post_share_api.entity.UserEntity;
import com.me.post_share_api.entity.enums.RoleEnum;
import com.me.post_share_api.repository.RoleRepository;
import com.me.post_share_api.repository.UserRepository;

@Configuration
public class DataInitializer {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner DBInit() {
        return args -> {
            RoleEntity roleAdmin = new RoleEntity(RoleEnum.ROLE_ADMIN);
            roleRepository.save(roleAdmin);
            RoleEntity roleModerator = new RoleEntity(RoleEnum.ROLE_MODERATOR);
            roleRepository.save(roleModerator);
            RoleEntity roleUser = new RoleEntity(RoleEnum.ROLE_USER);
            roleRepository.save(roleUser);

            UserEntity userAdmin = new UserEntity("UserAdmin", passwordEncoder.encode("useradmin"), roleAdmin);
            userRepository.save(userAdmin);
            UserEntity userModerator = new UserEntity("UserModerator", passwordEncoder.encode("usermoderator"),
                    roleModerator);
            userRepository.save(userModerator);
            UserEntity userUser = new UserEntity("UserUser", passwordEncoder.encode("useruser"), roleUser);
            userRepository.save(userUser);

        };
    }

}
