package co.edu.unbosque.proyecto_final.services;



import co.edu.unbosque.proyecto_final.jpa.entities.Owner;
import co.edu.unbosque.proyecto_final.jpa.entities.UserApp;
import co.edu.unbosque.proyecto_final.jpa.repositories.OwnerRepository;
import co.edu.unbosque.proyecto_final.jpa.repositories.OwnerRepositoryImpl;
import co.edu.unbosque.proyecto_final.jpa.repositories.UserAppRepository;
import co.edu.unbosque.proyecto_final.jpa.repositories.UserAppRepositoryImpl;
import co.edu.unbosque.proyecto_final.servlets.pojos.OwnerPOJO;

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

    public OwnerPOJO editNameByUsername(String username, String name){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        ownerRepository = new OwnerRepositoryImpl(entityManager);
        ownerRepository.editNameByUsername(username,name);

        entityManager.close();
        entityManagerFactory.close();

        Owner owner = findByUserName(username);
        OwnerPOJO ownerPOJO = new OwnerPOJO(owner.getUserapp().getUsername(),owner.getPerson_id(),owner.getName(),owner.getAddress(),owner.getNeighborhood());

        return ownerPOJO;
    }
    public OwnerPOJO editAddressByUsername(String username,String address){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        ownerRepository = new OwnerRepositoryImpl(entityManager);
        ownerRepository.editAddressByUsername(username,address);

        entityManager.close();
        entityManagerFactory.close();

        Owner owner = findByUserName(username);
        OwnerPOJO ownerPOJO = new OwnerPOJO(owner.getUserapp().getUsername(),owner.getPerson_id(),owner.getName(),owner.getAddress(),owner.getNeighborhood());

        return ownerPOJO;
    }
    public OwnerPOJO editNeighborhoodByUsername(String username,String neighborhood){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        ownerRepository = new OwnerRepositoryImpl(entityManager);
        ownerRepository.editNeighborhoodByUsername(username,neighborhood);

        entityManager.close();
        entityManagerFactory.close();

        Owner owner = findByUserName(username);
        OwnerPOJO ownerPOJO = new OwnerPOJO(owner.getUserapp().getUsername(),owner.getPerson_id(),owner.getName(),owner.getAddress(),owner.getNeighborhood());

        return ownerPOJO;

    }


    public Owner findByOwnerId(Integer owner_id){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        ownerRepository = new OwnerRepositoryImpl(entityManager);
        List<Owner> owners= ownerRepository.findAll();
       // List<UserApp> userApps= userRepository.findAll();

        String user = "";
        Owner owner1=new Owner();
        for (Owner owner : owners) {
            if (owner.getPerson_id() == owner_id) {

                user = owner.getUserapp().getUsername();
                System.out.print(user+"aquiiiiiiiiiii");
                owner1 = new Owner( owner.getName(), owner.getAddress(), owner.getNeighborhood());
            }
        }
        entityManager.close();
        entityManagerFactory.close();

        return owner1;
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
    public Owner findByUserName(String username){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        ownerRepository = new OwnerRepositoryImpl(entityManager);
        Owner persistedOwner =ownerRepository.findByUserName(username).get();


        entityManager.close();
        entityManagerFactory.close();

        return persistedOwner;
    }
    public Integer findIdByUserName(String username){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        ownerRepository = new OwnerRepositoryImpl(entityManager);
        Owner persistedOwner = ownerRepository.findByAddress(username).get();


        entityManager.close();
        entityManagerFactory.close();

        return persistedOwner.getPerson_id();
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
                    owner.getUserapp().getUsername(),
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
            owner.setUserapp(user.get());
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
