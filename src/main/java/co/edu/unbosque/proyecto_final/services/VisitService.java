package co.edu.unbosque.proyecto_final.services;

import co.edu.unbosque.proyecto_final.jpa.entities.Visit;
import co.edu.unbosque.proyecto_final.jpa.repositories.*;
import co.edu.unbosque.proyecto_final.servlets.pojos.VisitPOJO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Stateless
public class VisitService {

    UserAppRepository userAppRepository;
    VisitRepository visitRepository;

    public Visit findByVisitId(Integer visit_id){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        visitRepository = new VisitRepositoryImpl(entityManager);
        Visit persistedVisit = visitRepository.findByVisitId(visit_id).get();

        entityManager.close();
        entityManagerFactory.close();

        return persistedVisit;
    }

    public Visit findByType(String type){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        visitRepository = new VisitRepositoryImpl(entityManager);
        Visit persistedVisit = visitRepository.findByType(type).get();

        entityManager.close();
        entityManagerFactory.close();

        return persistedVisit;
    }

    public Visit findByVet(String vet_name){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        visitRepository = new VisitRepositoryImpl(entityManager);
        Visit persistedVisit = visitRepository.findByVetName(vet_name).get();

        entityManager.close();
        entityManagerFactory.close();

        return persistedVisit;
    }

    public Visit findByPetId(Integer pet_id){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        visitRepository = new VisitRepositoryImpl(entityManager);
        Visit persistedVisit = visitRepository.findByPetId(pet_id).get();

        entityManager.close();
        entityManagerFactory.close();

        return persistedVisit;
    }

    public List<VisitPOJO> listVisits(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        visitRepository = new VisitRepositoryImpl(entityManager);
        List<Visit> visits = visitRepository.findAll();

        entityManager.close();
        entityManagerFactory.close();

        List<VisitPOJO> visitPOJO = new ArrayList<>();
        for (Visit visit : visits){
            visitPOJO.add(new VisitPOJO(
                    visit.getVisit_id(),
                    visit.getCreated_at(),
                    visit.getType(),
                    visit.getDescription(),
                    visit.getVet_id().getName(),
                    visit.getPet().getPet_id()
            ));
        }
        return  visitPOJO;
    }

    public VisitPOJO saveVisit(String created_at, String type, String description, String vet_id, Integer pet_id){
        if (type.equalsIgnoreCase("esterilización")||type.equalsIgnoreCase("esterilizacion")||type.equalsIgnoreCase("implantación de microchip")||type.equalsIgnoreCase("implantacion de microchip")||type.equalsIgnoreCase("vacunación")||type.equalsIgnoreCase("desparasitación")||type.equalsIgnoreCase("desparasitacion")||type.equalsIgnoreCase("urgencia ")||type.equalsIgnoreCase("control")) {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
            EntityManager entityManager = entityManagerFactory.createEntityManager();

            visitRepository = new VisitRepositoryImpl(entityManager);

            Optional<Visit> persistedVisit = visitRepository.save(new Visit(created_at, type, description), vet_id, pet_id);

            entityManager.close();
            entityManagerFactory.close();

            VisitPOJO visitPOJO = new VisitPOJO(persistedVisit.get().getVisit_id(),created_at, type, description, persistedVisit.get().getPet(), persistedVisit.get().getVet_id());
            return visitPOJO;
        }else {
            return  null;
        }
    }

    public Integer countByType(String type){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        visitRepository = new VisitRepositoryImpl(entityManager);

        Integer petCaseNum = visitRepository.countByType(type);

        entityManager.close();
        entityManagerFactory.close();

        return petCaseNum;
    }

    public Integer countByVetName(String vetName) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        visitRepository = new VisitRepositoryImpl(entityManager);

        Integer visit_VetNameNum = visitRepository.countByVetName(vetName);

        entityManager.close();
        entityManagerFactory.close();

        return visit_VetNameNum;
    }
}
