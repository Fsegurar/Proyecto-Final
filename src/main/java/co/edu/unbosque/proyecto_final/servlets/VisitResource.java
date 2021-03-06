package co.edu.unbosque.proyecto_final.servlets;


import co.edu.unbosque.proyecto_final.jpa.entities.Vet;
import co.edu.unbosque.proyecto_final.services.PetCaseService;
import co.edu.unbosque.proyecto_final.services.PetService;
import co.edu.unbosque.proyecto_final.services.VetService;
import co.edu.unbosque.proyecto_final.services.VisitService;
import co.edu.unbosque.proyecto_final.servlets.pojos.VetPOJO;
import co.edu.unbosque.proyecto_final.servlets.pojos.VisitPOJO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.ClientInfoStatus;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;

@Path("users")
public class VisitResource {

    @POST
    @Path("/{vet_id}/pets/{pet_id}/visit")
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

    @GET
    @Path("/visits/{vet_name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVisitsByVetName(@PathParam("vet_name") String vet_name) {
        Optional persistVisit = Optional.of(new VisitService().findByVet(vet_name));

        if (persistVisit.isPresent()) {
            return Response.status(Response.Status.OK)
                    .entity(persistVisit.get())
                    .build();
        } else {
            return Response.status(400)
                    .build();
        }
    }
    @GET
    @Path("/visits/count")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVisitsCountByType() {

        Hashtable<String, Integer> visitsCount = new Hashtable<>();
        visitsCount.put("Esterilizacion",0);
        visitsCount.put("Implantacion_de_Microchip",0);
        visitsCount.put("Vacunacion",0);
        visitsCount.put("Desparacitacion",0);
        visitsCount.put("Urgencia",0);
        visitsCount.put("Control",0);

        visitsCount.forEach((k, v) -> {
            v = new VisitService().countByType(k);
            visitsCount.replace(k,v);
        });

        if (!visitsCount.isEmpty()) {
            return Response.status(Response.Status.OK)
                    .entity(visitsCount)
                    .build();
        } else {
            return Response.status(400)
                    .build();
        }
    }

    @GET
    @Path("/visits/count/vet")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVisitsCountByVet() {

        Hashtable<Vet, Integer> visits = new Hashtable<>();
        Hashtable<String, Integer> visitsCount = new Hashtable<>();

        List<Vet> vets = new VetService().listVets();

        vets.forEach((vet) -> {
            visits.put(vet, 0);
        });

        visits.forEach((k, v) -> {
            v = new VisitService().countByVetName(k);
            visitsCount.put(k.getName(),v);
        });

        if (!visitsCount.isEmpty()) {
            return Response.status(Response.Status.OK)
                    .entity(visitsCount)
                    .build();
        } else {
            return Response.status(400)
                    .build();
        }
    }

}
