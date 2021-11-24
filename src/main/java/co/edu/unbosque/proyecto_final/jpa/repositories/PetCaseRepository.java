package edu.unbosque.Taller_5.jpa.repositories;

import edu.unbosque.Taller_5.jpa.entities.PetCase;

import java.util.List;
import java.util.Optional;

public interface PetCaseRepository {

    Optional<PetCase> findByCaseId(Integer case_id);

    Optional<PetCase> findByType(String type);

    Optional<PetCase> findByPetId(Integer pet_id);

    List<PetCase> findAll();

    Optional<PetCase> save(PetCase petCase);


}
