package edu.unbosque.Taller_5.jpa.repositories;

import edu.unbosque.Taller_5.jpa.entities.Owner;

import java.util.List;
import java.util.Optional;

public interface OwnerRepository {

    Optional<Owner> findByOwnerId(Integer owner_id);

    Optional<Owner> findByName(String name);

    Optional<Owner> findByAddress(String address);

    Optional<Owner> findByNeighborhood(String neighborhood);

    List<Owner> findAll();

    Optional<Owner> save(Owner owner);

    Optional<Owner> editNameByUsername(String username,String name);

    Optional<Owner> editAddressByUsername(String username,String address);

    Optional<Owner> editNeighborhoodByUsername(String username,String neighborhood);

    Optional<Owner> findByUsername(String username);
}
