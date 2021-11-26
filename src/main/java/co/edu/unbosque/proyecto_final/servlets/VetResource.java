package co.edu.unbosque.proyecto_final.servlets;

import co.edu.unbosque.proyecto_final.services.VetService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("users/vet/{username}")
public class VetResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVet(@PathParam("username") String username){

        Optional persistedVet = Optional.of(new VetService());

        if (persistedVet.isPresent()) {
            return Response.status(Response.Status.OK)
                    .build();
        } else {
            return Response.status(400)
                    .build();
        }

    }

}
