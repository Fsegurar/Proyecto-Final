package edu.unbosque.Taller_5.services;


import edu.unbosque.Taller_5.jpa.entities.Official;
import edu.unbosque.Taller_5.jpa.entities.UserApp;
import edu.unbosque.Taller_5.jpa.repositories.OfficialRepository;
import edu.unbosque.Taller_5.jpa.repositories.OfficialRepositoryImpl;
import edu.unbosque.Taller_5.jpa.repositories.UserAppRepository;
import edu.unbosque.Taller_5.jpa.repositories.UserAppRepositoryImpl;
import edu.unbosque.Taller_5.servlets.pojos.OfficialPOJO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Stateless
public class OfficialService {

    OfficialRepository officialRepository;
    UserAppRepository userRepository;

    public OfficialPOJO editNameByUsername(String username,String name){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        officialRepository = new OfficialRepositoryImpl(entityManager);
        officialRepository.editNameByUsername(username,name);

        entityManager.close();
        entityManagerFactory.close();

        Official official = findByUsername(username);
        OfficialPOJO officialPOJO = new OfficialPOJO(official.getUsername().getUsername(), official.getName());

        return officialPOJO;

    }

    public Official findByUsername(String username){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        officialRepository = new OfficialRepositoryImpl(entityManager);
        Official persistedOfficial = officialRepository.findByUsername(username).get();

        entityManager.close();
        entityManagerFactory.close();

        return persistedOfficial;
    }

    public Official findByname(String name){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        officialRepository = new OfficialRepositoryImpl(entityManager);
        Official persistedOfficial = officialRepository.findByName(name).get();

        entityManager.close();
        entityManagerFactory.close();

        return persistedOfficial;
    }

    public List<OfficialPOJO> listOfficials() {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        officialRepository = new OfficialRepositoryImpl(entityManager);
        List<Official> officials = officialRepository.findAll();

        entityManager.close();
        entityManagerFactory.close();

        List<OfficialPOJO> officialPOJOS = new ArrayList<>();
        for (Official official : officials) {
            officialPOJOS.add(new OfficialPOJO(
                    official.getUsername().getUsername(),
                    official.getName()
            ));
        }

        return officialPOJOS;

    }

    public OfficialPOJO saveOfficial(String username, String name) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        userRepository = new UserAppRepositoryImpl(entityManager);
        officialRepository = new OfficialRepositoryImpl(entityManager);

        Optional<UserApp> user = userRepository.findByUsername(username);
        user.ifPresent(u ->{
            Official official = new Official(name);
            official.setUsername(user.get());
            u.setOfficial(official);
            userRepository.save(u);
        });

        entityManager.close();
        entityManagerFactory.close();

        OfficialPOJO officialpojo = new OfficialPOJO(name);

        return officialpojo;

    }
}
