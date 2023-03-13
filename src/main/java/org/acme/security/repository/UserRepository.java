package org.acme.security.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.acme.security.entity.User;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
}
