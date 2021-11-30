package co.edu.unbosque.proyecto_final.servlets;

import co.edu.unbosque.proyecto_final.services.OwnerService;

import javax.validation.constraints.Null;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Optional;

@Path("/users/owner/")
public class OwnerResource {

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOwner(@PathParam("id") String id){

        Optional persistedOwner = Optional.of(new OwnerService().findByUserName(id));

        if (persistedOwner.isPresent()) {
            return Response.status(Response.Status.OK)
                    .entity(persistedOwner.get())
                    .build();
        } else {
            return Response.status(400)
                    .build();
        }

    }

    @GET
    @Path("/count")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOwnersCountByNeighborhood(){

        Hashtable<String, Integer> ownersCount = new Hashtable<>();

        ownersCount.put("A. NARINO",0);
        ownersCount.put("B. UNIDOS",0);
        ownersCount.put("BOSA",0);
        ownersCount.put("C. BOLIVAR",0);
        ownersCount.put("CHAPINERO",0);
        ownersCount.put("ENGATIVA",0);
        ownersCount.put("FONTIBON",0);
        ownersCount.put("KENNEDY",0);
        ownersCount.put("LA CANDELARIA",0);
        ownersCount.put("LOS MARTIRES",0);
        ownersCount.put("P. ARANDA",0);
        ownersCount.put("R. URIBE",0);
        ownersCount.put("SAN CRISTOBAL",0);
        ownersCount.put("SANTA FE",0);
        ownersCount.put("SUBA",0);
        ownersCount.put("SUMAPAZ",0);
        ownersCount.put("TEUSAQUILLO",0);
        ownersCount.put("TUNJUELITO",0);
        ownersCount.put("USAQUEN",0);
        ownersCount.put("USME",0);

        ownersCount.forEach((k, v) -> {
            v = new OwnerService().countByNeighborhood(k);
            ownersCount.replace(k,v);
        });

        if (!ownersCount.isEmpty()) {
            return Response.status(Response.Status.OK)
                    .entity(ownersCount)
                    .build();
        } else {
            return Response.status(400)
                    .build();
        }

    }

}
