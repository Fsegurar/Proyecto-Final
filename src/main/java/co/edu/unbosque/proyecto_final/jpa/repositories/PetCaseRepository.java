package co.edu.unbosque.proyecto_final.jpa.repositories;



import co.edu.unbosque.proyecto_final.jpa.entities.PetCase;

import java.util.List;
import java.util.Optional;

public interface PetCaseRepository {

    Optional<PetCase> findByCaseId(Integer case_id);

    Optional<PetCase> findByType(String type);

    Optional<PetCase> findByPetId(Integer pet_id);

    List<PetCase> findAll();

    Optional<PetCase> save(PetCase petCase);




}
