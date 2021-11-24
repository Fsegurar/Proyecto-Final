package edu.unbosque.Taller_5.jpa.repositories;

import edu.unbosque.Taller_5.jpa.entities.Vet;

import java.util.List;
import java.util.Optional;

public interface VetRepository {

    Optional<Vet> findByUsername(String username);

    Optional<Vet> findByName(String name);

    Optional<Vet> findByAddress(String Address);

    Optional<Vet> findByNeighborhood(String neighborhood);

    List<Vet> findAll();

    Optional<Vet> save(Vet vet);

    Optional<Vet> editNameByUsername(String username,String name);

    Optional<Vet> editAddressByUsername(String username,String address);

    Optional<Vet> editNeighborhoodByUsername(String username,String neighborhood);
}
