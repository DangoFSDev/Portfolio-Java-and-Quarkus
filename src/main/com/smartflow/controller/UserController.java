package main.com.smartflow.controller;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import main.com.smartflow.request.UserRequest;
import main.com.smartflow.response.UserResponse;
import main.com.smartflow.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Path("/api/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    private final UserService service;

    @POST
    @Path("/login")
    public UserResponse userLogin(UserRequest request) {

        return service.userLogin(request);
    }

    @POST
    @Path("/addUser")
    public UserResponse addUser(UserRequest request) {

        return service.addUser(request);
    }

    @PUT
    @Path("/updateUser")
    public UserResponse updateUser(UserRequest request) {

        return service.updateUser(request);
    }

    @DELETE
    @Path("/deleteUser/{id}")
    public UserResponse deleteUser(@PathParam("id") Long id) {

        return service.deleteUser(id);
    }

}