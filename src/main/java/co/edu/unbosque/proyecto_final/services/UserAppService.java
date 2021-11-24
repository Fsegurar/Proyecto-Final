package edu.unbosque.Taller_5.services;

import edu.unbosque.Taller_5.jpa.entities.UserApp;
import edu.unbosque.Taller_5.jpa.repositories.UserAppRepository;
import edu.unbosque.Taller_5.jpa.repositories.UserAppRepositoryImpl;
import edu.unbosque.Taller_5.servlets.pojos.UserAppPOJO;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Stateless
public class UserAppService {

    UserAppRepository userappRepository;

    public UserAppPOJO editEmailByUsername(String username,String email){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        userappRepository = new UserAppRepositoryImpl(entityManager);
        userappRepository.editEmailByUsername(username,email);
        List<UserApp> users = userappRepository.findAll();

        entityManager.close();
        entityManagerFactory.close();
        UserAppPOJO userAppPOJO = new UserAppPOJO();
        for (UserApp user : users) {
            if (user.getUsername().equalsIgnoreCase(username)){
                userAppPOJO = new UserAppPOJO(user.getUsername(),user.getPassword(),user.getEmail(),user.getRole());
            }
        }

        return userAppPOJO;

    }
    public UserApp findByUsername(String username){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        userappRepository = new UserAppRepositoryImpl(entityManager);
       UserApp persistedUserApp = userappRepository.findByUsername(username).get();

        entityManager.close();
        entityManagerFactory.close();

        return persistedUserApp;
    }

    public List<UserAppPOJO> listUsers() {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        userappRepository = new UserAppRepositoryImpl(entityManager);
        List<UserApp> users = userappRepository.findAll();

        entityManager.close();
        entityManagerFactory.close();

        List<UserAppPOJO> usersappPOJO = new ArrayList<>();
        for (UserApp user : users) {
            usersappPOJO.add(new UserAppPOJO(
                    user.getUsername(),
                    user.getPassword(),
                    user.getEmail(),
                    user.getRole()
            ));
        }

        return usersappPOJO;

    }

    public UserAppPOJO saveUserApp(String username, String password, String email, String role) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        userappRepository = new UserAppRepositoryImpl(entityManager);

        UserApp userapp = new UserApp(username, password, email, role);
        userappRepository.save(userapp);

        entityManager.close();
        entityManagerFactory.close();

        UserAppPOJO userappPOJO = new UserAppPOJO(username, password, email, role);

        return userappPOJO;

    }
    public void deleteUserapp(String username){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        userappRepository = new UserAppRepositoryImpl(entityManager);
        userappRepository.deleteByUsername(username);

        entityManager.close();
        entityManagerFactory.close();
    }
}
