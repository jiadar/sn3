package com.sixnought.login;

import com.sixnought.login.auth.DBAuthenticator;
// import com.sixnought.login.core.Concubine;
// import com.sixnought.login.db.ConcubineDAO;
// import com.sixnought.login.resources.ConcubinesResource;
import com.sixnought.login.core.User;
import com.sixnought.login.db.UserDAO;
import com.sixnought.login.resources.UsersResource;

import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.Authorizer;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.UnitOfWorkAwareProxyFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.hibernate.SessionFactory;

public class LoginApplication extends Application<LoginConfiguration> {

    private final HibernateBundle<LoginConfiguration> hibernateBundle
            = new HibernateBundle<LoginConfiguration>(
               User.class) {
       // ,
       //              Concubine.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(
                LoginConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    public static void main(final String[] args) throws Exception {
        new LoginApplication().run(args);
    }

    @Override
    public String getName() {
        return "Login";
    }

    @Override
    public void initialize(final Bootstrap<LoginConfiguration> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
        bootstrap.addBundle(
                new MigrationsBundle<LoginConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(
                    LoginConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
    }

    @Override
    public void run(final LoginConfiguration configuration, final Environment environment) {

        final UserDAO userDAO = new UserDAO(hibernateBundle.getSessionFactory());
        // final ConcubineDAO bookmarkDAO = new ConcubineDAO(hibernateBundle.getSessionFactory());

        final DBAuthenticator authenticator
                = new UnitOfWorkAwareProxyFactory(hibernateBundle)
                .create(DBAuthenticator.class,
                        new Class<?>[]{UserDAO.class, SessionFactory.class},
                        new Object[]{userDAO,
                            hibernateBundle.getSessionFactory()});

        environment.jersey().register(new AuthDynamicFeature(
                new BasicCredentialAuthFilter.Builder<User>()
                .setAuthenticator(authenticator)
                .setAuthorizer(new Authorizer<User>() {
                    @Override
                    public boolean authorize(User principal, String role) {
                        return true;
                    }
                })
                .setRealm("SECURITY REALM")
                .buildAuthFilter()));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(
                new AuthValueFactoryProvider.Binder<>(User.class));

        environment.jersey().register(new UsersResource(userDAO));
        // environment.jersey().register(new ConcubinesResource(concubineDAO));
    }

}
