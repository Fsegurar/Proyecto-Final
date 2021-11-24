package edu.unbosque.Taller_5.jpa.repositories;

import edu.unbosque.Taller_5.jpa.entities.UserApp;
import edu.unbosque.Taller_5.servlets.pojos.UserAppPOJO;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class UserAppRepositoryImpl implements UserAppRepository {

    private EntityManager entityManager;

    public UserAppRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<UserApp> findByRole(String role) {
        UserApp userApp = entityManager.createQuery("SELECT b FROM UserApp b WHERE b.role = :role", UserApp.class)
                .setParameter("role", role)
                .getSingleResult();
        return userApp != null ? Optional.of(userApp) : Optional.empty();
    }
    @Override
    public Optional<UserApp> findByUsername(String username) {
        UserApp userApp = entityManager.find(UserApp.class,username);
        return userApp!=null ? Optional.of(userApp) : Optional.empty();
    }

    @Override
    public List findAll() {
        return entityManager.createQuery("from UserApp ").getResultList();
    }

    @Override
    public Optional<UserApp> save(UserApp userapp) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(userapp);
            entityManager.getTransaction().commit();
            return Optional.of(userapp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void deleteByUsername(String Username) {
        UserApp userApp = entityManager.find(UserApp.class,Username);
        if(userApp!=null){
            try{
                entityManager.getTransaction().begin();
                entityManager.remove(userApp.getOfficial());
                entityManager.remove(userApp.getOwner());
                entityManager.remove(userApp.getVet());
                entityManager.remove(userApp);
                entityManager.getTransaction().commit();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }



    public Optional<UserApp> editEmailByUsername(String username, String email) {
        try {
            entityManager.getTransaction().begin();
            UserApp userapp = entityManager.find(UserApp.class, username);
            userapp.setEmail(email);

            entityManager.getTransaction().commit();
            return Optional.of(userapp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

}
