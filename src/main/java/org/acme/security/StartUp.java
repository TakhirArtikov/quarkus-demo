package org.acme.security;

import io.quarkus.runtime.StartupEvent;
import org.acme.security.entity.ERole;
import org.acme.security.entity.User;
import org.acme.security.repository.UserRepository;
import org.acme.security.service.UserService;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

@Singleton
public class StartUp {

    @Inject
    UserService service;

    @Transactional
    public void loadUsers(@Observes StartupEvent evt) {
        // reset and load all test users

        User user=new User();
        user.setUsername("Jamshid");
        user.setPassword("1234");
        user.setRole("user");
        service.addUser(user);
    }
}
