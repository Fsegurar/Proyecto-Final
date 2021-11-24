package edu.unbosque.Taller_5.servlets;

import edu.unbosque.Taller_5.services.PetCaseService;
import edu.unbosque.Taller_5.servlets.pojos.PetCasePOJO;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("users/{user_id}/pets/{pet_id}/petcase")
public class PetCaseResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@PathParam("user_id") Integer user_id, @PathParam("pet_id") Integer pet_id,
                           PetCasePOJO petCase){

        Optional<PetCasePOJO> persistedPetCase = Optional.of(new PetCaseService().savePetCase(
                petCase.getCreated_at(), petCase.getType(), petCase.getDescription(), pet_id));

        if (persistedPetCase.isPresent()) {
            return Response.status(Response.Status.CREATED)
                    .build();
        } else {
            return Response.status(400)
                    .build();
        }
    }
}
