package com.fezor.finance_control.resource;

import com.fezor.finance_control.dto.UserCreateDTO;
import com.fezor.finance_control.dto.UserResponseDTO;
import com.fezor.finance_control.entity.User;
import com.fezor.finance_control.service.UserService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UserService userService;

    @GET
    public Response list() {
        return Response.ok(userService.listAll()).build();
    }

    @POST
    public Response create(UserCreateDTO dto) {
        return Response.status(Response.Status.CREATED)
                .entity(userService.create(dto))
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, UserCreateDTO dto) {
        UserResponseDTO updated = userService.update(id, dto);
        if (updated == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        if (userService.delete(id)) {
            return Response.noContent().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
