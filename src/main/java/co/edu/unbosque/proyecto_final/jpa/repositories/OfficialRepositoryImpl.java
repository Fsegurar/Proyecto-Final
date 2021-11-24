package edu.unbosque.Taller_5.jpa.repositories;

import edu.unbosque.Taller_5.jpa.entities.Official;
import edu.unbosque.Taller_5.jpa.entities.UserApp;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class OfficialRepositoryImpl implements OfficialRepository{

    private EntityManager entityManager;

    public OfficialRepositoryImpl (EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Official> findByUsername(String username) {
        Official official =entityManager.find(Official.class,username);
        return official!=null ? Optional.of(official) : Optional.empty();
    }

    @Override
    public Optional<Official> findByName(String name) {
        Official visit = entityManager.createQuery("SELECT b FROM Official b WHERE b.name = :name", Official.class)
                .setParameter("name", name)
                .getSingleResult();
        return visit != null ? Optional.of(visit) : Optional.empty();
    }

    @Override
    public List<Official> findAll() {return entityManager.createQuery("from Official ").getResultList();}

    @Override
    public Optional<Official> save(Official official) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(official);
            entityManager.getTransaction().commit();
            return Optional.of(official);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Official> editNameByUsername(String username, String name) {
        try {
            entityManager.getTransaction().begin();
            Official official = entityManager.find(Official.class, username);
            official.setName(name);

            entityManager.getTransaction().commit();
            return Optional.of(official);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

}
