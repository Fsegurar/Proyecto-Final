package co.edu.unbosque.proyecto_final.jpa.repositories;



import co.edu.unbosque.proyecto_final.jpa.entities.Pet;
import co.edu.unbosque.proyecto_final.jpa.entities.Vet;
import co.edu.unbosque.proyecto_final.jpa.entities.Visit;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class VisitRepositoryImpl implements VisitRepository{

    private EntityManager entityManager;

    public VisitRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Visit> findByVisitId(Integer visit_id) {
        Visit visit = entityManager.find(Visit.class,visit_id);
        return visit!=null ? Optional.of(visit) : Optional.empty();
    }

    @Override
    public Optional<Visit> findByType(String type) {
        Visit visit = entityManager.createQuery("SELECT b FROM Visit b WHERE b.type = :type", Visit.class)
                .setParameter("type", type)
                .getSingleResult();
        return visit != null ? Optional.of(visit) : Optional.empty();
    }

    @Override
    public Optional<Visit> findByVet(String vet_name) {
        Visit visit = entityManager.createQuery("SELECT b FROM Visit b WHERE b.vet = :vet_name", Visit.class)
                .setParameter("vet_name", vet_name)
                .getSingleResult();
        return visit != null ? Optional.of(visit) : Optional.empty();
    }

    @Override
    public Optional<Visit> findByPetId(Integer pet_id) {
        Visit visit = entityManager.createQuery("SELECT b FROM Visit b WHERE b.pet = :pet_id", Visit.class)
                .setParameter("pet_id", pet_id)
                .getSingleResult();
        return visit != null ? Optional.of(visit) : Optional.empty();
    }

    @Override
    public List<Visit> findAll() {return entityManager.createQuery("from UserApp ").getResultList();
    }


    @Override
    public Optional<Visit> save(Visit visit, String vet_id, Integer pet_id) {
        try {
            entityManager.getTransaction().begin();

            VetRepository vetRepository = new VetRepositoryImpl(entityManager);

            Vet vet = vetRepository.findByName(vet_id).get();
            visit.setVet(vet);

            Pet pet = entityManager.find(Pet.class, pet_id);
            visit.setPet(pet);

            entityManager.persist(visit);
            entityManager.getTransaction().commit();
            return Optional.of(visit);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

}
