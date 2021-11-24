package edu.unbosque.Taller_5.services;

import edu.unbosque.Taller_5.jpa.entities.Official;
import edu.unbosque.Taller_5.jpa.entities.Owner;
import edu.unbosque.Taller_5.jpa.entities.UserApp;
import edu.unbosque.Taller_5.jpa.repositories.*;
import edu.unbosque.Taller_5.servlets.pojos.OwnerPOJO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Stateless
public class OwnerService {

    OwnerRepository ownerRepository;
    UserAppRepository userRepository;

    public OwnerPOJO editNameByUsername(String username,String name){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        ownerRepository = new OwnerRepositoryImpl(entityManager);
        ownerRepository.editNameByUsername(username,name);

        entityManager.close();
        entityManagerFactory.close();

        Owner owner = findByUsername(username);
        OwnerPOJO ownerPOJO = new OwnerPOJO(owner.getUsername().getUsername(),owner.getPerson_id(),owner.getName(),owner.getAddress(),owner.getNeighborhood());

        return ownerPOJO;
    }
    public OwnerPOJO editAddressByUsername(String username,String address){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        ownerRepository = new OwnerRepositoryImpl(entityManager);
        ownerRepository.editAddressByUsername(username,address);

        entityManager.close();
        entityManagerFactory.close();

        Owner owner = findByUsername(username);
        OwnerPOJO ownerPOJO = new OwnerPOJO(owner.getUsername().getUsername(),owner.getPerson_id(),owner.getName(),owner.getAddress(),owner.getNeighborhood());

        return ownerPOJO;
    }
    public OwnerPOJO editNeighborhoodByUsername(String username,String neighborhood){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        ownerRepository = new OwnerRepositoryImpl(entityManager);
        ownerRepository.editNeighborhoodByUsername(username,neighborhood);

        entityManager.close();
        entityManagerFactory.close();

        Owner owner = findByUsername(username);
        OwnerPOJO ownerPOJO = new OwnerPOJO(owner.getUsername().getUsername(),owner.getPerson_id(),owner.getName(),owner.getAddress(),owner.getNeighborhood());

        return ownerPOJO;

    }

    public Owner findByUsername(String username){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        ownerRepository = new OwnerRepositoryImpl(entityManager);
        Owner persistedOwner = ownerRepository.findByUsername(username).get();

        entityManager.close();
        entityManagerFactory.close();

        return persistedOwner;
    }

    public Owner findByOwnerId(Integer owner_id){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        ownerRepository = new OwnerRepositoryImpl(entityManager);
        Owner persistedOwner = ownerRepository.findByOwnerId(owner_id).get();

        entityManager.close();
        entityManagerFactory.close();

        return persistedOwner;
    }

    public Owner findByName(String name){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        ownerRepository = new OwnerRepositoryImpl(entityManager);
        Owner persistedOwner = ownerRepository.findByName(name).get();

        entityManager.close();
        entityManagerFactory.close();

        return persistedOwner;
    }

    public Owner findByAddress(String address){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        ownerRepository = new OwnerRepositoryImpl(entityManager);
        Owner persistedOwner = ownerRepository.findByAddress(address).get();

        entityManager.close();
        entityManagerFactory.close();

        return persistedOwner;
    }

    public Owner findByNeighborhood(String neighborhood){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        ownerRepository = new OwnerRepositoryImpl(entityManager);
        Owner persistedOwner = ownerRepository.findByNeighborhood(neighborhood).get();

        entityManager.close();
        entityManagerFactory.close();

        return persistedOwner;
    }

    public List<OwnerPOJO> listOwners(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        ownerRepository = new OwnerRepositoryImpl(entityManager);
        List<Owner> owners = ownerRepository.findAll();

        entityManager.close();
        entityManagerFactory.close();

        List<OwnerPOJO> ownerPOJO = new ArrayList<>();
        for (Owner owner : owners){
            ownerPOJO.add(new OwnerPOJO(
                    owner.getUsername().getUsername(),
                    owner.getPerson_id(),
                    owner.getName(),
                    owner.getAddress(),
                    owner.getNeighborhood()
            ));
        }
        return ownerPOJO;
    }

    public OwnerPOJO saveOwner(String username, String name, String address, String neighborhood){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        userRepository = new UserAppRepositoryImpl(entityManager);
        ownerRepository = new OwnerRepositoryImpl(entityManager);

        Optional<UserApp> user = userRepository.findByUsername(username);
        user.ifPresent(u ->{
            Owner owner = new Owner(name,address,neighborhood);
            owner.setUsername(user.get());
            owner.setPerson_id(listOwners().size()+1);
            owner.setPets(null);
            u.setOwner(owner);
            userRepository.save(u);
        });

        entityManager.close();
        entityManagerFactory.close();

        OwnerPOJO ownerPOJO = new OwnerPOJO(name,address,neighborhood);
        return ownerPOJO;
    }
}
