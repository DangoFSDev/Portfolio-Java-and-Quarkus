package main.com.smartflow.controller;

import java.util.ArrayList;
import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import main.com.smartflow.request.UserRequest;
import main.com.smartflow.response.LeavesResponse;
import main.com.smartflow.service.LeavesService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Path("/api")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LeavesController {

    private final LeavesService service;

    @GET
    @Path("/leaves")
    public List<LeavesResponse> getLeaves() {

        return new ArrayList<>();
    }

    @POST
    @Path("/addLeaves")
    public LeavesResponse addLeaves(List<UserRequest> request) {

        return new LeavesResponse();
    }

    @PUT
    @Path("/updateLeave")
    public LeavesResponse updateLeave(UserRequest request) {

        return new LeavesResponse();
    }

    @PUT
    @Path("/updateLeaves")
    public List<LeavesResponse> updateLeaves(List<UserRequest> request) {

        return new ArrayList<>();
    }

    @DELETE
    @Path("/cancelLeave/{id}")
    public LeavesResponse cancelLeave(@PathParam("id") Long id) {

        return new LeavesResponse();
    }

    @DELETE
    @Path("/cancelLeaves")
    public List<LeavesResponse> cancelLeaves(List<Integer> ids) {

        return new ArrayList<>();
    }

}
