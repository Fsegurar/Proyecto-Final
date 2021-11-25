package co.edu.unbosque.proyecto_final.servlets;

import co.edu.unbosque.proyecto_final.services.PetCaseService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("petCases")
public class ListPetCaseResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPetCase() {

        Optional persistedPetCases = Optional.of(new PetCaseService().listPetCases());

        return Response.ok()
                .entity(persistedPetCases.get())
                .build();
    }
}
