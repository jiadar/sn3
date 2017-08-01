package com.sixnought.login.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
//import com.sixnought.login.core.Concubine;
import com.sixnought.login.core.User;
import com.sixnought.login.db.UserDAO;
//import com.sixnought.login.db.ConcubineDAO;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.IntParam;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.commons.beanutils.BeanUtils;
import org.jasypt.util.password.PasswordEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.jasypt.util.password.PasswordEncryptor;


@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class UsersResource {

   public static final String WRONG_BODY_DATA_FORMAT = "Wrong body data format";
   private static final Logger log = LoggerFactory.getLogger(UsersResource.class);
   private final UserDAO userDAO;

   public UsersResource(final UserDAO userDAO) {
      this.userDAO = userDAO;
   }
   
    @GET
    @UnitOfWork
    public Optional<User> getUser(@Auth User user) {
       return userDAO.findByEmail(user.getEmail());
    }

   @POST
   @UnitOfWork
   public User addUser(User user) {
      // log.info("IN ADD USER...");
      final PasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
      User result = new User();
      result.setUsername(user.getUsername());
      String encrypted_password=passwordEncryptor.encryptPassword(user.getPassword());
      // log.info("adduser encrypted password: " + encrypted_password);
      result.setPassword(encrypted_password);
      result.setEmail(user.getEmail());
      return userDAO.save(result);
   }


}


