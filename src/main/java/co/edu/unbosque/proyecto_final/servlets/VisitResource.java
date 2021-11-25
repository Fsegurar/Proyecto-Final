package co.edu.unbosque.proyecto_final.servlets;


import co.edu.unbosque.proyecto_final.services.PetService;
import co.edu.unbosque.proyecto_final.services.VisitService;
import co.edu.unbosque.proyecto_final.servlets.pojos.VisitPOJO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("users/{vet_id}/pets/{pet_id}/visit")
public class VisitResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@PathParam("vet_id") String vet_id, @PathParam("pet_id") Integer pet_id,
                           VisitPOJO visit){

        Optional<VisitPOJO> persistedVisit = Optional.of(new VisitService().saveVisit(
                visit.getCreated_at(), visit.getType(), visit.getDescription(), vet_id, pet_id));

        if (visit.getPet()!=null){
            new PetService().editMicrochipById(pet_id, visit.getPet().getMicrochip());
        }

        if (persistedVisit.isPresent()) {
            return Response.status(Response.Status.CREATED)
                    .build();
        } else {
            return Response.status(400)
                    .build();
        }
    }

}
