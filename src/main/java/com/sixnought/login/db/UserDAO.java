package com.sixnought.login.db;

import com.sixnought.login.core.User;
import io.dropwizard.hibernate.AbstractDAO;
import java.util.List;
import java.util.Optional;
import org.hibernate.SessionFactory;

public class UserDAO extends AbstractDAO<User> {

    public UserDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
   
    public List<User> findAll() {
        return list(namedQuery("User.findAll"));
    }

   public User save(User user) {
       return persist(user);
    }

    public Optional<User> findByUsernameAndPassword(
            String username,
            String password
    ) {
        return Optional.ofNullable(
                uniqueResult(
                        namedQuery("User.findByUsernameAndPassword")
                        .setParameter("username", username)
                        .setParameter("password", password)
                ));
    }

    public Optional<User> findById(Integer id) {
        return Optional.ofNullable(get(id));
    }

    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(
                uniqueResult(
                        namedQuery("User.findByUsername")
                        .setParameter("username", username)
                ));
    }

   public Optional<User> findByEmail(String email) {
       return Optional.ofNullable(
          uniqueResult(namedQuery("User.findByEmail").setParameter("email", email)));            
    }
}
