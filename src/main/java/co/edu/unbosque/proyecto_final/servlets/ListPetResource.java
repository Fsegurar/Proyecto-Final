package co.edu.unbosque.proyecto_final.servlets;

import co.edu.unbosque.proyecto_final.services.PetService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("pets")
public class ListPetResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPet() {

        Optional persistedPets = Optional.of(new PetService().listPets());

        return Response.ok()
                .entity(persistedPets.get())
                .build();
    }
}
