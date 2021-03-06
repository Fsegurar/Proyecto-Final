package co.edu.unbosque.proyecto_final.jpa.repositories;



import co.edu.unbosque.proyecto_final.jpa.entities.Official;
import java.util.List;
import java.util.Optional;

public interface OfficialRepository {

    Optional<Official> findByUsername(String username);

    Optional<Official> findByName(String name);

    List<Official> findAll();

    Optional<Official> save(Official official);

    Optional<Official> editNameByUsername(String username,String name);
}
