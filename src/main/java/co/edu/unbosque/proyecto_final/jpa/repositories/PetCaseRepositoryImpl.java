package edu.unbosque.Taller_5.jpa.repositories;

import edu.unbosque.Taller_5.jpa.entities.PetCase;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class PetCaseRepositoryImpl implements PetCaseRepository{

    private EntityManager entityManager;

    public PetCaseRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<PetCase> findByCaseId(Integer case_id) {
        PetCase petCase = entityManager.find(PetCase.class,case_id);
        return petCase!=null ? Optional.of(petCase) : Optional.empty();
    }

    @Override
    public Optional<PetCase> findByType(String type) {
        PetCase petCase = entityManager.createQuery("SELECT b FROM PetCase b WHERE b.type = :type", PetCase.class)
                .setParameter("type", type)
                .getSingleResult();
        return petCase != null ? Optional.of(petCase) : Optional.empty();
    }

    @Override
    public Optional<PetCase> findByPetId(Integer pet_id) {
        PetCase petCase = entityManager.createQuery("SELECT b FROM PetCase b WHERE b.pet = :pet_id", PetCase.class)
                .setParameter("pet_id", pet_id)
                .getSingleResult();
        return petCase != null ? Optional.of(petCase) : Optional.empty();
    }

    @Override
    public List<PetCase> findAll() {return entityManager.createQuery("from UserApp ").getResultList();
    }


    @Override
    public Optional<PetCase> save(PetCase petCase) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(petCase);
            //entityManager.clear();
            entityManager.getTransaction().commit();
            entityManager.close();
            return Optional.of(petCase);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
