package com.sixnought.login.auth;

import com.sixnought.login.core.User;
import com.sixnought.login.db.UserDAO;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.hibernate.UnitOfWork;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.context.internal.ManagedSessionContext;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.jasypt.util.password.PasswordEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
   
public class DBAuthenticator implements Authenticator<BasicCredentials, User> {
      
   private final UserDAO userDAO;
   private final SessionFactory sessionFactory;
   private final PasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
   private static final Logger log = LoggerFactory.getLogger(DBAuthenticator.class);
 
   public DBAuthenticator(final UserDAO userDAO, final SessionFactory sessionFactory) {
      this.userDAO = userDAO;
      this.sessionFactory = sessionFactory;
   }

   @UnitOfWork
   @Override
   public final Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
      Session session = sessionFactory.openSession();
      Optional<User> result;
      
      try {
         ManagedSessionContext.bind(session);
         result = userDAO.findByUsername(credentials.getUsername());
         if (result.isPresent()) {
            
            if (passwordEncryptor.checkPassword(credentials.getPassword(), result.get().getPassword())) {
               return result;
            }
            else {
               return Optional.empty();
            }
         }
         else {
            return Optional.empty(); 
         }
      }
      catch (Exception e) {
         throw new AuthenticationException(e);
      }
      finally {
         ManagedSessionContext.unbind(sessionFactory);
         session.close();
      }
      //       ManagedSessionContext.bind(session);

      //       result = userDAO.findByUsername(credentials.getUsername());

      //       if (!result.isPresent()) {
      //          // new user	
      //          userId = UUID.randomUUID();
      //          result.setUserId(userId);
               
      //          UserCredentials userCredentials = new UserCredentials();
      //          userCredentials.setFacebookIdentifier(null);
      //          userCredentials.setUserEmail(request.getEmail());
      //          userCredentials.setUserId(userId);
      //          userCredentials.setUserName(request.getUserName());
      //          userCredentials.setEncryptedPassword(passwordEncryptor.encryptPassword(request.getPassword()));
      //          credentialDAO.insertUser(userCredentials);
      //          AccessToken token = new AccessToken();
      //          token.setTokenId(userToken);
      //          token.setUserId(userId);
      //          token.setLastAccess(new DateTime().now());
      //          tokenDAO.insert(token);
      //          return Result.ok(result, MediaType.APPLICATION_JSON).build();
      //       }
      //       else {
      //          if (passwordEncryptor.checkPassword(
      //                 credentials.getPassword(),
      //                 result.get().getPassword())) {
      //             return result;
      //          } else {
      //             return Optional.empty();
      //          }
      //       }

      //    } catch (Exception e) {
      //       throw new AuthenticationException(e);
      //    } finally {
      //       ManagedSessionContext.unbind(sessionFactory);
      //       session.close();
      //    }

      // }


   }
 }
