package com.fezor.finance_control.resource;

import com.fezor.finance_control.dto.TransactionCreateDTO;
import com.fezor.finance_control.service.TransactionService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/transactions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TransactionResource {

    @Inject
    TransactionService service;

    @GET
    public Response list() {
        return Response.ok(service.listAll()).build();
    }

    @POST
    public Response create(TransactionCreateDTO dto) {
        return Response.status(Response.Status.CREATED)
                .entity(service.create(dto))
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, TransactionCreateDTO dto) {
        var updated = service.update(id, dto);

        if (updated == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        if (service.delete(id)) {
            return Response.noContent().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

}
