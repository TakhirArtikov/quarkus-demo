package org.acme.security.service;

import io.quarkus.elytron.security.common.BcryptUtil;
import org.acme.security.entity.User;
import org.acme.security.repository.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository repository;

    public  void addUser(User newUser) {

        User user=new User();

        user.setUsername(newUser.getUsername());
        user.setPassword(BcryptUtil.bcryptHash(newUser.getPassword()));
        user.setRole(newUser.getRole());
        repository.persist(user);
    }




}
