package co.edu.unbosque.proyecto_final.servlets;

import co.edu.unbosque.proyecto_final.services.UserAppService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/users")
public class ListUsersResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {

        Optional persistedUsers = Optional.of(new UserAppService().listUsers());

        return Response.ok()
                .entity(persistedUsers.get())
                .build();
    }
}
