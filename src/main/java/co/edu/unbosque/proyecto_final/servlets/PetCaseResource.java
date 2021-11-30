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

    @GET
    @Path("/petcase/count")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPetsCountBySpecies() {

        Hashtable<String, Integer> petCasesCount = new Hashtable<>();
        petCasesCount.put("Esterilizacion",0);
        petCasesCount.put("Implantacion_de_Microchip",0);
        petCasesCount.put("Vacunacion",0);
        petCasesCount.put("desparacitacion",0);
        petCasesCount.put("Urgencia",0);
        petCasesCount.put("Control",0);

        petCasesCount.forEach((k, v) -> {
            v = new PetCaseService().countByType(k);
            petCasesCount.replace(k,v);
        });

        if (!petCasesCount.isEmpty()) {
            return Response.status(Response.Status.OK)
                    .entity(petCasesCount)
                    .build();
        } else {
            return Response.status(400)
                    .build();
        }
    }
}
