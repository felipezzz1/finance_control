package com.fezor.finance_control.resource;

import com.fezor.finance_control.entity.User;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @POST
    @Transactional
    public User create(User user) {
        user.persist();
        return user;
    }

    @GET
    public List<User> listAll() {
        return User.listAll();
    }
}
