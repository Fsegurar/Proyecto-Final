package edu.unbosque.Taller_5.services;

import edu.unbosque.Taller_5.jpa.entities.*;
import edu.unbosque.Taller_5.jpa.repositories.*;
import edu.unbosque.Taller_5.servlets.pojos.OfficialPOJO;
import edu.unbosque.Taller_5.servlets.pojos.OwnerPOJO;
import edu.unbosque.Taller_5.servlets.pojos.PetPOJO;
import edu.unbosque.Taller_5.servlets.pojos.VetPOJO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Stateless
public class VetService {

    UserAppRepository userRepository;
    VetRepository vetRepository;

    public VetPOJO editNameByUsername(String username,String name){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        vetRepository = new VetRepositoryImpl(entityManager);
        vetRepository.editNameByUsername(username,name);

        entityManager.close();
        entityManagerFactory.close();

        Vet vet = findByUsername(username);
        VetPOJO vetPOJO = new VetPOJO(vet.getUsername().getUsername(),vet.getName(),vet.getAddress(),vet.getNeighborhood());

        return vetPOJO;
    }

    public VetPOJO editAddressByUsername(String username,String address){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        vetRepository = new VetRepositoryImpl(entityManager);
        vetRepository.editAddressByUsername(username,address);
        entityManager.close();
        entityManagerFactory.close();

        Vet vet = findByUsername(username);
        VetPOJO vetPOJO = new VetPOJO(vet.getUsername().getUsername(),vet.getName(),vet.getAddress(),vet.getNeighborhood());

        return vetPOJO;
    }
    public VetPOJO editNeighborhoodByUsername(String username,String neighborhood){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        vetRepository = new VetRepositoryImpl(entityManager);
        vetRepository.editNeighborhoodByUsername(username,neighborhood);
        entityManager.close();
        entityManagerFactory.close();

        Vet vet = findByUsername(username);
        VetPOJO vetPOJO = new VetPOJO(vet.getUsername().getUsername(),vet.getName(),vet.getAddress(),vet.getNeighborhood());

        return vetPOJO;
    }

    public Vet findByUsername(String username){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        vetRepository = new VetRepositoryImpl(entityManager);
        Vet persistedVet = vetRepository.findByUsername(username).get();

        entityManager.close();
        entityManagerFactory.close();

        return persistedVet;
    }

    public Vet findByName(String name){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        vetRepository = new VetRepositoryImpl(entityManager);
        Vet persistedVet=null;
        List<Vet> vets = vetRepository.findAll();
        for (Vet vet : vets) {
            if(vet.getName().equals(name)){
                persistedVet = vet;
            }
        }
        entityManager.close();
        entityManagerFactory.close();

        return persistedVet;
    }

    public Vet findByAdress(String Adress){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        vetRepository = new VetRepositoryImpl(entityManager);
        Vet persistedVet = vetRepository.findByAddress(Adress).get();

        entityManager.close();
        entityManagerFactory.close();

        return persistedVet;
    }

    public  Vet findByNeighborhood(String neighborhood){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        vetRepository = new VetRepositoryImpl(entityManager);
        Vet persistedVet = vetRepository.findByNeighborhood(neighborhood).get();

        entityManager.close();
        entityManagerFactory.close();

        return persistedVet;
    }

    public List<VetPOJO> listVets(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        vetRepository = new VetRepositoryImpl(entityManager);
        List<Vet> vets = vetRepository.findAll();

        entityManager.close();
        entityManagerFactory.close();

        List<VetPOJO> vetPOJO = new ArrayList<>();
        for (Vet vet : vets){
            vetPOJO.add(new VetPOJO(
                    vet.getUsername().getUsername(),
                    vet.getName(),
                    vet.getAddress(),
                    vet.getNeighborhood()
            ));
        }
        return vetPOJO;
    }

    public VetPOJO saveVet(String username, String name, String address, String neighborhood){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        userRepository = new UserAppRepositoryImpl(entityManager);
        vetRepository = new VetRepositoryImpl(entityManager);

        Optional<UserApp> user = userRepository.findByUsername(username);
        user.ifPresent(u ->{
            Vet vet = new Vet(name,address,neighborhood);
            vet.setUsername(user.get());
            u.setVet(vet);
            userRepository.save(u);
        });

        entityManager.close();
        entityManagerFactory.close();

        VetPOJO vetPOJO = new VetPOJO(name,address,neighborhood);

        return vetPOJO;
    }
}
