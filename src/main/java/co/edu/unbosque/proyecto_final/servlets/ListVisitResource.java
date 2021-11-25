package co.edu.unbosque.proyecto_final.servlets;

import co.edu.unbosque.proyecto_final.services.VisitService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("visits")
public class ListVisitResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVisit() {

        Optional persistedVisits = Optional.of(new VisitService().listVisits());

        return Response.ok()
                .entity(persistedVisits.get())
                .build();
    }
}
