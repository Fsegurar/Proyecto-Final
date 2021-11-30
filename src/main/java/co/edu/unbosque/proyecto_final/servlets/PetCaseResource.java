package co.edu.unbosque.proyecto_final.servlets;



import co.edu.unbosque.proyecto_final.services.PetCaseService;
import co.edu.unbosque.proyecto_final.services.PetService;
import co.edu.unbosque.proyecto_final.servlets.pojos.PetCasePOJO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Hashtable;
import java.util.Optional;

@Path("users")
public class PetCaseResource {

    @POST
    @Path("/{user_id}/pets/{pet_id}/petcase")
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
