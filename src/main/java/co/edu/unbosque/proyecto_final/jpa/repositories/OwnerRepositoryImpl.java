package edu.unbosque.Taller_5.jpa.repositories;

import edu.unbosque.Taller_5.jpa.entities.Owner;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class OwnerRepositoryImpl implements OwnerRepository{

    private EntityManager entityManager;

    public  OwnerRepositoryImpl (EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Owner> findByOwnerId(Integer owner_id) {
        Owner visit = entityManager.createQuery("SELECT b FROM Owner b WHERE b.person_id = :owner_id", Owner.class)
                .setParameter("owner_id", owner_id)
                .getSingleResult();
        return visit != null ? Optional.of(visit) : Optional.empty();
    }
// createquery
    @Override
    public Optional<Owner> findByName(String name) {
        Owner visit = entityManager.createQuery("SELECT b FROM Owner b WHERE b.name = :name", Owner.class)
                .setParameter("name", name)
                .getSingleResult();
        return visit != null ? Optional.of(visit) : Optional.empty();
    }
    @Override
    public Optional<Owner> findByAddress(String address) {
        Owner visit = entityManager.createQuery("SELECT b FROM Owner b WHERE b.address = :address", Owner.class)
                .setParameter("address", address)
                .getSingleResult();
        return visit != null ? Optional.of(visit) : Optional.empty();
    }

    @Override
    public Optional<Owner> findByNeighborhood(String neighborhood) {
        Owner visit = entityManager.createQuery("SELECT b FROM Owner b WHERE b.neighborhood = :neighborhood", Owner.class)
                .setParameter("neighborhood", neighborhood)
                .getSingleResult();
        return visit != null ? Optional.of(visit) : Optional.empty();
    }

    @Override
    public List<Owner> findAll() {return entityManager.createQuery("from Owner ").getResultList();
}

    @Override
    public Optional<Owner> save(Owner owner) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(owner);
            entityManager.getTransaction().commit();
            return Optional.of(owner);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Owner> editNameByUsername(String username, String name) {
        try {
            entityManager.getTransaction().begin();
            Owner owner = entityManager.find(Owner.class, username);
            owner.setName(name);

            entityManager.getTransaction().commit();
            return Optional.of(owner);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Owner> editAddressByUsername(String username, String address) {
        try {
            entityManager.getTransaction().begin();
            Owner owner = entityManager.find(Owner.class, username);
            owner.setAddress(address);

            entityManager.getTransaction().commit();
            return Optional.of(owner);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Owner> editNeighborhoodByUsername(String username, String neighborhood) {
        try {
            entityManager.getTransaction().begin();
            Owner owner = entityManager.find(Owner.class, username);
            owner.setNeighborhood(neighborhood);

            entityManager.getTransaction().commit();
            return Optional.of(owner);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Owner> findByUsername(String username) {
        Owner owner = entityManager.find(Owner.class,username);
        return owner!=null ? Optional.of(owner) : Optional.empty();
    }
}
