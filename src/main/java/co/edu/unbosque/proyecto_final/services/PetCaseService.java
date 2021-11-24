package edu.unbosque.Taller_5.services;

import edu.unbosque.Taller_5.jpa.entities.Pet;
import edu.unbosque.Taller_5.jpa.entities.PetCase;
import edu.unbosque.Taller_5.jpa.repositories.PetCaseRepository;
import edu.unbosque.Taller_5.jpa.repositories.PetCaseRepositoryImpl;
import edu.unbosque.Taller_5.jpa.repositories.PetRepository;
import edu.unbosque.Taller_5.jpa.repositories.PetRepositoryImpl;
import edu.unbosque.Taller_5.servlets.pojos.PetCasePOJO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Stateless
public class PetCaseService {

    PetRepository petRepository;
    PetCaseRepository petCaseRepository;

    public PetCase findByCaseId(Integer case_id){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        petCaseRepository = new PetCaseRepositoryImpl(entityManager);
        PetCase persistedPetCase = petCaseRepository.findByCaseId(case_id).get();

        entityManager.close();
        entityManagerFactory.close();

        return persistedPetCase;
    }

    public PetCase findByType(String type){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        petCaseRepository = new PetCaseRepositoryImpl(entityManager);
        PetCase persistedPetCase = petCaseRepository.findByType(type).get();

        entityManager.close();
        entityManagerFactory.close();

        return persistedPetCase;
    }

    public PetCase findByPetId(Integer pet_id){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        petCaseRepository = new PetCaseRepositoryImpl(entityManager);
        PetCase persistedPetCase = petCaseRepository.findByPetId(pet_id).get();

        entityManager.close();
        entityManagerFactory.close();

        return persistedPetCase;
    }

    public List<PetCasePOJO> listPetCases(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        petCaseRepository = new PetCaseRepositoryImpl(entityManager);
        List<PetCase> cases = petCaseRepository.findAll();

        entityManager.close();
        entityManagerFactory.close();

        List<PetCasePOJO> petCasePOJO = new ArrayList<>();
        for (PetCase petCase : cases){
            petCasePOJO.add(new PetCasePOJO(
                    petCase.getCase_id(),
                    petCase.getCreated_at(),
                    petCase.getType(),
                    petCase.getDescription(),
                    petCase.getPet().getPet_id()
            ));
        }
        return petCasePOJO;
    }
    public PetCasePOJO savePetCase(String created_at, String type, String description, Integer pet_id){
        if (type.equalsIgnoreCase("perdida") || type.equalsIgnoreCase("pérdida") || type.equalsIgnoreCase("robo ") || type.equalsIgnoreCase("fallecimiento")) {


            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
            EntityManager entityManager = entityManagerFactory.createEntityManager();

            petCaseRepository = new PetCaseRepositoryImpl(entityManager);
            petRepository = new PetRepositoryImpl(entityManager);


            Optional<Pet> pet = petRepository.findByPetId(pet_id);
            pet.ifPresent(p -> {
                PetCase petCase = new PetCase(created_at, type, description);
                petCase.setPet(p);
                p.addPetCase(petCase);
                petCaseRepository.save(petCase);
            });

            entityManager.close();
            entityManagerFactory.close();

            PetCasePOJO petCasePOJO = new PetCasePOJO(created_at, type, description);
            return petCasePOJO;
        }else {
            return null;
        }
    }
}


