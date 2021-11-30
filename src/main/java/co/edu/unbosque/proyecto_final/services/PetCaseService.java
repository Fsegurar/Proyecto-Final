package co.edu.unbosque.proyecto_final.services;

import co.edu.unbosque.proyecto_final.jpa.entities.Pet;
import co.edu.unbosque.proyecto_final.jpa.entities.PetCase;
import co.edu.unbosque.proyecto_final.jpa.repositories.PetCaseRepository;
import co.edu.unbosque.proyecto_final.jpa.repositories.PetCaseRepositoryImpl;
import co.edu.unbosque.proyecto_final.jpa.repositories.PetRepository;
import co.edu.unbosque.proyecto_final.jpa.repositories.PetRepositoryImpl;
import co.edu.unbosque.proyecto_final.servlets.pojos.PetCasePOJO;

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

    public Integer countByType(String type){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        petCaseRepository = new PetCaseRepositoryImpl(entityManager);

        Integer petCaseNum = petCaseRepository.countByType(type);

        entityManager.close();
        entityManagerFactory.close();

        return petCaseNum;
    }
}


