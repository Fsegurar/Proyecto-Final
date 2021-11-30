package co.edu.unbosque.proyecto_final.servlets;



import co.edu.unbosque.proyecto_final.services.OwnerService;
import co.edu.unbosque.proyecto_final.services.PetService;
import co.edu.unbosque.proyecto_final.servlets.pojos.PetPOJO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Hashtable;
import java.util.Optional;

@Path("users")
public class PetResource {

    @POST
    @Path("/{user_id}/pets")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@PathParam("user_id") Integer user_id, PetPOJO pet) {

        Optional<PetPOJO> persistedPet = Optional.of(new PetService().savePet(
                pet.getMicrochip(), pet.getName(), pet.getSpecies(), pet.getRace(), pet.getSize(),
                pet.getSex(), pet.getPicture(), user_id));

        if (persistedPet.isPresent()) {
            return Response.status(Response.Status.CREATED)
                    .build();
        } else {
            return Response.status(400)
                    .build();
        }

    }

    @PUT
    @Path("/pets/{pet_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response modify(@PathParam("pet_id") Integer pet_id, PetPOJO pet) {

        Optional<PetPOJO> persistedPet = null;
        if (pet.getName() != null) {
            persistedPet = Optional.of(new PetService().editNameByPetId(pet_id, pet.getName()));
        }
        if (pet.getSpecies() != null) {
            persistedPet = Optional.of(new PetService().editSpecieByPetId(pet_id, pet.getSpecies()));
        }
        if (pet.getRace() != null) {
            persistedPet = Optional.of(new PetService().editRaceByPetId(pet_id, pet.getRace()));
        }
        if (pet.getSize() != null) {
            persistedPet = Optional.of(new PetService().editSizeByPetId(pet_id, pet.getSize()));
        }
        if (pet.getSex() != null) {
            persistedPet = Optional.of(new PetService().editSexByPetId(pet_id, pet.getSex()));
        }
        if (pet.getPicture() != null) {
            persistedPet = Optional.of(new PetService().editPictureByPetId(pet_id, pet.getPicture()));
        }

        if (persistedPet.isPresent()) {
            return Response.status(Response.Status.OK)
                    .entity(persistedPet.get())
                    .build();
        } else {
            return Response.status(400)
                    .build();
        }

    }

    @GET
    @Path("/pets/count")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPetsCountBySpecies() {

        Hashtable<String, Integer> petsCount = new Hashtable<>();
        petsCount.put("Perro",0);
        petsCount.put("Gato",0);

        petsCount.forEach((k, v) -> {
            v = new PetService().countBySpecies(k);
            petsCount.replace(k,v);
        });

        if (!petsCount.isEmpty()) {
            return Response.status(Response.Status.OK)
                    .entity(petsCount)
                    .build();
        } else {
            return Response.status(400)
                    .build();
        }
    }
}
