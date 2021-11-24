package edu.unbosque.Taller_5.servlets;

import edu.unbosque.Taller_5.services.PetCaseService;
import edu.unbosque.Taller_5.services.PetService;
import edu.unbosque.Taller_5.services.VisitService;
import edu.unbosque.Taller_5.servlets.pojos.PetCasePOJO;
import edu.unbosque.Taller_5.servlets.pojos.VisitPOJO;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
