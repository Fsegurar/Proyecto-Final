package co.edu.unbosque.proyecto_final.servlets;

import co.edu.unbosque.proyecto_final.services.OwnerService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/users/owner/{id}")
public class OwnerResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOwner(@PathParam("id") Integer id){

        Optional persistedOwner = Optional.of(new OwnerService().findByOwnerId(id));

        if (persistedOwner.isPresent()) {
            return Response.status(Response.Status.OK)
                    .entity(persistedOwner.get())
                    .build();
        } else {
            return Response.status(400)
                    .build();
        }

    }

}
