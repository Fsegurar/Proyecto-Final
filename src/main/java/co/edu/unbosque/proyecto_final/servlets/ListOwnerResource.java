package co.edu.unbosque.proyecto_final.servlets;

import co.edu.unbosque.proyecto_final.services.OwnerService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/owners")
public class ListOwnerResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOwner() {

        Optional persistedOwners = Optional.of(new OwnerService().listOwners());

        return Response.ok()
                .entity(persistedOwners.get())
                .build();
    }
}
