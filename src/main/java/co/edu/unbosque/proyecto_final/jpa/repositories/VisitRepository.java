package edu.unbosque.Taller_5.jpa.repositories;

import edu.unbosque.Taller_5.jpa.entities.Vet;
import edu.unbosque.Taller_5.jpa.entities.Visit;

import java.util.List;
import java.util.Optional;

public interface VisitRepository {

    Optional<Visit> findByVisitId(Integer visit_id);

    Optional<Visit> findByType(String type);

    Optional<Visit> findByVet(String vet_name);

    Optional<Visit> findByPetId(Integer pet_id);

    List<Visit> findAll();

    Optional<Visit> save (Visit visit);

}
