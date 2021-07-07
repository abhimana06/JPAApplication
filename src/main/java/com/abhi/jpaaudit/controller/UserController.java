package com.abhi.jpaaudit.controller;

import com.abhi.jpaaudit.model.Role;
import com.abhi.jpaaudit.model.User;
import com.abhi.jpaaudit.repo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@Slf4j
public class UserController {

    private static final String DEFAULT_ROLE = "USER";
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/addUser")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public String addUser(@RequestBody User user){
        if(user!=null){
            log.info("password: " + user.getPassword());
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            if(CollectionUtils.isEmpty(user.getRoles())){
                Role role = new Role(2,DEFAULT_ROLE );
                Set<Role> roleSet= new HashSet<>();
                roleSet.add(role);
                user.setRoles(roleSet);
            }

            userRepository.save(user);
            return "User saved successfully";
        }else{
            throw new RuntimeException("User Request is Null");
        }
    }
}
