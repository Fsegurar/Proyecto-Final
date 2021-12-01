package co.edu.unbosque.proyecto_final.jpa.repositories;



import co.edu.unbosque.proyecto_final.jpa.entities.Visit;

import java.util.List;
import java.util.Optional;

public interface VisitRepository {

    Optional<Visit> findByVisitId(Integer visit_id);

    Optional<Visit> findByType(String type);

    Optional<Visit> findByVetName(String vet_name);

    Optional<Visit> findByPetId(Integer pet_id);

    List<Visit> findAll();

    Optional<Visit> save (Visit visit, String vet_id, Integer pet_id);

    Integer countByType(String type);
}
