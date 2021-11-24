package edu.unbosque.Taller_5.jpa.repositories;

import edu.unbosque.Taller_5.jpa.entities.Owner;
import edu.unbosque.Taller_5.jpa.entities.Pet;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class PetRepositoryImpl implements PetRepository{

    private EntityManager entityManager;

    public PetRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Pet> findByPetId(Integer pet_id) {
        Pet pet = entityManager.createQuery("SELECT b FROM Pet b WHERE b.pet_id = :pet_id", Pet.class)
                .setParameter("pet_id", pet_id)
                .getSingleResult();
        return pet!=null ? Optional.of(pet) : Optional.empty();
    }

    @Override
    public Optional<Pet> findByName(String name) {
        Pet pet = entityManager.createQuery("SELECT b FROM Pet b WHERE b.name = :name", Pet.class)
                .setParameter("name", name)
                .getSingleResult();
        return pet != null ? Optional.of(pet) : Optional.empty();
    }

    @Override
    public Optional<Pet> findBySpecies(String species) {
        Pet pet = entityManager.createQuery("SELECT b FROM Pet b WHERE b.species = :species", Pet.class)
                .setParameter("species", species)
                .getSingleResult();
        return pet != null ? Optional.of(pet) : Optional.empty();
    }

    @Override
    public Optional<Pet> findByRace(String race) {
        Pet pet = entityManager.createQuery("SELECT b FROM Pet b WHERE b.race = :race", Pet.class)
                .setParameter("race", race)
                .getSingleResult();
        return pet != null ? Optional.of(pet) : Optional.empty();
    }

    @Override
    public Optional<Pet> findBySize(String size) {
        Pet pet = entityManager.createQuery("SELECT b FROM Pet b WHERE b.size = :size", Pet.class)
                .setParameter("size", size)
                .getSingleResult();
        return pet != null ? Optional.of(pet) : Optional.empty();
    }

    @Override
    public Optional<Pet> findBySex(String sex) {
        Pet pet = entityManager.createQuery("SELECT b FROM Pet b WHERE b.sex = :sex", Pet.class)
                .setParameter("sex", sex)
                .getSingleResult();
        return pet != null ? Optional.of(pet) : Optional.empty();
    }

    @Override
    public Optional<Pet> findByOwner(Integer owner_id) {
        Pet pet = entityManager.createQuery("SELECT b FROM Pet b WHERE b.owner = :owner_id", Pet.class)
                .setParameter("owner_id", owner_id)
                .getSingleResult();
        return pet != null ? Optional.of(pet) : Optional.empty();
    }

    @Override
    public List<Pet> findAll() {return entityManager.createQuery("from Pet ").getResultList();
    }


    @Override
    public Optional<Pet> save(Pet pet) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(pet);
            entityManager.getTransaction().commit();
            return Optional.of(pet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Pet> editNameByPetId(Integer pet_id, String name) {
        try {
            entityManager.getTransaction().begin();
            Pet pet = findByPetId(pet_id).get();
            pet.setName(name);

            entityManager.getTransaction().commit();
            return Optional.of(pet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Pet> editSpecieByPetId(Integer pet_id, String specie) {
        try {
            entityManager.getTransaction().begin();
            Pet pet = entityManager.find(Pet.class, pet_id);
            pet.setSpecies(specie);

            entityManager.getTransaction().commit();
            return Optional.of(pet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Pet> editRaceByPetId(Integer pet_id, String race) {
        try {
            entityManager.getTransaction().begin();
            Pet pet = entityManager.find(Pet.class, pet_id);
            pet.setRace(race);

            entityManager.getTransaction().commit();
            return Optional.of(pet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Pet> editSizeByPetId(Integer pet_id, String size) {
        try {
            entityManager.getTransaction().begin();
            Pet pet = entityManager.find(Pet.class, pet_id);
            pet.setSize(size);

            entityManager.getTransaction().commit();
            return Optional.of(pet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Pet> editSexByPetId(Integer pet_id, String sex) {
        try {
            entityManager.getTransaction().begin();
            Pet pet = entityManager.find(Pet.class, pet_id);
            pet.setSex(sex);

            entityManager.getTransaction().commit();
            return Optional.of(pet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Pet> editPictureByPetId(Integer pet_id, String picture) {
        try {
            entityManager.getTransaction().begin();
            Pet pet = entityManager.find(Pet.class, pet_id);
            pet.setPicture(picture);

            entityManager.getTransaction().commit();
            return Optional.of(pet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Pet> editMicrochipByPetId(Integer pet_id, String microchip) {
       try {
            entityManager.getTransaction().begin();
            Pet pet = entityManager.find(Pet.class, pet_id);
            pet.setMicrochip(microchip);
            entityManager.getTransaction().commit();
            return Optional.of(pet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


}
