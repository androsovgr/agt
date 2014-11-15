package ru.mephi.agt.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ru.mephi.agt.request.LoginRequest;
import ru.mephi.agt.request.StringRequest;
import ru.mephi.agt.response.IdResponse;
import ru.mephi.agt.response.LoginResponse;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ApiInterface {

	@Path("/login")
	@POST
	public LoginResponse login(LoginRequest request);

	@Path("/register")
	@POST
	public IdResponse register(StringRequest request);
}
