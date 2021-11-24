package edu.unbosque.Taller_5.services;

import edu.unbosque.Taller_5.jpa.entities.Owner;
import edu.unbosque.Taller_5.jpa.entities.Pet;
import edu.unbosque.Taller_5.jpa.repositories.OwnerRepository;
import edu.unbosque.Taller_5.jpa.repositories.OwnerRepositoryImpl;
import edu.unbosque.Taller_5.jpa.repositories.PetRepository;
import edu.unbosque.Taller_5.jpa.repositories.PetRepositoryImpl;
import edu.unbosque.Taller_5.servlets.pojos.PetPOJO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Stateless
public class PetService {

    OwnerRepository ownerRepository;
    PetRepository petRepository;

    public PetPOJO editMicrochipById(Integer pet_id, String microchip) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        petRepository = new PetRepositoryImpl(entityManager);
        petRepository.editMicrochipByPetId(pet_id,microchip);

        entityManager.close();
        entityManagerFactory.close();

        Pet user = findByPetId(pet_id);
        PetPOJO petPOJO = new PetPOJO(user.getPet_id(),user.getMicrochip(),user.getName(),user.getSpecies(),user.getRace(),user.getSize(),user.getSex(),user.getPicture(),user.getOwner().getPerson_id());
        return petPOJO;
    }

    public PetPOJO editNameByPetId(Integer pet_id, String name){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        petRepository = new PetRepositoryImpl(entityManager);
        petRepository.editNameByPetId(pet_id,name);

        entityManager.close();
        entityManagerFactory.close();

        Pet user = findByPetId(pet_id);
        PetPOJO petPOJO = new PetPOJO(user.getPet_id(),user.getMicrochip(),user.getName(),user.getSpecies(),user.getRace(),user.getSize(),user.getSex(),user.getPicture(),user.getOwner().getPerson_id());

        return petPOJO;
    }

    public PetPOJO editSpecieByPetId(Integer pet_id,String specie){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        petRepository = new PetRepositoryImpl(entityManager);
        petRepository.editSpecieByPetId(pet_id,specie);

        entityManager.close();
        entityManagerFactory.close();

        Pet user = findByPetId(pet_id);
        PetPOJO petPOJO = new PetPOJO(user.getPet_id(),user.getMicrochip(),user.getName(),user.getSpecies(),user.getRace(),user.getSize(),user.getSex(),user.getPicture(),user.getOwner().getPerson_id());


        return petPOJO;
    }

    public PetPOJO editRaceByPetId(Integer pet_id,String race){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        petRepository = new PetRepositoryImpl(entityManager);
        petRepository.editRaceByPetId(pet_id,race);

        entityManager.close();
        entityManagerFactory.close();

        Pet user = findByPetId(pet_id);
        PetPOJO petPOJO = new PetPOJO(user.getPet_id(),user.getMicrochip(),user.getName(),user.getSpecies(),user.getRace(),user.getSize(),user.getSex(),user.getPicture(),user.getOwner().getPerson_id());

        return petPOJO;
    }

    public PetPOJO editSizeByPetId(Integer pet_id,String size){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        petRepository = new PetRepositoryImpl(entityManager);
        petRepository.editSizeByPetId(pet_id,size);

        entityManager.close();
        entityManagerFactory.close();

        Pet user = findByPetId(pet_id);
        PetPOJO petPOJO = new PetPOJO(user.getPet_id(),user.getMicrochip(),user.getName(),user.getSpecies(),user.getRace(),user.getSize(),user.getSex(),user.getPicture(),user.getOwner().getPerson_id());

        return petPOJO;
    }

    public PetPOJO editSexByPetId(Integer pet_id,String sex){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        petRepository = new PetRepositoryImpl(entityManager);
        petRepository.editSexByPetId(pet_id,sex);

        entityManager.close();
        entityManagerFactory.close();

        Pet user = findByPetId(pet_id);
        PetPOJO petPOJO = new PetPOJO(user.getPet_id(),user.getMicrochip(),user.getName(),user.getSpecies(),user.getRace(),user.getSize(),user.getSex(),user.getPicture(),user.getOwner().getPerson_id());

        return petPOJO;
    }

    public PetPOJO editPictureByPetId(Integer pet_id,String picture){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        petRepository = new PetRepositoryImpl(entityManager);
        petRepository.editPictureByPetId(pet_id,picture);

        entityManager.close();
        entityManagerFactory.close();

        Pet user = findByPetId(pet_id);
        PetPOJO petPOJO = new PetPOJO(user.getPet_id(),user.getMicrochip(),user.getName(),user.getSpecies(),user.getRace(),user.getSize(),user.getSex(),user.getPicture(),user.getOwner().getPerson_id());

        return petPOJO;
    }
    public Pet findByPetId(Integer pet_id){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        petRepository = new PetRepositoryImpl(entityManager);
        Pet persistedPet = petRepository.findByPetId(pet_id).get();

        entityManager.close();
        entityManagerFactory.close();

        return persistedPet;
    }

    public Pet findByName(String name){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        petRepository = new PetRepositoryImpl(entityManager);
        Pet persistedPet = petRepository.findByName(name).get();

        entityManager.close();
        entityManagerFactory.close();

        return persistedPet;
    }

    public Pet findBySpecies(String species){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        petRepository = new PetRepositoryImpl(entityManager);
        Pet persistedPet = petRepository.findBySpecies(species).get();

        entityManager.close();
        entityManagerFactory.close();

        return persistedPet;
    }

    public Pet findByRace(String race){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        petRepository = new PetRepositoryImpl(entityManager);
        Pet persistedPet = petRepository.findByRace(race).get();

        entityManager.close();
        entityManagerFactory.close();

        return persistedPet;
    }

    public Pet findBySize(String size){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        petRepository = new PetRepositoryImpl(entityManager);
        Pet persistedPet = petRepository.findBySize(size).get();

        entityManager.close();
        entityManagerFactory.close();

        return persistedPet;
    }

    public Pet findBySex(String sex){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        petRepository = new PetRepositoryImpl(entityManager);
        Pet persistedPet = petRepository.findBySex(sex).get();

        entityManager.close();
        entityManagerFactory.close();

        return persistedPet;
    }

    public Pet findByOwner(Integer owner_id){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        petRepository = new PetRepositoryImpl(entityManager);
        Pet persistedPet = petRepository.findByOwner(owner_id).get();

        entityManager.close();
        entityManagerFactory.close();

        return persistedPet;
    }

    public List<PetPOJO> listPets(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        petRepository = new PetRepositoryImpl(entityManager);
        List<Pet> pets = petRepository.findAll();

        entityManager.close();
        entityManagerFactory.close();

        List<PetPOJO> petPOJO = new ArrayList<>();
        for (Pet pet : pets){
            petPOJO.add(new PetPOJO(
                    pet.getPet_id(),
                    pet.getMicrochip(),
                    pet.getName(),
                    pet.getSpecies(),
                    pet.getRace(),
                    pet.getSize(),
                    pet.getSex(),
                    pet.getPicture(),
                    pet.getOwner().getPerson_id()
            ));
        }
        return petPOJO;
    }

    public PetPOJO savePet( String microchip, String name, String species, String race, String size, String sex, String picture, Integer owner_id){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("taller_5");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        petRepository = new PetRepositoryImpl(entityManager);
        ownerRepository = new OwnerRepositoryImpl(entityManager);

        Optional<Owner> owner = Optional.of(ownerRepository.findAll().get(owner_id-1));

        owner.ifPresent(o -> {
            o.addPet( new Pet(microchip,name,species,sex,race,size,picture));
            ownerRepository.save(o);
        });

        entityManager.close();
        entityManagerFactory.close();
        PetPOJO petPOJO = new PetPOJO(listPets().size(), microchip, name, species,race,size,sex,picture,owner_id);

        return petPOJO;
    }

}
