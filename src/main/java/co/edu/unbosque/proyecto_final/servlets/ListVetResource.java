package co.edu.unbosque.proyecto_final.servlets;

import co.edu.unbosque.proyecto_final.services.VetService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("vets")
public class ListVetResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVet() {

        Optional persistedVets = Optional.of(new VetService().listVets());

        return Response.ok()
                .entity(persistedVets.get())
                .build();
    }
}
