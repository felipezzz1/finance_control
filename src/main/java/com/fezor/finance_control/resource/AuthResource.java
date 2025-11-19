package com.fezor.finance_control.resource;

import com.fezor.finance_control.dto.LoginDTO;
import com.fezor.finance_control.dto.LoginResponseDTO;
import com.fezor.finance_control.dto.RegisterDTO;
import com.fezor.finance_control.service.AuthService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    AuthService authService;

    @POST
    @Path("/register")
    public void register(RegisterDTO registerDTO){
        authService.register(registerDTO);
    }

    @POST
    @Path("/login")
    public LoginResponseDTO login(LoginDTO loginDTO){
        return authService.login(loginDTO);
    }
}
